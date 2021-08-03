package com.spring.itjunior.service;

import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.paging.PaginationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PagingService {

    private final FreeBoardService freeBoardService;
    private final QnaBoardService qnaBoardService;

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

    public PageDto makeNewsPaging(Object totalCount) {
        PageDto pageDto = new PageDto();
        pageDto.setRecordsPerPage(10);
        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(Integer.parseInt(totalCount.toString()));
        pageDto.setPaginationInfo(paginationInfo);

        return pageDto;
    }

}
