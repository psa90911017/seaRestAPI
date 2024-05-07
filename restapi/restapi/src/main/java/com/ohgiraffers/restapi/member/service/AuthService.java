package com.ohgiraffers.restapi.member.service;

import com.ohgiraffers.restapi.exception.DuplicatedMemberEmailException;
import com.ohgiraffers.restapi.exception.LoginFailedException;
import com.ohgiraffers.restapi.jwt.TokenProvider;
import com.ohgiraffers.restapi.member.dto.MemberDTO;
import com.ohgiraffers.restapi.member.dto.TokenDTO;
import com.ohgiraffers.restapi.member.entity.Member;
import com.ohgiraffers.restapi.member.entity.MemberRole;
import com.ohgiraffers.restapi.member.repository.MemberRepository;
import com.ohgiraffers.restapi.member.repository.MemberRoleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;
    private final MemberRoleRepository memberRoleRepository;

    @Autowired
    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, TokenProvider tokenProvider, ModelMapper modelMapper, MemberRoleRepository memberRoleRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.modelMapper = modelMapper;
        this.memberRoleRepository = memberRoleRepository;
    }

    /* 필기 : 로그인 */
    public Object login(MemberDTO memberDTO) {
        log.info("[AuthService] login() START");

        /* 목차. 1. 아이디 조회 */
        Member member = memberRepository.findByMemberId(memberDTO.getMemberId());

        if (member == null) {
            throw new LoginFailedException(memberDTO.getMemberId() + "를 찾을 수 없습니다.");
        }

        /* 목차. 2. 비밀번호 매칭
         *  BCrypt 암호화 라이브러리 bean을 의존성 주입받아 처리하는 부분부터 security 설정 부분을 추가해 보자.
         *  ===================================================================
         *  BCryptPasswordEncoder.matches() : matches(rawPassword, encodedPassword)
         *  여기서 두 번째 전달인자인 encodedPassword는 '다이제스트'라고도 하며, 암호화된 비밀번호를 의미한다.
         *  즉, 사용자로부터 넘어온 요청 속 비밀번호와 원본 비밀번호를 직접 비교하는 것이 아니라,
         *  암호화된 비밀번호와 비교하기 때문에 보안상 안전하다.
         * */
        if (!passwordEncoder.matches(memberDTO.getMemberPassword(), member.getMemberPassword())) {
            log.info("[AuthService] Password Match Failed!");
            throw new LoginFailedException("잘못 된 비밀번호 입니다.");
        }

        /* 목차. 3. 토큰 발급 */
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);
        log.info("[AuthService] tokenDTO {}", tokenDTO);

        log.info("[AuthService] login() END");
        return memberDTO;
    }

    /* 필기 : 회원가입 */
    /* 설명. signup은 DML(INSERT) 작업이므로 @Transactional 어노테이션 추가 */
    @Transactional
    public MemberDTO signup(MemberDTO memberDTO) {
        log.info("[AuthService] signup() Start.");
        log.info("[AuthService] memberDTO {}", memberDTO);

        /* 설명. 이메일 중복 유효성 검사(비즈니스 로직에 따라 선택적으로 구현하면 됨) */
        if(memberRepository.findByMemberEmail(memberDTO.getMemberEmail()) != null) {
            log.info("[AuthService] 이메일이 중복됩니다.");
            throw new DuplicatedMemberEmailException("이메일이 중복됩니다.");
        }

        /* 설명. 우선 Repository로 쿼리를 작성하기 전에 DTO를 Entity로 매핑. */
        Member registMember = modelMapper.map(memberDTO, Member.class);

        /* 목차. 1. tbl_member 테이블에 회원 INSERT */
        /* 설명. 비밀번호 암호화 후 insert */
        registMember.setMemberPassword(passwordEncoder.encode(registMember.getMemberPassword()));
        Member result1 = memberRepository.save(registMember);      // 설명. 반환형은 int값이 아닌 엔티티임.

        /* 목차. 2. tbl_member_role 테이블에 회원별 권한 INSERT (현재 엔티티에는 회원가입 후 pk값이 없다!) */
        /* 목차. 2-1. 우선 일반 권한(AuthorityCode값이 2번)의 회원을 추가(일종의 디폴트 권한을 지정해주면 됨) */
        /*
         * 목차. 2-2. 엔티티에는 추가 할 회원의 pk값이 아직 없으므로 기존 회원의 마지막 회원 번호를 조회
         *      (하지만 jpql에 의해 앞선 save와 jpql이 flush()로 쿼리와 함께 날아가고 회원이 이미 sequence객체 값
         *       증가와 함께 insert가 되 버린다. -> 결론은, maxMemberCode가 현재 가입하는 회원의 번호이다.)
         * */
        int maxMemberCode = memberRepository.maxMemberCode();   // 설명. JPQL을 사용해 회원번호 max값 추출

        MemberRole registMemberRole = new MemberRole(maxMemberCode, 2);

        MemberRole result2 = memberRoleRepository.save(registMemberRole);

        /* 설명. 위의 두 가지 save()가 모두 성공해야 해당 트랜잭션이 성공했다고 판단. */
        log.info("[AuthService] Member Insert Result {}",
                (result1 != null && result2 != null) ? "회원 가입 성공" : "회원 가입 실패");

        log.info("[AuthService] signup() End.");

        return memberDTO;
    }
}
