package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {

    private long cno;
    private long bno;
    private String writer;
    private String content;
    private String regDate;

}
