package com.spring.itjunior.service;

import com.spring.itjunior.domain.Member;

public interface MemberService {

    public boolean saveMemberInfo(Member member);

    public boolean updateMemberInfo(Member requestMember);

    public Member findByIdx(int member_idx);

    public Member findByUserId(String userId);

    public boolean deleteByIdx(int member_idx);

}

//    public void updateLastLogin(Member member); //일단 사용 안함. mapper updateLastLogin은 사용함

//    public Member loginMember(Member member);
