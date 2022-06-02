package com.sparta.springcore.service;

import com.sparta.springcore.model.Memo;
import com.sparta.springcore.repository.MemoRepository;
import com.sparta.springcore.dto.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public Memo createMemo(MemoRequestDto requestDto, Long userId){

        Memo memo = new Memo(requestDto ,userId);

        memoRepository.save(memo);

        return memo;
    }
    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);
        return memo.getId();
    }
    //조회
    public List<Memo> getMemos(Long userId){
        return memoRepository.findAllByUserId(userId);
    }
}
