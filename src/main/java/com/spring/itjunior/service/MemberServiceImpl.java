package com.spring.itjunior.service;

import com.spring.itjunior.domain.DeleteYN;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.domain.Role;
import com.spring.itjunior.dto.JoinDto;
import com.spring.itjunior.dto.UpdateMemberDto;
import com.spring.itjunior.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class MemberServiceImpl implements MemberService{

    private MemberMapper memberMapper;
    private BCryptPasswordEncoder encoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper,BCryptPasswordEncoder encoder,AuthenticationManager authenticationManager) {
        this.memberMapper = memberMapper;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }


    @Transactional
    @Override
    public boolean saveMemberInfo(JoinDto joinDto) {
        String encPassword = getEncPassword(joinDto.getPassword());
        Member member = Member.builder()
                .userId(joinDto.getUserId())
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .nickname(joinDto.getNickname())
                .role((joinDto.getUserId().equals("root") ? Role.ADMIN : Role.USER))
                .password(encPassword)
                .delete_yn(DeleteYN.N)
                .build();

        int queryResult = memberMapper.insertOrUpdateMember(member);
        return (queryResult == 1) ? true : false;
    }

    //암호화 메서드
    private String getEncPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Transactional
    @Override
    public boolean updateMemberInfo(UpdateMemberDto updateMemberDto) {
        Member memberInfo = memberMapper.selectMemberByIdx(updateMemberDto.getMember_idx());
        log.info("updateMemberDto >>> {}",updateMemberDto.toString());
        log.info("memberInfo >>> {}",memberInfo.toString());
        if (StringUtils.isNotBlank(updateMemberDto.getPassword())) {
            log.info("null,공백이 아닌 정상적인 패스워드. 암호화 실행.");
            String encPassword = getEncPassword(updateMemberDto.getPassword());
            memberInfo.setPassword(encPassword);
        }
        log.info("회원 수정 이름 값 >>> {}",updateMemberDto.getName());
        memberInfo.setName(updateMemberDto.getName());
        log.info("회원 수정된 이름 값 >> {}",memberInfo.getName());
        memberInfo.setNickname(updateMemberDto.getNickname());
        memberInfo.setEmail(updateMemberDto.getEmail());

        log.info("기존 회원 >>> {}",updateMemberDto.toString());
        int queryResult = memberMapper.insertOrUpdateMember(memberInfo);
        log.info("수정된 회원 >>> "+memberInfo.toString());

        return (queryResult > 0) ? true : false;
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
    public String findNameAndEmail(Member member) {
        String resultId = memberMapper.findNameAndEmail(member);
        if (StringUtils.isBlank(resultId)) {
            return "fail";
        }
        return resultId;
    }

    @Override
    public int findUserIdAndName(Member member) {
        int resultIdx = memberMapper.findUserIdAndName(member);
        if (resultIdx == 0) {
            return 0;
        }
        return resultIdx;
    }

    @Override
    public String partialEncEmail(String rawEmail) {
        //이메일을 받아 @를 찾고
        //@인덱스와 첫번째 글자인덱스 사이를 반을 나눈 값이 prefix가 된다.
        //메일형식의 . 을 찾고
        //.과 @의 중간값이 suffix 인덱스가 된다. 그리고 suffix인덱스 - prefix인덱스 = 채워질 별의 갯수 가 된다.
        int length = rawEmail.length();
        int partition = 0;
        int partition2 = 0;
        int prefix = 0;
        int suffix = 0;
        for (int i = 0; i < length; i++) {
            if (rawEmail.charAt(i) == '@') {
                partition = i;
            } else if (rawEmail.charAt(i) == '.') {
                partition2 = i;
            }
        }
        int pre =partition/2;
        int suf = (partition2 - partition)/2;  //@와 . 의 사이값을 2로 나눈다.(@와. 의 중간값)
        prefix = partition - pre;
        suffix = partition2 - suf + 1; // +1 부분을 조절해주면 조절한 만큼의 *이 .에서 @쪽으로 더 생성된다.

        int star = suffix - prefix;
        String starStr = "";
        for (int i = 0; i < star; i++) {
            starStr += "*";
        }
        StringBuffer sb = new StringBuffer();
        sb.append(rawEmail);
        sb.replace(prefix,suffix,starStr);
        log.info("이메일 부분 암호화 완료 >> {}",sb.toString());
        return sb.toString();
    }


    @Override
    public boolean deleteByIdx(int member_idx) {
        int queryResult = memberMapper.deleteMemberByIdx(member_idx);
        return (queryResult==1) ? true : false;
    }

    @Override
    public boolean updateMemberPassword(Member member) {
        String encPassword = getEncPassword(member.getPassword());
        member.setPassword(encPassword);

        int resultUpdate = memberMapper.changePasswordByIdx(member);

        return resultUpdate > 0 ? true : false;
    }

    @Override
    public String idCheckByUserId(String userId) {
        int resultCheck = memberMapper.idCheck(userId);
        return resultCheck == 1 ? "same" : "notSame";
    }

    @Override
    public String emailCheckByEmail(String email) {
        int resultCheck = memberMapper.emailCheck(email);
        return resultCheck == 1 ? "same" : "notSame";
    }

    @Override
    public String nickNameCheckByNickname(String nickname) {
        int resultCheck = memberMapper.nickNameCheck(nickname);
        return resultCheck == 1 ? "same" : "notSame";
    }


}
//    //security PrincipalDetailsService 에서 해당 mapper를 사용하므로, 이 메서드는 일단 사용 안함.
//    @Override
//    public void updateLastLogin(Member member) {
//        memberMapper.updateLoginTime(member);
//    }


//    해당 로그인 service 역시 전통적인 방식이기때문에 사용 x
//    @Transactional(readOnly = true) //Select할때 트랜잭션 시작.서비스 종료시에 트랜잭션 종료(정합성유지)
//    @Override
//    public Member loginMember(Member member) {
//        log.info("login service userid>>>{}", member.getUserId());
//        return memberMapper.findByUseridAndPassword(member.getUserId(), member.getPassword());
//    }
