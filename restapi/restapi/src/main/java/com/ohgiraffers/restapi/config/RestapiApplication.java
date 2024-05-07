package com.ohgiraffers.restapi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ohgiraffers.restapi")
public class RestapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }

}


/* 필기 : 프로젝트 생성 순서
*   1. SpringBoot 프로젝트 생성 : Spring Initializer 설정6개 (SpringBoot)
*   2. build.gradle 의존성 추가
*   3. application.yaml 설정
*   4. SpringBoot Application 을 /config 패키지로 이동
*   5. AuthController 의 login() 메소드 작성
*       ① /common 패키지 생성 후 ResponseDTO 생성
*       ② MemberDTO, MemberRoleDTO, AuthorityDTO 생성
*   6. AuthService 의 login() 메소드 작성
*       ① Service 에 logger 추가
*       ② Member, MemberRole, MemberRolePk, Authority Entity 생성
*       ③ /exception 패키지 생성 후 패키지 내부 작성
*       ④ /config 패키지에 JpaConfiguration, SecurityConfig 등 생성
*       ⑤ /jwt 패키지 생성 후 패키지 내부 작성
*   7. AuthRepository 생성
*   8. AuthController 의 signup() 메소드 작성
*   9. AuthService 의 signup() 메소드 작성
* */