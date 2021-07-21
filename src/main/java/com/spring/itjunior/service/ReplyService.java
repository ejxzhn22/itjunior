package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Reply;
import com.spring.itjunior.domain.ReplyLike;
import com.spring.itjunior.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyMapper replyMapper;

    //댓글 모두 가져오기
    public List<Reply> replies(int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<Reply> replies = replyMapper.selectAll(free_idx);

        List<Integer> likeList = replyMapper.likeList(principalDetails.getMember().getMember_idx());
        List<Integer> unlikeList = replyMapper.unlikeList(principalDetails.getMember().getMember_idx());

        //like, unlike
        replies.forEach(reply -> {
            int likeCount = replyMapper.likeCount(reply.getReply_idx());
            int unlikeCount = replyMapper.unlikeCount(reply.getReply_idx());
            reply.setLike_count(likeCount);
            reply.setUnlike_count(unlikeCount);

            likeList.forEach(like ->{
                if(reply.getReply_idx() == like){
                    reply.setLike_state(true);
                }
            });

            unlikeList.forEach(unlike->{
                if(reply.getReply_idx() == unlike){
                    reply.setUnlike_state(true);
                }
            });
        });

        return replies;
    }

    //댓글 하나 가져오기
    public Reply reply(int reply_idx){

        return replyMapper.selectOne(reply_idx);
    }

    //부모 댓글 추가
    public int insertParent(Reply reply){
        replyMapper.insertParent(reply);
        System.out.println("키값 : "+reply.getReply_idx());
        return reply.getReply_idx();

    }

    // 대댓글 추가
    public Reply insertChild(Reply reply){
        replyMapper.insertChild(reply);

        return replyMapper.lastOne();

    }


    //댓글 갯수
    public int replycnt(int free_idx){
        return replyMapper.replycnt(free_idx);
    }

    //부모댓글 삭제하기
    public int deleteParent(int reply_idx){
        return replyMapper.deleteParent(reply_idx);
    }

    //대댓글 삭제하기
    public int deleteChild(int reply_idx) {
        return replyMapper.deleteChild(reply_idx);
    }

    //댓글 좋아요
    public int likeParent(ReplyLike replyLike){
        return replyMapper.likeParent(replyLike);
    }

    //댓글 좋아요취소하기
    public int likeParentDelete(ReplyLike replyLike){
        return replyMapper.likeParentDelete(replyLike);
    }

    //댓글 싫어요
    public int unlikeParent(ReplyLike replyLike){
        return replyMapper.unlikeParent(replyLike);
    }

    //댓글 싫어요취소
    public int unlikeParentDelete(ReplyLike replyLike){
        return replyMapper.unlikeParentDelete(replyLike);
    }
}
