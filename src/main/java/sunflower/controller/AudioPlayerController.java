package sunflower.controller;

import org.springframework.web.bind.annotation.*;
import sunflower.service.AudioPlayerService;

import java.util.List;

@RestController
public class AudioPlayerController {
    private AudioPlayerService audioPlayerService;

    public AudioPlayerController(AudioPlayerService audioPlayerService) {
        this.audioPlayerService = audioPlayerService;
    }

    @PostMapping("/audio")
    public void download(@RequestBody List<String> words) {
        audioPlayerService.downloadAudio(words);
    }

    @GetMapping("/audio/{word}")
    public byte[] download(@PathVariable("word") String word) {
        return audioPlayerService.getAudio(word);
    }
}
