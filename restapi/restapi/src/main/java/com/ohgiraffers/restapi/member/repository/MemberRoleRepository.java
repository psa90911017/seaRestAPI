package com.ohgiraffers.restapi.member.repository;

import com.ohgiraffers.restapi.member.entity.MemberRole;
import com.ohgiraffers.restapi.member.entity.MemberRolePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, MemberRolePk>{

}
