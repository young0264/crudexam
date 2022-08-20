package com.example.crudexam.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SampleController {

    @RequestMapping("/reply")
    public String sampleThymeleaf(Model model) {
        String text = "타임리프에 전달되는 데이터 입니다.";
        model.addAttribute("text", text);
        return "/board/test_thyme";
    }


    @ResponseBody
    @RequestMapping("/add/reply")
    public String replyReceive(@RequestParam(value="id") String id, @RequestParam(value = "content") String content) {
        log.info("id={}", id);
        log.info("content={}", content);
        String returnValue = "의영님 " + content;
        return returnValue;
    }
}
