package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.domain.RecruitLike;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.mapper.RecruitMapper;
import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Log4j2
@Service
public class RecruitServiceImpl implements RecruitService{

    private RecruitMapper recruitMapper;
    private ApiService apiService;

    @Autowired
    public RecruitServiceImpl(RecruitMapper recruitMapper, ApiService apiService) {
        this.recruitMapper = recruitMapper;
        this.apiService = apiService;
    }

    @Transactional
    @Override
    public boolean saveRecruitInfo(RecruitDTO recruitDTO) {
        int queryResult = 0;

        queryResult = recruitMapper.insertRecruit(recruitDTO);

        return (queryResult == 1) ? true : false;
    }

    @Override
    public RecruitDTO getRecruitInfo(long job_idx) {
        RecruitDTO params = recruitMapper.selectRecruitByIdx(job_idx);
        return params;
    }

    @Override
    public JSONObject getApiList(PageDto pageDto) {
        JSONObject apiResult = apiService.recruitApiList(pageDto);
        log.info("api 호출 json 값 >>> {}",apiResult.get("jobs"));

        return apiResult;

    }

    @Override
    public boolean isRecruitLike(RecruitLike recruitLike) {
        int queryResult = recruitMapper.selectIsRecruitLike(recruitLike);
        return (queryResult > 0) ? true : false;
    }

    @Override
    public boolean pushRecruitLike(long job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        int recruit_idx = recruitMapper.selectRecruitByIdx(job_idx).getRecruit_idx();
        RecruitLike recruitLike = RecruitLike.builder()
                .recruit_idx(recruit_idx)
                .member_idx(principalDetails.getMember().getMember_idx())
                .build();
        int queryResult = recruitMapper.insertRecruitLike(recruitLike);
        return (queryResult > 0) ? true : false;
    }

    @Override
    public boolean cancelRecruitLike(RecruitLike recruitLike) {
        int queryResult = recruitMapper.deleteRecruitLike(recruitLike);
        return (queryResult > 0) ? true : false;
    }

    @Override
    public List<Category> keywordList() {
        return recruitMapper.selectKeyword();
    }

//    @Override
//    public int selectLikeCount(JSONObject jsonObject) {
//        return 0;
//    }
}
