package hong.tabulator.hongTabulator.domain.hongAnswer;

import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerDTO;
import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerUpdateDTO;
import hong.tabulator.hongTabulator.domain.hongAnswer.vo.HongAnswerVO;

import java.util.List;

public interface HongAnswerService {

    Long join(HongAnswerDTO dto);

    void update(Long id, HongAnswerUpdateDTO dto);

    void delete(Long id);

    List<HongAnswerVO> list(Long postId);
}
