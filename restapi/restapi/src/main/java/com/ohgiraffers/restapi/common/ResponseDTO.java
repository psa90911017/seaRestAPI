package com.ohgiraffers.restapi.common;

import org.springframework.http.HttpStatus;

/* 설명 : 응답 body에 담길 객체 (JSON 문자열이 될 객체) */
public class ResponseDTO {

    private int status;         // 상태 코드
    private String message;     // 응답 메시지
    private Object data;        // 응답 데이터

    public ResponseDTO() {
    }

    /* 설명. HttpStatus 클래스 사용:
     *  HttpStatus enum 타입에서 'value'라는 int형 상태 코드 값만 추출해 status 초기값 설정
    */
    public ResponseDTO(HttpStatus status, String message, Object data) {
        this.status = status.value();   // 설명 : Status Code만 추출해서 사용
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
