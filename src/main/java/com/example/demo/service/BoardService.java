package com.example.demo.service;

import com.example.demo.domain.BoardVO;

import java.util.List;

public interface BoardService {
    void insertBoard(BoardVO bvo);

    List<BoardVO> getList();

    BoardVO getDetail(long bno);

    void deleteBoard(long bno);

    void modifyBoard(BoardVO boardVO);
}
