package com.example.demo.service;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.BoardVO;
import com.example.demo.domain.FileVO;
import com.example.demo.domain.PagingVO;
import com.example.demo.repository.BoardMapper;
import com.example.demo.repository.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceimpl implements BoardService{
    private final BoardMapper boardMapper;
    private final FileMapper fileMapper;

    @Transactional
    @Override
    public void insertBoard(BoardDTO boardDTO) {
        boardMapper.insertBoard(boardDTO.getBoardVO());
        if(boardDTO.getFileList() == null){
            return;
        }
        if(boardDTO.getFileList().size() > 0){
            // 파일 저장
            long bno = boardMapper.getBno();
            for(FileVO fvo : boardDTO.getFileList()){
                fvo.setBno(bno);
                fileMapper.insertFile(fvo);
            }

        }

    }

    @Override
    public List<BoardVO> getList(PagingVO pagingVO) {
        return boardMapper.getList(pagingVO);
    }

    @Override
    public BoardDTO getDetail(long bno) {
        BoardDTO boardDTO = new BoardDTO(boardMapper.getDetail(bno), fileMapper.getFileList(bno));
        return boardDTO;
    }

    @Override
    public void deleteBoard(long bno) {
        boardMapper.deleteBoard(bno);
    }

    @Override
    public void modifyBoard(BoardVO boardVO) {
        boardMapper.modifyBoard(boardVO);
    }

    @Override
    public int getTotalCount(PagingVO pagingVO) {
        return boardMapper.getTotalCount(pagingVO);
    }

    @Override
    public FileVO getFile(String uuid) {
        return fileMapper.getFile(uuid);
    }

    @Override
    public int fileDelete(String uuid) {
        return fileMapper.fileDelete(uuid);
    }


}
