package com.example.demo.repository;

import com.example.demo.domain.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardVO bvo);

    List<BoardVO> getList();

    BoardVO getDetail(long bno);

    void deleteBoard(long bno);

    void modifyBoard(BoardVO boardVO);
}
