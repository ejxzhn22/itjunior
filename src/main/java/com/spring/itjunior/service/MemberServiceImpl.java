package com.spring.itjunior.service;

import com.spring.itjunior.domain.Member;
import com.spring.itjunior.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public boolean saveMemberInfo(Member member) {
        int queryResult = memberMapper.insertMember(member);
        return (queryResult == 1) ? true : false;
    }

    @Transactional(readOnly = true) //Select할때 트랜잭션 시작.서비스 종료시에 트랜잭션 종료(정합성유지)
    @Override
    public Member loginMember(Member member) {
        log.info("login service userid>>>{}", member.getUserId());
        return memberMapper.findByUseridAndPassword(member.getUserId(), member.getPassword());
    }

    @Override
    public void updateLastLogin(Member member) {
        memberMapper.updateLoginTime(member);
    }

}
