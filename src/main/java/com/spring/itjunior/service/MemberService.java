package com.spring.itjunior.service;

import com.spring.itjunior.domain.Member;

public interface MemberService {

    public boolean saveMemberInfo(Member member);


    public void updateLastLogin(Member member);

    public Member findByIdx(int member_idx);

    public Member findByUserId(String userId);

    public boolean deleteByIdx(int member_idx);
}


//    public Member loginMember(Member member);
