package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    public int insertOrUpdateMember(Member member);

    public void updateLoginTime(Member member);

    public Member selectMemberByIdx(int member_idx);

    public Member selectMemberByUserId(String UserId);

    public int deleteMemberByIdx(int member_idx);
}

//    public Member findByUseridAndPassword(@Param("userId") String userId, @Param("password") String password);
