package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.domain.RecruitScrap;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.mapper.RecruitMapper;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        return apiResult;

    }

    @Override
    public boolean isRecruitScrap(long job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (isRecruitInfo(job_idx)) return false;
        int recruit_idx = recruitMapper.selectRecruitByIdx(job_idx).getRecruit_idx();
        RecruitScrap recruitScrap = RecruitScrap.builder()
                .recruit_idx(recruit_idx)
                .member_idx(principalDetails.getMember().getMember_idx())
                .build();
        int queryResult = recruitMapper.selectIsRecruitScrap(recruitScrap);
        return (queryResult > 0) ? true : false;
    }


    @Override
    public boolean pushRecruitScrap(long job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (isRecruitInfo(job_idx)) return false;
        int recruit_idx = recruitMapper.selectRecruitByIdx(job_idx).getRecruit_idx();
        RecruitScrap recruitScrap = RecruitScrap.builder()
                .recruit_idx(recruit_idx)
                .member_idx(principalDetails.getMember().getMember_idx())
                .build();
        int queryResult = recruitMapper.insertRecruitScrap(recruitScrap);
        return (queryResult > 0) ? true : false;
    }

    @Override
    public boolean cancelRecruitScrap(long job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (isRecruitInfo(job_idx)) return false;
        int recruit_idx = recruitMapper.selectRecruitByIdx(job_idx).getRecruit_idx();
        RecruitScrap recruitScrap = RecruitScrap.builder()
                .recruit_idx(recruit_idx)
                .member_idx(principalDetails.getMember().getMember_idx())
                .build();
        int queryResult = recruitMapper.deleteRecruitScrap(recruitScrap);
        return (queryResult > 0) ? true : false;
    }

    @Override
    public int selectScrapCount(long job_idx) {
        int count = 0;
        if (isRecruitInfo(job_idx)) return count;
        int recruit_idx = recruitMapper.selectRecruitByIdx(job_idx).getRecruit_idx();
        count = recruitMapper.selectScrapCount(recruit_idx);
        return count;
    }

    @Override
    public List<RecruitDTO> getRecruitScrapList(@AuthenticationPrincipal PrincipalDetails principalDetails,PageDto pageDto) {
        int member_idx = principalDetails.getMember().getMember_idx();

        Map<String, Object> scrapListParams = new HashMap<String, Object>();
        scrapListParams.put("member_idx",member_idx);
        scrapListParams.put("pageDto", pageDto);

        List<RecruitDTO> recruitScrapList = recruitMapper.selectScrappedInfoByUserIdx(scrapListParams);
        return recruitScrapList;
    }

    @Override
    public int selectScrapMyCount(int member_idx) {
        int scrapTotalCount = recruitMapper.selectTotalScrapCount(member_idx);
        return scrapTotalCount;
    }

    //jobList.jsp 에서 받아온 job_idx를 DB Recruit테이블로 넘겨서 해당 idx의 대한 기업정보가 존재하지 않으면 true 반환.
    private boolean isRecruitInfo(long job_idx) {
        if (recruitMapper.selectRecruitByIdx(job_idx) == null) {
            return true;
        }
        return false;
    }

    public List<Category> keywordList() {
        return recruitMapper.selectKeyword();
    }

}
