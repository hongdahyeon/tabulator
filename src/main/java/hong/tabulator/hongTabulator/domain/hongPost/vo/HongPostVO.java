package hong.tabulator.hongTabulator.domain.hongPost.vo;

import hong.tabulator.hongTabulator.domain.hongPost.HongPost;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongPostVO {

    private Long id;
    private String title;
    private String content;
    private String delYn;

    public HongPostVO(HongPost hongPost) {
        this.id = hongPost.getId();
        this.title = hongPost.getTitle();
        this.content = hongPost.getContent();
        this.delYn = hongPost.getDelYn();
    }
}