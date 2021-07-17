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

    //부모 댓글 추가
    public int insertParent(Reply reply){
        return replyMapper.insertParent(reply);

    }
    //대댓글 추가
    public int insertChild(Reply reply){
        return replyMapper.insertChild(reply);

    }

    //댓글 갯수
    public int replycnt(int free_idx){
        return replyMapper.replycnt(free_idx);
    }
}
