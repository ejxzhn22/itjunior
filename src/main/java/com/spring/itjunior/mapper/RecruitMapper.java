package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.domain.RecruitScrap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitMapper {

    /** 사람인api로 호출된 채용기업정보 리스트 페이지에서 하나의 기업의 좋아요 또는 스크랩 버튼을 누를 시 DB에 기업정보를 추가한다.*/
    public int insertRecruit(RecruitDTO recruitDTO);

    public RecruitDTO selectRecruitByIdx(long job_idx);

    public int selectIsRecruitScrap(RecruitScrap recruitScrap);

    public int insertRecruitScrap(RecruitScrap recruitScrap);

    public int deleteRecruitScrap(RecruitScrap recruitScrap);

    //잡리스트 키워드 가져오기
    public List<Category> selectKeyword();

    //채용게시판 글 좋아요 갯수 출력
    public int selectScrapCount(int recruit_idx);



}
