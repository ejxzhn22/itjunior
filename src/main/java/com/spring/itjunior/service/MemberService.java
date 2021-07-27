package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.dto.JoinDto;
import com.spring.itjunior.dto.UpdateMemberDto;

public interface MemberService {

    public boolean saveMemberInfo(JoinDto joinDto);

    public boolean updateMemberInfo(UpdateMemberDto updateMemberDto);

    public Member findByIdx(int member_idx);

    public Member findByUserId(String userId);

    public String findNameAndEmail(Member member);

    public int findUserIdAndName(Member member);

    public String partialEncEmail(String rawEmail);

    public boolean deleteByIdx(Member member);

    public boolean deleteMemberByIdxForOauth(int member_idx);

    public boolean updateMemberPassword(Member member);

    public String idCheckByUserId(String userId);

    public String emailCheckByEmail(String email);

    public String nickNameCheckByNickname(String nickname);

}

//    public void updateLastLogin(Member member); //일단 사용 안함. mapper updateLastLogin은 사용함

//    public Member loginMember(Member member);
