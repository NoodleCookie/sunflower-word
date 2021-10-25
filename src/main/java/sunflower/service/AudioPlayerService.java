package sunflower.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sunflower.configuration.UserContext;
import sunflower.entity.WordAudio;
import sunflower.repository.WordAudioRepository;

import java.io.*;
import java.util.List;
import java.util.Set;

@Service
@ConfigurationProperties(prefix = "word")
@Data
public class AudioPlayerService {

    private RestTemplate restTemplate = new RestTemplate();

    private String translateUrl = "";

    private WordAudioRepository wordAudioRepository;

    public AudioPlayerService(WordAudioRepository wordAudioRepository) {
        this.wordAudioRepository = wordAudioRepository;
    }

    public byte[] getAudio(String word) {
        return wordAudioRepository.findById(word).orElseThrow(() -> new RuntimeException("no such word")).getAudio();
    }

    public void downloadAudio(List<String> words) {
        File file = new File("word-mp3");
        if (!file.exists()) {
            file.mkdir();
        }
        Set<String> allKey = wordAudioRepository.getAllKey();

        for (String word : words) {
            if (!allKey.contains(word)) {
                byte[] audio = restTemplate.getForObject(translateUrl + word, byte[].class);
                WordAudio wordAudio = WordAudio.builder().word(word).audio(audio).build();
                wordAudio.setCreatedBy(UserContext.getUser());
                wordAudioRepository.save(wordAudio);
            }
        }
    }
}
