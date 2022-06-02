package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String username;
    private String title;
    private String contents;
    private String password;
}
