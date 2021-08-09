package com.spring.itjunior.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitScrap {
    private int scrap_idx;
    private int recruit_idx;
    private int member_idx;

    @Builder
    public RecruitScrap(int recruit_idx, int member_idx) {
        this.recruit_idx = recruit_idx;
        this.member_idx = member_idx;
    }
}
