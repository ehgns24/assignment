package com.sparta.springcore.controller;

import com.sparta.springcore.model.Memo;
import com.sparta.springcore.repository.MemoRepository;
import com.sparta.springcore.dto.MemoRequestDto;
import com.sparta.springcore.security.UserDetailsImpl;
import com.sparta.springcore.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {
    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //로그인 되어 있는 회원의 테이블의 ID
        Long userId = userDetails.getUser().getId();

        Memo memo = new Memo(requestDto, userId);
        return memoRepository.save(memo);
    }
    
    @DeleteMapping("api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id){
        memoRepository.deleteById(id);
        return id;
    }
    @PutMapping("api/memos/{id}")
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoRequestDto requestDto){
        memoService.update(id,requestDto);
        return id;
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUser().getId();

        return memoService.getMemos(userId);
    }
}