package com.spring.itjunior.config.oauth;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.DeleteYN;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.domain.Role;
import com.spring.itjunior.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberMapper memberMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("getClientRegistration() >>> {}",userRequest.getClientRegistration()); //registrationId 어떤 Oauth로그인 했는지 확인 가능.
        log.info("getAccessToken() >>> {}",userRequest.getAccessToken().getTokenValue());
        //구글 로그인 버튼 클릭 -> 구글 로그인창 -> 구글 로그인 완료 -> 코드를 리턴(Oauth라이브러리) -> AccessToken요청(여기까지가 userRequest 정보)
        //userRequest정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필을 받아준다.
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes() >>> {}",oAuth2User.getAttributes());

        String provider = userRequest.getClientRegistration().getRegistrationId(); //google
        String providerId = oAuth2User.getAttribute("sub");
        String userId = provider +"_"+ providerId; //google_110073601230259037362

        Member member = memberMapper.selectMemberByUserId(userId);

        if (member == null) { //DB에 검색된 회원이 존재하지 않는다면, 구글 최초 로그인
            log.info("ITJunior 최초 구글 로그인 입니다. 회원가입을 진행하겠습니다.");
            String uuidValue = oauthDefaultUUIDPassword();
            String password = oauthDefaultEncodingPassword(uuidValue); //itjunior암호화 + uuid생성 값
            log.info("google로그인 회원가입 비밀번호 >>> ",password);
            String nickname = oAuth2User.getAttribute("name").toString().replace("-","");

            //DB member테이블의 name varchar(50)으로 설정해 주었으며, name의 max valid는 20이다. providerId의 값을 고려하여 varchar(50)으로 설정.
            //WelcomeToITJuniorUser의 문구는 글자수 최소 21자 이기때문에 구글 최초 로그인 후 HomeController에서 분기를 신청하여 로그인 시 자동으로 회원수정폼으로 이동하게 되며
            //이동 후 회원은 name(WelcomeToITJuniorUser~~)값을 고치지 않고는 수정 할 수가 없다. 그러므로 회원 이름 수정을 강제시킬 수 있다.
            String name = "WelcomeToITJuniorUser" + providerId;

            String email = oAuth2User.getAttribute("email");
            Role role = Role.USER;
            DeleteYN deleteYN = DeleteYN.N;

            member = Member.builder()
                    .userId(userId)
                    .password(password)
                    .nickname(nickname)
                    .name(name)
                    .email(email)
                    .role(role)
                    .uuid(uuidValue)
                    .delete_yn(deleteYN)
                    .provider(provider)
                    .providerId(providerId)
                    .build();
            memberMapper.insertOrUpdateMember(member);
        }else {
            log.info("이미 회원등록이 되있습니다. 로그인을 진행합니다.");
        }

        return new PrincipalDetails(member,oAuth2User.getAttributes()); //이때 authentication 객체에 들어가면서 시큐리티 세션에 저장이 된다.
    }

    public String oauthDefaultUUIDPassword(){
        UUID uuid = UUID.randomUUID();
        String uuidPwd = uuid.toString().replace("-", "");
        return uuidPwd;
    }
    public String oauthDefaultEncodingPassword(String uuidValue){
        String encPwd = bCryptPasswordEncoder.encode("itjunior"+uuidValue);
        return encPwd;
    }
}
