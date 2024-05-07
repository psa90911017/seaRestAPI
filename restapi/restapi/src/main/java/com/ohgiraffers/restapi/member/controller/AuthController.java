package com.ohgiraffers.restapi.member.controller;

import com.ohgiraffers.restapi.common.ResponseDTO;
import com.ohgiraffers.restapi.member.dto.MemberDTO;
import com.ohgiraffers.restapi.member.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/* 설명. 알아둬야 하는 개념들: @RestController, @ResponseBody, ResponseEntity, CORS */
/*
 * 설명. @RestController
 *  Spring에서 RESTful 웹 서비스를 개발하기 위해 @Controller와 @ResponseBody를 결합해놓은 어노테이션.
 *  해당 클래스의 모든 메소드는 HTTP 요청에 대한 View를 반환하지 않고, HTTP 응답 몸체(response body)에
 *  직접 데이터를 작성하게 된다.
 *  이 데이터는 Spring Boot에서 기본적으로 제공하는 MappingJackson2HttpMessageConverter가
 *  내부적으로 ObjectMapper를 활용하여 인코딩 타입(UTF-8) 및 MIME 타입(application/json)이 적용된 JSON 문자열로 반환한다.
 *  즉, 이 많은 작업들을 @RestControlle 어노테이션이 처리해준다.
*/

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /* 설명.
     *  @RequestBody를 통해 RequestBody로 넘어온 JSON 문자열을 파싱해 MemberDTO 속성으로 매핑해 객체로 받아낸다.
     *  (회원 아이디, 비밀번호)
     *  ========================================================================================================
     *  참고로 요청의 body에서 데이터를 뽑아내겠다는 것은 요청이 POST 요청이었다는 것을 알 수 있다.
     *  왜냐하면 GET 요청은 body가 아니라 header에 데이터가 담겨있기 때문이다.
    */
    @Operation(summary = "로그인요청", description = "로그인 및 인증이 진행됩니다", tags =  {"AuthController"})
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO) {

        /* 설명. ResponseEntity
         *  HTTP 응답 몸체와 헤더, 그리고 상태 코드를 제어할 수 있는 Spring Framework의 클래스다.
         *    응답으로 변환될 정보가 담긴 모든 요소들을 해당 객체로 만들어서 반환해 준다.(body + header + status)
         *  (ResponseBody와 차별점이 있다면, ResponseEntity는 HTTP 상태 코드나 헤더도 다룰 수 있다.)
         *  필요한 정보들만 담아서 전달할 수 있기 때문에 REST API를 만들 때 유용하게 사용하는 클래스다.
         *    또한 ResponseEntity를 사용할 때, 생성자 대신 Builder 사용을 권장한다.
         *  (숫자 타입인 상태 코드를 실수로 잘못 입력하지 않도록 메소드들이 제공 된다.)
        */
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인성공", authService.login(memberDTO)));
        /* 설명. (React 및 Spring 연계 시, 가장 중요!!!!!!!!!!)
         *  ResponseEntity의 body() 메소드를 사용하면 Response객체의 body에 담기는 ResponseDTO는 JSON문자열이 되고
         *  화면단이 React인 곳으로 가면 결국 Store에 해당 리듀서가 관리하는 state 값이 된다.
        */
    }

    /* 필기 : 회원가입 */
    @Operation(summary = "회원 가입 요청", description = "회원 가입이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(@RequestBody MemberDTO memberDTO) {   // 회원 가입 정보를 받아 냄
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.CREATED, "회원가입 성공", authService.signup(memberDTO)));
    }
}
