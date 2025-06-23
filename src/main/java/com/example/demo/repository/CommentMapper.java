package com.example.demo.repository;

import com.example.demo.domain.CommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(CommentVO commentVO);

    List<CommentVO> getCommentList(long bno);

    int deleteComment(long cno);

    int updateComment(@Param("cno") long cno, @Param("content") String content);
}
