package com.ohgiraffers.restapi.member.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_member")
public class Member {

    @Id
    @Column(name = "member_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberCode;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_password")
    private String memberPassword;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_email")
    private String memberEmail;

    @OneToMany
    @JoinColumn(name = "member_code")
    private List<MemberRole> memberRole;

    public Member() {
    }

    public Member(int memberCode, String memberId, String memberPassword, String memberName, String memberEmail, List<MemberRole> memberRole) {
        this.memberCode = memberCode;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberRole = memberRole;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public List<MemberRole> getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(List<MemberRole> memberRole) {
        this.memberRole = memberRole;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberCode=" + memberCode +
                ", memberId='" + memberId + '\'' +
                ", memberPassword='" + memberPassword + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberRole=" + memberRole +
                '}';
    }
}
