package hong.tabulator.hongTabulator.domain.hongAnswer;

import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerUpdateDTO;
import hong.tabulator.hongTabulator.domain.hongPost.HongPost;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hong_answer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HongAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hong_answer_id")
    private Long id;

    private String content;
    private String delYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hong_post_id")
    private HongPost hongPost;

    @Builder(builderMethodName = "hongAnswerInsertBuilder")
    public HongAnswer(HongPost hongPost, String content) {
        this.content = content;
        this.delYn = "N";
        this.hongPost = hongPost;
    }

    public void deleteAnswer(){
        this.delYn = "Y";
    }

    public void updateAnswer(HongAnswerUpdateDTO dto){
        this.content = dto.getContent();
    }
}
