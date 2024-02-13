package hello.login.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        log.info("save: member = {}", member);
        return member;
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return store.values().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public void clear() {
        store.clear();
    }
}
