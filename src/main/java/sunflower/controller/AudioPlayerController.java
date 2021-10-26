package sunflower.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sunflower.dto.BaiduPicDetectiveDto;
import sunflower.service.MediaService;

import java.io.IOException;
import java.util.List;

@RestController
public class AudioPlayerController {
    private MediaService mediaService;

    public AudioPlayerController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping("/audio")
    public void download(@RequestBody List<String> words) {
        mediaService.downloadAudio(words);
    }

    @GetMapping("/audio/{word}")
    public byte[] download(@PathVariable("word") String word) {
        return mediaService.getAudio(word);
    }


    @PostMapping("/picture")
    public BaiduPicDetectiveDto uploadWordPicture(@RequestPart("file") MultipartFile file) throws IOException {
        return mediaService.getWordsFromPicture(file.getBytes());
    }
}
