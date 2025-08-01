package com.example.demo.service;

import com.example.demo.domain.CommentVO;

import java.util.List;

public interface CommentService {
    int insertComment(CommentVO commentVO);

    List<CommentVO> getCommentList(long bno);

    int deleteComment(long cno);

    int updateComment(long cno, String content);
}
