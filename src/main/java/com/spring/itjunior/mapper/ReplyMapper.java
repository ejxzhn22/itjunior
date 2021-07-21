package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.Reply;
import com.spring.itjunior.domain.ReplyLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    //댓글 모두 가져오기
    public List<Reply> selectAll(int free_idx);

    //댓글 하나 가져오기
    public Reply selectOne(int reply_idx);

    //마지막 insert 대댓글 가져오기
    public Reply lastOne();

    //부모 댓글 추가
    public int insertParent(Reply reply);

    //대댓글 추가
    public int insertChild(Reply reply);

    //댓글 갯수
    public int replycnt(int free_idx);

    //부모댓글 삭제
    public int deleteParent(int reply_idx);

    //대댓글 삭제
    public int deleteChild(int reply_idx);

    //댓글 좋아요
    public int likeParent(ReplyLike replyLike);

    //댓글 좋아요취소하기
    public int likeParentDelete(ReplyLike replyLike);

    //댓글 싫어요
    public int unlikeParent(ReplyLike replyLike);

    //댓글 싫어요취소하기
    public int unlikeParentDelete(ReplyLike replyLike);

    //좋아요한 댓글 리스트
    public List<Integer> likeList(int member_idx);

    //싫어요한 댓글 리스트
    public List<Integer> unlikeList(int member_idx);

    //댓글 좋아요 갯수 가져오기
    public int likeCount(int reply_idx);

    //댓글 싫어요 갯수 가져오기
    public int unlikeCount(int reply_idx);
}
