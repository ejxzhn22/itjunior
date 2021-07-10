package com.spring.itjunior.service;

import com.spring.itjunior.domain.Member;

public interface MemberService {

    public boolean saveMemberInfo(Member member);


    public void updateLastLogin(Member member);
}


//    public Member loginMember(Member member);
