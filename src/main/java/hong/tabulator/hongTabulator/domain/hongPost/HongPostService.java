package hong.tabulator.hongTabulator.domain.hongPost;


import hong.tabulator.hongTabulator.domain.hongPost.dto.HongPostDTO;
import hong.tabulator.hongTabulator.domain.hongPost.vo.HongPostVO;
import hong.tabulator.hongTabulator.domain.hongPost.vo.HongPostWithAddressVO;
import hong.tabulator.hongTabulator.domain.hongPost.vo.HongPostWithAnswerVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface HongPostService {

    Long join(HongPostDTO dto);

    void delete(Long id);

    Integer deleteSeveral(List<Long> ids);

    List<HongPostVO> list();

    List<HongPostWithAnswerVO> listWithAnswer();

    List<HongPostWithAddressVO> listWithAddress();

    HongPostVO view(Long id);

    HongPostWithAnswerVO viewWithAnswer(Long id);

    void edit(Long id, HongPostDTO dto);

    Map<String, Object> uploadCKImageFile(MultipartFile multipartFile);
}