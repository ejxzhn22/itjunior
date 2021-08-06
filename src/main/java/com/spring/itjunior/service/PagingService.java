package com.spring.itjunior.service;

import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.paging.PaginationInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class PagingService {

    private final FreeBoardService freeBoardService;
    private final QnaBoardService qnaBoardService;
    private final RecruitServiceImpl recruitServiceImpl;

    public PageDto makeFreePaging(PageDto pageDto) {

        int boardTotalCount = freeBoardService.selectBoardTotalCount(pageDto);

        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(boardTotalCount);
        pageDto.setPaginationInfo(paginationInfo);

        return pageDto;

    }

    public PageDto makeQnaPaging(PageDto pageDto) {

        int qnaTotalCount = qnaBoardService.selectBoardTotalCount(pageDto);

        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(qnaTotalCount);
        pageDto.setPaginationInfo(paginationInfo);

        return pageDto;
    }

    public PageDto makeNewsPaging(PageDto pageDto, JSONObject jsonObject) {

        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(Integer.parseInt(jsonObject.get("total").toString()));
        pageDto.setPaginationInfo(paginationInfo);
        pageDto.setRecordsPerPage(10);

        System.out.println("paging dto: "+pageDto);
        System.out.println("paging pageinfo: "+paginationInfo);

        return pageDto;
    }

    public PageDto makeRecruitPaging(PageDto pageDto, JSONObject jsonObject) {
        JSONObject inJobs = (JSONObject) jsonObject.get("jobs");

        pageDto.setRecordsPerPage(Integer.parseInt(inJobs.get("count").toString()));
        pageDto.setCurrentPageNo(Integer.parseInt(inJobs.get("start").toString())+1); //사람인 api start값 0부터 시작하기 때문에 +1.
        pageDto.setPageSize(10);

        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(Integer.parseInt(inJobs.get("total").toString()));
        log.info("makeRecruit서비스 >>> {}",paginationInfo.toString());

        pageDto.setPaginationInfo(paginationInfo);

        return pageDto;
    }

}
