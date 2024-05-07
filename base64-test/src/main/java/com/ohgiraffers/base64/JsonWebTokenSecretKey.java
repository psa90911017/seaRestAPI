package com.ohgiraffers.base64;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class JsonWebTokenSecretKey {

    public static void main(String[] args) {

        /* 설명. JWT의 비밀키 생성(HMAC-SHA 알고리즘 적용) - 최소한의 길이로 랜덤키 생성 됨 */
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//		Key key = Keys.hmacShaKeyFor();

        /* 설명. BASE64로 인코딩 */
        String secretKey = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("생성된 비밀 키: " + secretKey);

        /* 설명. JWT 토큰 생성 */
        String jws = Jwts.builder().setSubject("{\"name\": \"sea.park\"}").signWith(key).compact();
        System.out.println("생성된 JWT 토큰: " + jws);

        /* 설명. secretKey */
        if(verifyToken(jws, secretKey)) {
            System.out.println("토큰 인증 됨");
        }
    }

    /* https://www.baeldung.com/java-jwt-token-decode */
    private static boolean verifyToken(String accessToken, final String secretKey) {

        boolean validation = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(accessToken).getBody().getSubject().equals("{\"name\": \"sea.park\"}");

        if (!validation) {
            throw new RuntimeException("토큰 인증 되지 않음");
        }

        return true;
    }
}
