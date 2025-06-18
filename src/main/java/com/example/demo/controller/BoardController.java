package com.example.demo.controller;

import com.example.demo.domain.BoardVO;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/board/*")
@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService bsv;

    @GetMapping("/register")
    public void register(){}

    @PostMapping("/register")
    public String insert(BoardVO bvo){
        log.info(">> bvo > {}",bvo);
        bsv.insertBoard(bvo);

        return "redirect:/";
    }

    @GetMapping("/list")
    public void list(Model model){
        List<BoardVO> list =  bsv.getList();

        model.addAttribute("list", list);
    }

    @GetMapping("/detail")
    public void detail(@RequestParam("bno") long bno, Model model){

        BoardVO bvo = bsv.getDetail(bno);
        model.addAttribute("bvo", bvo);

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bno") long bno){

        bsv.deleteBoard(bno);

        return "redirect:/";
    }

    @PostMapping("/modify")
    public String modify(BoardVO boardVO, RedirectAttributes redirectAttributes){
        log.info(">> boardVO > {}", boardVO);
        bsv.modifyBoard(boardVO);
        redirectAttributes.addAttribute("bno", boardVO.getBno());
        return "redirect:/board/detail";
    }

}
