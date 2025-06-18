package com.example.demo.service;

import com.example.demo.domain.BoardVO;
import com.example.demo.repository.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceimpl implements BoardService{
    private final BoardMapper boardMapper;

    @Override
    public void insertBoard(BoardVO bvo) {
        boardMapper.insertBoard(bvo);
    }

    @Override
    public List<BoardVO> getList() {
        return boardMapper.getList();
    }

    @Override
    public BoardVO getDetail(long bno) {
        return boardMapper.getDetail(bno);
    }

    @Override
    public void deleteBoard(long bno) {
        boardMapper.deleteBoard(bno);
    }

    @Override
    public void modifyBoard(BoardVO boardVO) {
        boardMapper.modifyBoard(boardVO);

    }
}
