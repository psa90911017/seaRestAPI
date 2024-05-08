package com.ohgiraffers.restapi.member.service;

import com.ohgiraffers.restapi.member.dto.MemberDTO;
import com.ohgiraffers.restapi.member.entity.Member;
import com.ohgiraffers.restapi.member.entity.MemberRole;
import com.ohgiraffers.restapi.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	private final MemberRepository memberRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CustomUserDetailsService(MemberRepository memberRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.modelMapper = modelMapper;
	}

	/*
	 * 설명. 만약, org.hibernate.LazyInitializationException 에러가 발생한다면...
	 *  -> 조회(SELECT)라 해도 @Transactional 을 달아주자!
	 *  해당 에러는 영속성 컨텍스트가 도중에 종료되어 발생하는 오류이다.
	 *  @Transactional 을 달면 해당 메소드가 끝날 때까지 하나의 영속성 컨텍스트가 유지되어 뒤늦게 연관관계에 있는
	 *  엔티티를 활용함에 있어서 문제되지 않는다.
	 */
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
		Member member = memberRepository.findByMemberId(memberId);
		
		/* 설명. MemberDTO 는 엔티티를 옮겨 담는 DTO 이자 UserDetails 이다. */
		MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);
		
		/* 설명. 엔티티로는 MemberDTO 에 추가한 Collection<GrantedAuthority> authorities 속성이 옮겨담아지지 않는다. */
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(MemberRole memberRole : member.getMemberRole()) {
			String authorityName = memberRole.getAuthority().getAuthorityName();
			authorities.add(new SimpleGrantedAuthority(authorityName));
		}
		
		memberDTO.setAuthorities(authorities);
		
		return memberDTO;
	}
}
