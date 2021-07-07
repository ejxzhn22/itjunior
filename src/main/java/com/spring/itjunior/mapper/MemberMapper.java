package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    public int insertMember(MemberDTO memberDTO);
}
