package com.example.demo.repository;

import com.example.demo.domain.CommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int insertComment(CommentVO commentVO);

    List<CommentVO> getCommentList(long bno);
}
