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




}
