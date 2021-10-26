package sunflower.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class BaiduPicDetectiveDto {

    private List<WordsResult> words_result;

    private String log_id;

    private int words_result_num;

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    @Data
    public static class WordsResult{
        private String words;
    }
}
