package com.ohgiraffers.base64;

import java.util.Base64;
import java.util.Base64.*;

public class EncodingDecoding {

    public static void main(String[] args) {

        /* 설명. java 8에서 제공하는 기본 Base64 Encoder와 Decoder */
        Encoder encode = Base64.getEncoder();
        Decoder decode = Base64.getDecoder();

        /* 설명. 1. encode */
        String testStr = "망곰이랑햄터랑같이놀자아";
        byte[] testStrToByteArr = testStr.getBytes();

        byte[] encodeByte = encode.encode(testStrToByteArr);

        String encodedStr = new String(encodeByte);
        System.out.println("인코딩: " + encodedStr);

        /* 설명. 2. decode */
        byte[] decodeByte = decode.decode(encodedStr);
        System.out.println("디코딩: " + new String(decodeByte));
    }
}
