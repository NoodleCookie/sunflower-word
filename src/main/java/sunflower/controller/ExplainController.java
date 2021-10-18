package sunflower.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sunflower.entity.Explain;
import sunflower.entity.WordCard;
import sunflower.service.ExplainService;
import sunflower.service.WordService;

import java.util.List;

@RestController
public class ExplainController {

    private ExplainService explainService;

    public ExplainController(ExplainService explainService) {
        this.explainService = explainService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/explain/{id}")
    public WordCard insert(@PathVariable("id") long id, @RequestBody Explain explain) {
        return explainService.insert(id, explain);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/explain/{id}")
    public void delete(@PathVariable("id") long id) {
        explainService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/explain")
    public Integer update(@RequestBody Explain explain) {
        return explainService.update(explain);
    }
}