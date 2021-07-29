package com.spring.itjunior.service;

import com.spring.itjunior.domain.RecruitDTO;
import org.json.simple.JSONObject;

public interface RecruitService {

    public boolean saveRecruitInfo(RecruitDTO recruitDTO);

    public JSONObject getApiList();

}
