package com.sparta.springcore.model;

import com.sparta.springcore.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 만듭니다.
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Memo extends com.sparta.springcore.time.Timestamped { // 생성,수정 시간을 자동으로 만들어줍니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long userId;

    public Memo(String username, String title, String contents, String password) {
        this.username = username;
        this.title = title;
        this.contents = contents;
        this.password = password;
    }
    public Memo(com.sparta.springcore.dto.MemoRequestDto requestDto, Long userId) {
        this.userId = userId;
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(com.sparta.springcore.dto.MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
}