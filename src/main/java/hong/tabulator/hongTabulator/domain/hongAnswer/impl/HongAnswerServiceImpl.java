package hong.tabulator.hongTabulator.domain.hongAnswer.impl;

import hong.tabulator.hongTabulator.domain.hongAnswer.HongAnswer;
import hong.tabulator.hongTabulator.domain.hongAnswer.HongAnswerRepository;
import hong.tabulator.hongTabulator.domain.hongAnswer.HongAnswerService;
import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerDTO;
import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerUpdateDTO;
import hong.tabulator.hongTabulator.domain.hongAnswer.vo.HongAnswerVO;
import hong.tabulator.hongTabulator.domain.hongPost.HongPost;
import hong.tabulator.hongTabulator.domain.hongPost.HongPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HongAnswerServiceImpl implements HongAnswerService {

    private final HongAnswerRepository hongAnswerRepository;
    private final HongPostRepository hongPostRepository;

    @Override
    @Transactional(readOnly = false)
    public Long join(HongAnswerDTO dto) {

        Optional<HongPost> hongPostOptional = hongPostRepository.findById(dto.getPostId());

        if(hongPostOptional.isPresent()) {

            HongPost hongPost = hongPostOptional.get();
            HongAnswer hongAnswer = HongAnswer.hongAnswerInsertBuilder()
                    .hongPost(hongPost)
                    .content(dto.getContent())
                    .build();
            HongAnswer save = hongAnswerRepository.save(hongAnswer);
            return save.getId();

        } else throw new IllegalArgumentException("there is no post");
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Long id, HongAnswerUpdateDTO dto) {
        HongAnswer answer = hongAnswerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("there is no answer"));
        answer.updateAnswer(dto);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        HongAnswer answer = hongAnswerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("there is no answer"));
        answer.deleteAnswer();
    }

    @Override
    public List<HongAnswerVO> list(Long postId) {
        List<HongAnswer> list = hongAnswerRepository.findAllByHongPostIdAndDelYnIs(postId, "N");
        return list.stream().map(HongAnswerVO::new).toList();
    }
}
