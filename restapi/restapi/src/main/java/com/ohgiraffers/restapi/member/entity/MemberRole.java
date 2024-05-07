package com.ohgiraffers.restapi.member.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_member_role")
@IdClass(MemberRolePk.class)
public class MemberRole {

	@Id
	@Column(name = "member_code")
	private int memberNo;
	
	@Id
	@Column(name = "authority_code")
	private int authorityCode;
	
	/* 설명. Authority 타입의 속성은 조회할 때 JOIN용도로 사용하되, INSERT 또는 UPDATE 할 때는 무시하도록 설정. */
	@ManyToOne
	@JoinColumn(name = "authority_code", insertable = false, updatable = false)
	private Authority authority;

	public MemberRole() {
	}
	
	public MemberRole(int memberNo, int authorityCode) {
		this.memberNo = memberNo;
		this.authorityCode = authorityCode;
	}

	public MemberRole(int memberNo, int authorityCode, Authority authority) {
		this.memberNo = memberNo;
		this.authorityCode = authorityCode;
		this.authority = authority;
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

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "MemberRole [memberNo=" + memberNo + ", authorityCode=" + authorityCode + ", authority=" + authority
				+ "]";
	}
}
