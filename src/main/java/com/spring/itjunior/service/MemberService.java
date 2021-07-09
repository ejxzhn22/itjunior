package com.spring.itjunior.service;

import com.spring.itjunior.domain.MemberDTO;

public interface MemberService {

    public boolean saveMemberInfo(MemberDTO memberDTO);

    public MemberDTO loginMember(MemberDTO memberDTO);

    public void updateLastLogin(MemberDTO memberDTO);
}
