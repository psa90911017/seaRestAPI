package com.ohgiraffers.restapi.config;

import com.ohgiraffers.restapi.jwt.JwtAuthenticationEntryPoint;
import com.ohgiraffers.restapi.jwt.JwtAccessDeniedHandler;
import com.ohgiraffers.restapi.jwt.JwtFilter;
import com.ohgiraffers.restapi.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	@Autowired
	public SecurityConfig(TokenProvider tokenProvider,
						  JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
						  JwtAccessDeniedHandler jwtAccessDeniedHandler) {
		this.tokenProvider = tokenProvider;
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}
	
	/* 설명. 1. 암호화 처리를 위한 PasswordEncoder를 빈으로 설정(빈을 등록 시 메소드 이름 오타 없도록 주의!) */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* 설명. 2. Spring Security 설정을 무시 할 정적 리소스 등록 */
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	/* 설명. 3. HTTP요청에 대한 권한별 설정 (세션 인증 -> 토큰 인증으로 인해 바뀐 부분 존재) */

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http
			.csrf(csrf -> csrf.disable())	// CSRF 보호 비활성화
			.exceptionHandling(exception -> {	//예외 처리
				exception.authenticationEntryPoint(jwtAuthenticationEntryPoint);	// 필요한 권한이 없을 시 403 상태코드 발생
				exception.accessDeniedHandler(jwtAccessDeniedHandler);				// 유효한 자격증명을 제공하지 않고 접근 시 401 상태코드 발생
			})
			.authorizeHttpRequests(auth -> {  // HTTP 요청에 대한 접근 권한 설정
				/* 설명.
				 *  CORS를 위해 preflight 요청 처리용 options 요청 허용.
				 *  preflight request란?
				 *  요청 할 url이 외부 도메인일 경우 웹 브라우저에서 자체 실행되며 options 메소드로 사전 요청을 보내게 된다.
				 *  사전에 요청이 안전한지 확인하기 위함(유효한지 서버에 미리 파악할 수 있도록 보내는 수단이다.)
				 * */
				auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();	// CORS Preflight 요청 허용
				auth.requestMatchers("/").authenticated();  					// 기본 경로는 인증 필요
				auth.requestMatchers("/auth/**", "/api/v1/products/**", "/api/v1/reviews/**").permitAll();	// 특정 경로는 무조건 허용
				auth.requestMatchers("/api/**").hasAnyRole("USER", "ADMIN");							// API 경로는 USER 또는 ADMIN 역할을 가진 사용자만 접근 가능
				/* 설명. 아래부터는 프로젝트 초기 Security 기능을 약화시켜 개발을 진행하게 끔 해주는 내용들이다. */
//				auth.anyRequest().permitAll();	// 어떤 요청이든 허용 -> Security를 활용한 로그인이 모두 완성되지 않았을 때 사용할 것
			})
			.sessionManagement(session ->  // 세션 방식을 사용하지 않음
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.cors(cors -> {})	// 기본 CORS 설정 사용
			.addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);  // 우리가 직접 작성한 커스텀 필터인 JwtFilter를 필터 체인에 추가

		return http.build();
	}
	
	/* 설명. 4. CORS 설정용 Bean(허용 할 origin과 httpMethod 종류와 header 값) */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Origin",
													"Content-type",
													"Access-Control-Allow-Headers",
													"Authorization",
													"X-Requested-With"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
