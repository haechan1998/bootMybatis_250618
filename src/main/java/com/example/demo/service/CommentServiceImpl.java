package com.example.demo.service;

import com.example.demo.domain.CommentVO;
import com.example.demo.repository.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int insertComment(CommentVO commentVO) {
        return commentMapper.insertComment(commentVO);
    }

    @Override
    public List<CommentVO> getCommentList(long bno) {
        return commentMapper.getCommentList(bno);
    }

    @Override
    public int deleteComment(long cno) {
        return commentMapper.deleteComment(cno);
    }

    @Override
    public int updateComment(long cno, String content) {
        return commentMapper.updateComment(cno, content);
    }
}
