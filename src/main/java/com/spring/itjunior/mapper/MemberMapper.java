package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    public int insertMember(MemberDTO memberDTO);

    public MemberDTO findByUseridAndPassword(@Param("userId") String userId, @Param("password") String password);

    public void updateLoginTime(MemberDTO memberDTO);
}
