package sunflower.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import sunflower.configuration.BaiduConfiguration;
import sunflower.configuration.UserContext;
import sunflower.dto.BaiduPicDetectiveDto;
import sunflower.entity.WordAudio;
import sunflower.repository.WordAudioRepository;
import sunflower.utils.Base64Util;
import sunflower.utils.HttpUtil;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Set;

@Service
@ConfigurationProperties(prefix = "word")
@Data
public class MediaService {

    private RestTemplate restTemplate = new RestTemplate();

    private String translateUrl = "";
    private String pictureDetective = "";
    private String accessToken = "";

    private WordAudioRepository wordAudioRepository;

    private BaiduConfiguration baiduConfiguration;

    public MediaService(WordAudioRepository wordAudioRepository, BaiduConfiguration baiduConfiguration) {
        this.wordAudioRepository = wordAudioRepository;
        this.baiduConfiguration = baiduConfiguration;
    }

    private String getAccessTokenFromBaidu() {
        return AuthService.getAuth();
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
                System.out.println(word+":"+audio.length);
                WordAudio wordAudio = WordAudio.builder().word(word).audio(audio).build();
                wordAudio.setCreatedBy(UserContext.getUser());
                wordAudioRepository.save(wordAudio);
            }
        }
    }

    public BaiduPicDetectiveDto getWordsFromPicture(byte[] imgData) {
        try {
            // 本地文件路径
//            String filePath = "[本地文件路径]";
//            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode("data:image/jpg;base64,"+imgStr, "UTF-8");

            String param = "image=" + imgParam;

            String result = HttpUtil.post(pictureDetective, accessToken, param);
            System.out.println(result);
            return new ObjectMapper().readValue(result, BaiduPicDetectiveDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public void uploadCustomAudio(String name,MultipartFile file){
        WordAudio build = WordAudio.builder().audio(file.getBytes()).word(name).build();
        build.setCreatedBy(UserContext.getUser());
        wordAudioRepository.save(build);
    }
}
