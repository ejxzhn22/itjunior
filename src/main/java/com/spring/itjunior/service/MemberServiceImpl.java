package com.spring.itjunior.service;

import com.spring.itjunior.domain.MemberDTO;
import com.spring.itjunior.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public boolean saveMemberInfo(MemberDTO memberDTO) {
        int queryResult = memberMapper.insertMember(memberDTO);
        return (queryResult == 1) ? true : false;
    }
}
