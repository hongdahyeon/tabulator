package hong.tabulator.hongTabulator.domain.hongPost.vo;


import hong.tabulator.hongTabulator.domain.Address;
import hong.tabulator.hongTabulator.domain.hongPost.HongPost;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HongPostWithAddressVO {

    private Long id;
    private String title;
    private String content;
    private String delYn;
    private Address address;

    public HongPostWithAddressVO(HongPost hongPost) {
        this.id = hongPost.getId();
        this.title = hongPost.getTitle();
        this.content = hongPost.getContent();
        this.delYn = hongPost.getDelYn();
        this.address = new Address("city"+hongPost.getId(), "street"+hongPost.getId(), "zipcode"+hongPost.getId() );
    }
}