package hong.tabulator.hongTabulator.domain.hongAnswer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HongAnswerRepository extends JpaRepository<HongAnswer, Long> {

    List<HongAnswer> findAllByHongPostIdAndDelYnIs(Long postId, String delYn);
}
