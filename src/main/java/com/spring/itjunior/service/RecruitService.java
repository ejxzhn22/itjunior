package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.domain.RecruitLike;
import com.spring.itjunior.dto.PageDto;
import org.json.simple.JSONObject;

import java.util.List;

public interface RecruitService {

    public boolean saveRecruitInfo(RecruitDTO recruitDTO);

    public RecruitDTO getRecruitInfo(long job_idx);

    public JSONObject getApiList(PageDto pageDto);

    public boolean isRecruitLike(RecruitLike recruitLike);

    public boolean pushRecruitLike(long job_idx, PrincipalDetails principalDetails);

    public boolean cancelRecruitLike(RecruitLike recruitLike);

    public List<Category> keywordList();

    //채용게시판 글 좋아요 갯수 출력
//    public int selectLikeCount(JSONObject jsonObject);

}
