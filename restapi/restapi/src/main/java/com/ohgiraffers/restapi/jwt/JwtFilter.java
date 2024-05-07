package com.ohgiraffers.restapi.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/* 설명. OncePerRequestFilter: 사용자의 요청에 한 번만 동작하는 필터 */
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    /* 설명. 사용자가 요청 헤더(request header)에 Authorization 속성으로 token을 던짐 */
    public static final String AUTHORIZATION_HEADER = "Authorization";
    /* 설명. 사용자가 던지는 토큰 값만 파싱하기 위한 접두사 저장용 변수(접두사는 Bearer라는 표준으로 정의됨)
     *  (https://datatracker.ietf.org/doc/html/rfc6750#section-6.1.1)
     * */
    public static final String BEARER_PREFIX = "Bearer";

    private final TokenProvider tokenProvider;

    @Autowired
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        /* 설명. 요청에서 토큰값 추출 */
        String jwt = resolveToken(request);

        /* 설명. 추출한 토큰의 유효성 검사 후 인증을 위해 Authentication 객체를 SecurityCOntextHolder에 담는다.
         *  아래 if()문 내 2줄의 코드가 인증 작업이다.
         * */
        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        /* 설명. 다음 filter chain 진행 */
        filterChain.doFilter(request, response);
    }

    /* 설명. Request Header에서 토큰 정보 꺼내기(위에 정의한 static final 변수 두개 사용) */
    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);	// = "Authorization"

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            /* 설명. 사용자가 보낸 토큰 값 추출 */
            return bearerToken.substring(7);
        }

        return null;
    }
}
