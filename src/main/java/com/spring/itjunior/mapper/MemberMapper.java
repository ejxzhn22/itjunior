package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    public int insertMember(Member member);

    public Member findByUseridAndPassword(@Param("userId") String userId, @Param("password") String password);

    public void updateLoginTime(Member member);
}
