package com.example.demo.handler;

import com.example.demo.domain.FileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;

@Slf4j
@Component
public class FileRemoveHandler {

    private final String UP_DIR = "D:\\web_java_chc\\_myProject\\_java\\_fileUpload";

    public boolean deleteFile(FileVO fileVO){
        File delFile = new File(UP_DIR, fileVO.getSaveDir());
        boolean isDel = false;

        String deleteFileName = fileVO.getUuid()+"_"+fileVO.getFileName();

        try {
            File deleteFile = new File(delFile, deleteFileName);
            log.info(">>>> delFile >> {}", deleteFile.toString());
            if(deleteFile.exists()){
                log.info("123");
                isDel = deleteFile.delete();
            }
            // 그림 파일 이라면
            if(fileVO.getFileType() == 1){
                String deleteThumbFileName = fileVO.getUuid()+"_th_"+fileVO.getFileName();
                File deleteThumbFile = new File(delFile, deleteThumbFileName);
                log.info(">>>> delThFile >> {}", deleteThumbFile.toString());
                if(deleteThumbFile.exists()){
                    isDel = deleteThumbFile.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isDel;
    }
}
