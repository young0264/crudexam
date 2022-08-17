package com.example.crudexam.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter //테스트용

@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {
    private String title;
    private String content;
}
