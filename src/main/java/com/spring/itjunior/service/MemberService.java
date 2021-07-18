package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.dto.JoinDto;

public interface MemberService {

    public boolean saveMemberInfo(JoinDto joinDto);

    public boolean updateMemberInfo(Member requestMember);

    public Member findByIdx(int member_idx);

    public Member findByUserId(String userId);

    public String findNameAndEmail(Member member);

    public int findUserIdAndName(Member member);

    public String partialEncEmail(String rawEmail);

    public boolean deleteByIdx(int member_idx);

    public boolean updateMemberPassword(Member member);

}

//    public void updateLastLogin(Member member); //일단 사용 안함. mapper updateLastLogin은 사용함

//    public Member loginMember(Member member);
