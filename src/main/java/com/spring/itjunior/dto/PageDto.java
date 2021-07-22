package com.spring.itjunior.dto;

import com.spring.itjunior.paging.Criteria;
import com.spring.itjunior.paging.PaginationInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDto extends Criteria {

    /** 페이징 정보 */
    private PaginationInfo paginationInfo;
}
