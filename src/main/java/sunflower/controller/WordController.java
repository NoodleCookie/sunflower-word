package sunflower.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sunflower.entity.WordCard;
import sunflower.service.WordService;

import java.util.List;

@RestController
public class WordController {

    private WordService wordService;

    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/word")
    public WordCard save(@RequestBody WordCard wordCard) {
        return wordService.save(wordCard);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/word/all")
    public List<WordCard> select() {
        return wordService.select();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/word/eng/{eng}")
    public List<WordCard> selectEngLike(@PathVariable("eng") String eng) {
        return wordService.selectByEng(eng);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/word/chi/{chi}")
    public List<WordCard> selectChiLike(@PathVariable("chi") String chi) {
        return wordService.selectByChi(chi);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/word/all/{target}")
    public List<WordCard> selectChiOrEngLike(@PathVariable("target") String target) {
        return wordService.selectByChiOrEng(target);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/word/{id}")
    public void delete(@PathVariable("id") long id) {
        wordService.delete(id);
    }

}
