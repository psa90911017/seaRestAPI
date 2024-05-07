package com.ohgiraffers.restapi.member.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_authority")
public class Authority {

	/* 설명. @GeneratedValue 생략
	 *  향후, 프로젝트의 설계 방향에 따라 회원의 권한을 추가하는 로직이 생길 수 있다.
	 *  (ex: '관리자 또는 인사팀 권한을 가진 사용자는 사용자에게 부여되는 새로운 권한을 추가할 수 있다.')
	 *  (ex: '영업팀이 신설되어 영업팀에 소속된 사용자들에게 부여할 SALES 권한을 추가해야 한다.')
	 *  그러면 해당 테이블에 새로운 권한이 INSERT 될 것인데, 이 때 @GeneratedValue를 추가해 사용하면 된다.
	 * */
	@Id
	@Column(name = "authority_code")
	private int authorityCode;
	
	@Column(name = "authority_name")
	private String authorityName;
	
	@Column(name = "authority_desc")
	private String authorityDesc;

	public Authority() {
	}

	public Authority(int authorityCode, String authorityName, String authorityDesc) {
		this.authorityCode = authorityCode;
		this.authorityName = authorityName;
		this.authorityDesc = authorityDesc;
	}

	public int getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(int authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	@Override
	public String toString() {
		return "Authority{" +
				"authorityCode=" + authorityCode +
				", authorityName='" + authorityName + '\'' +
				", authorityDesc='" + authorityDesc + '\'' +
				'}';
	}
}





