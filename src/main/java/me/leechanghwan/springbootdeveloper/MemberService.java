package me.leechanghwan.springbootdeveloper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public void test() {
        //1. 생성
        memberRepository.save(new Member(1L, "A"));

        //2. 조회
        Optional<Member> member = memberRepository.findById(1L);    //단건 조회
        List<Member> allMembers = memberRepository.findAll();       //전체 조회

        //3. 삭제
        memberRepository.deleteById(1L);
    }
}
