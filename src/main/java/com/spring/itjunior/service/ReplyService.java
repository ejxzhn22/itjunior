package com.spring.itjunior.service;

import com.spring.itjunior.domain.Reply;
import com.spring.itjunior.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyMapper replyMapper;

    //댓글 모두 가져오기
    public List<Reply> replies(int free_idx){

        return replyMapper.selectAll(free_idx);
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
}
