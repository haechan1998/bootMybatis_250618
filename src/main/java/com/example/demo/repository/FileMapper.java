package com.example.demo.repository;

import com.example.demo.domain.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFile(FileVO fvo);

    List<FileVO> getFileList(long bno);

    FileVO getFile(String uuid);

    int fileDelete(String uuid);
}
