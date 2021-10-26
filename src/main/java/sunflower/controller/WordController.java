package sunflower.controller;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sunflower.entity.WordCard;
import sunflower.service.GoogleTranslatorService;
import sunflower.service.WordService;

import java.util.List;

@RestController
public class WordController {

    private WordService wordService;
    private GoogleTranslatorService googleTranslatorService;

    public WordController(WordService wordService, GoogleTranslatorService googleTranslatorService) {
        this.wordService = wordService;
        this.googleTranslatorService = googleTranslatorService;
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
    public List<WordCard> select(@PathVariable("eng") String word) {
        return wordService.findByWord(word);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/word/ch/{ch}")
    public List<WordCard> selectByCh(@PathVariable("ch") String ch) {
        return wordService.findByCh(ch);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/word/trash")
    public List<WordCard> trashWord() {
        return wordService.findDeletedWord();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/word/{id}")
    public void delete(@PathVariable("id") long id) {
        wordService.delete(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/word/logic/{id}")
    public void logicDelete(@PathVariable("id") long id) {
        wordService.logicDelete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/word/collect/{id}")
    public void collectWord(@PathVariable("id") long id) {
        wordService.collectWord(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/word")
    public void update(@RequestBody WordCard wordCard) {
        wordService.update(wordCard);
    }

    @SneakyThrows
    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/word/translate/{eng}")
    public String translate(@PathVariable("eng") String word) {
        return googleTranslatorService.translate("en", "zh-CN", word);
    }
}
