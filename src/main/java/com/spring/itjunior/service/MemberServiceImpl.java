package com.spring.itjunior.service;

import com.spring.itjunior.domain.Member;
import com.spring.itjunior.domain.Role;
import com.spring.itjunior.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

    private MemberMapper memberMapper;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper,BCryptPasswordEncoder encoder) {
        this.memberMapper = memberMapper;
        this.encoder = encoder;
    }

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public boolean saveMemberInfo(Member member) {
        if(member.getUserId().equals("root")){
            member.setRole(Role.ADMIN);
        }
        String rawPassword = member.getPassword();
        String encPassword = encoder.encode(rawPassword);
        member.setPassword(encPassword);
        member.setRole(Role.USER);

        int queryResult = memberMapper.insertOrUpdateMember(member);
        return (queryResult == 1) ? true : false;
    }

    @Transactional
    @Override
    public boolean updateMemberInfo(int member_idx, Member requestMember) {
        Member memberInfo = memberMapper.selectMemberByIdx(member_idx);

        memberInfo.setPassword(requestMember.getPassword());
        memberInfo.setNickname(requestMember.getNickname());
        memberInfo.setEmail(requestMember.getEmail());

        int queryResult = memberMapper.insertOrUpdateMember(memberInfo);

        log.info("수정 결과 = {}",queryResult);

        return (queryResult > 0) ? true : false;
    }

    @Override
    public void updateLastLogin(Member member) {
        memberMapper.updateLoginTime(member);
    }

    @Override
    public Member findByIdx(int member_idx) {
        Member selectMemberInfo = memberMapper.selectMemberByIdx(member_idx);
        log.info("member_idx로 회원정보조회 >>> {}",selectMemberInfo.toString());
        return selectMemberInfo;
    }

    @Override
    public Member findByUserId(String userId) {
        Member selectMemberInfo = memberMapper.selectMemberByUserId(userId);
        log.info("userId로 회원정보조회 >>> {}",selectMemberInfo.toString());
        return selectMemberInfo;
    }

    @Override
    public boolean deleteByIdx(int member_idx) {
        int queryResult = memberMapper.deleteMemberByIdx(member_idx);
        return (queryResult==1) ? true : false;
    }


}
//    해당 로그인 service 역시 전통적인 방식이기때문에 사용 x
//    @Transactional(readOnly = true) //Select할때 트랜잭션 시작.서비스 종료시에 트랜잭션 종료(정합성유지)
//    @Override
//    public Member loginMember(Member member) {
//        log.info("login service userid>>>{}", member.getUserId());
//        return memberMapper.findByUseridAndPassword(member.getUserId(), member.getPassword());
//    }
