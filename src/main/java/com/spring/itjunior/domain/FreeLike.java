package com.spring.itjunior.domain;

import lombok.Builder;
import lombok.Data;

@Builder(builderMethodName = "freeLikeBuild")
@Data
public class FreeLike {
    private int freelike_idx;
    private int free_idx;
    private  int member_idx;

    public static FreeLikeBuilder builder(int free_idx, int member_idx){
        return freeLikeBuild()
                .free_idx(free_idx)
                .member_idx(member_idx);
    }

}
