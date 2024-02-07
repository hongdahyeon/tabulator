package hong.tabulator.hongTabulator.domain.hongAnswer.vo;


import hong.tabulator.hongTabulator.domain.hongAnswer.HongAnswer;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongAnswerVO {

    private Long id;
    private String content;

    public HongAnswerVO(HongAnswer hongAnswer) {
        this.id = hongAnswer.getId();
        this.content = hongAnswer.getContent();
    }
}
