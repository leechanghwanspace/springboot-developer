package me.leechanghwan.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //JpaRepository를 상속받을 때, 엔티티 Member와 엔티티의 기본키 타임 Long을 인수로 넣어줌
}