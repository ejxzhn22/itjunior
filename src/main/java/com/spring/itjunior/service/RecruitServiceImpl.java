package com.spring.itjunior.service;

import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.mapper.RecruitMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
    public String getApiList() {
        String apiResult = apiService.recruitApiList();

        return apiResult;

    }
}
