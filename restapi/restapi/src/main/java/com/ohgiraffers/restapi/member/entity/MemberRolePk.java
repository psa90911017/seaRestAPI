package com.ohgiraffers.restapi.member.entity;

import java.io.Serializable;

/* 설명. 복합키 타입을 정의할 때는 Serializable을 반드시 구현 */
public class MemberRolePk implements Serializable {

    private int memberNo;
    private int authorityCode;

    public MemberRolePk() {
    }

    public MemberRolePk(int memberNo, int authorityCode) {
        this.memberNo = memberNo;
        this.authorityCode = authorityCode;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public int getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(int authorityCode) {
        this.authorityCode = authorityCode;
    }

    @Override
    public String toString() {
        return "MemberRolePk [memberNo=" + memberNo + ", authorityCode=" + authorityCode + "]";
    }
}

