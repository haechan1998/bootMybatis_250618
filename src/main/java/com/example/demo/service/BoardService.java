package com.example.demo.service;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.PagingVO;

import java.util.List;

public interface BoardService {
    void insertBoard(BoardDTO boardDTO);

    List<BoardVO> getList(PagingVO pagingVO);

    BoardDTO getDetail(long bno);

    void deleteBoard(long bno);

    void modifyBoard(BoardDTO boardDTO);

    int getTotalCount(PagingVO pagingVO);

    FileVO getFile(String uuid);

    int fileDelete(String uuid);
}
