package hong.tabulator.hongTabulator.domain.hongAnswer;

import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerDTO;
import hong.tabulator.hongTabulator.domain.hongAnswer.dto.HongAnswerUpdateDTO;
import hong.tabulator.hongTabulator.domain.hongAnswer.vo.HongAnswerVO;
import hong.tabulator.hongTabulator.global.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HongAnswerRestController {

    private final HongAnswerService service;

    @PostMapping("/answer")
    public Response join(@RequestBody HongAnswerDTO dto){
        Long joinId = service.join(dto);
        return Response.ok(joinId);
    }

    @PutMapping("/answer/{id}")
    public Response update(@PathVariable(name = "id") Long id, @RequestBody HongAnswerUpdateDTO dto) {
        service.update(id, dto);
        return Response.ok("");
    }

    @DeleteMapping("/answer/{id}")
    public Response delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return Response.ok("");
    }

    @GetMapping("/answer-list/{id}")
    public Response list(@PathVariable(name = "id") Long id) {
        List<HongAnswerVO> list = service.list(id);
        return Response.ok(list);
    }
}
