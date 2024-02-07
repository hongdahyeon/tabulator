package hong.tabulator.hongTabulator.domain.hongPost.vo;

import hong.tabulator.hongTabulator.domain.hongAnswer.vo.HongAnswerVO;
import hong.tabulator.hongTabulator.domain.hongPost.HongPost;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HongPostWithAnswerVO {

    private Long id;
    private String title;
    private String content;
    private List<HongAnswerVO> answers = new ArrayList<>();

    public HongPostWithAnswerVO(HongPost hongPost, List<HongAnswerVO> answers) {
        this.id = hongPost.getId();
        this.title = hongPost.getTitle();
        this.content = hongPost.getContent();
        this.answers = answers;
    }
}
