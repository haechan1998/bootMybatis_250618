package com.example.demo.controller;

import com.example.demo.domain.CommentVO;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/comment/*")
@Slf4j
@RestController
public class CommentController {

    private final CommentService commentService;


    @PostMapping("/post")
    public ResponseEntity<String> insertComment(@RequestBody CommentVO commentVO){

        log.info("> commentVO {}", commentVO);
        int isOk = commentService.insertComment(commentVO);

        return isOk > 0
                ? ResponseEntity.ok("ok")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");

    }
    @GetMapping("/{bno}")
    public ResponseEntity<List<CommentVO>> getCommentList(@PathVariable("bno") long bno){

        List<CommentVO> list = commentService.getCommentList(bno);
        log.info(">> list >{}",list);

        return ResponseEntity.ok(list);
    }

    // HTTP GET 방식으로 쿼리 파라미터를 받는 방식
    @DeleteMapping("/comment/delete")
    public ResponseEntity<String> deleteComment(@RequestParam("cno") long cno){

        int isOk = commentService.deleteComment(cno);

        return ResponseEntity.ok(isOk > 0 ? "1" : "0");
    }

    @PutMapping("/comment/updateComment")
    public ResponseEntity<String> updateComment(@RequestBody CommentVO commentVO){

        long cno = commentVO.getCno();
        String content = commentVO.getContent();
        // 수정하기 귀찮으니 그냥 get 으로 값들을 가져와서 세팅해주자...

        int isOk = commentService.updateComment(cno, content);

        return ResponseEntity.ok(isOk > 0 ? "1" : "0");
    }






}
