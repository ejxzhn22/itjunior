package com.spring.itjunior.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "replyLikeBuild")
@Data
public class ReplyLike {
    private int replylike_idx;
    private int reply_idx;
    private  int member_idx;

    public static ReplyLikeBuilder builder(int reply_idx, int member_idx){

        return replyLikeBuild()
                .reply_idx(reply_idx)
                .member_idx(member_idx);
    }
}
