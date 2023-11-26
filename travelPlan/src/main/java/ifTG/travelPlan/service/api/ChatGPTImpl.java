package ifTG.travelPlan.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifTG.travelPlan.service.api.dto.gpt.request.*;
import ifTG.travelPlan.service.api.dto.gpt.response.ResponseArgument;
import ifTG.travelPlan.service.api.dto.gpt.response.ResponseGPTDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGPTImpl implements ChatGPT{
    @Value("${api.chat_gpt.url.base_url}")
    private String chatGPTBaseUrl;
    @Value("${api.chat_gpt.url.chat_completions}")
    private String chatCompletionUrl;
    @Value("${api.chat_gpt.secure_key}")
    private String secureKey;

    @Value("${api.chat_gpt.destination.command}")
    private String command;

    @Value("${api.chat_gpt.destination.function}")
    private String function;

    private final RestTemplate restTemplate;

    @Override
    @Async
    public CompletableFuture<List<String>> findRelatedKeywords(String search){
        log.info("search keyword = {}", search);
        String uri = chatGPTBaseUrl + chatCompletionUrl;
        HttpHeaders httpHeaders = getHttpHeaders();
        ChatGPTRequestDto gpt = getHttpBody(search);
        HttpEntity<?> httpEntity = new HttpEntity<>(gpt, httpHeaders);
        return CompletableFuture.completedFuture(getKeywords(uri,httpEntity));
    }


    private ChatGPTRequestDto getHttpBody(String search) {
        List<GPTMessage> messages = getGptMessages(search, command);
        GPTCommendDto gptCommendDto = getGptCommendDto();
        List<GptFunction> gptFunctions = getGptFunctions(gptCommendDto);
        return ChatGPTRequestDto.builder()
                .messages(messages)
                .functions(gptFunctions)
                .function_call("desired_search_keywords")
                .build();
    }

    private List<String> getKeywords(String uri, HttpEntity<?> httpEntity) {
        ResponseGPTDto resultDto = restTemplate.postForObject(uri, httpEntity, ResponseGPTDto.class);

        List<String> keywords = new ArrayList<>();
        assert resultDto != null;
        resultDto.getChoices().forEach(
                choice->
                {
                    try {
                        keywords.addAll(new ObjectMapper().readValue(choice.getMessage().getFunction_call().getArguments(), ResponseArgument.class).getKeywords());
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return keywords;
    }

    private List<GptFunction> getGptFunctions(GPTCommendDto gptCommendDto) {
        List<GptFunction> gptFunctions = new ArrayList<>();
        gptFunctions.add(new GptFunction("desired_search_keywords", function, new GPTParameter("object", gptCommendDto)));
        return gptFunctions;
    }

    private static GPTCommendDto getGptCommendDto() {
        GptItem gptItem = new GptItem("string");
        GptKeyword gptKeyword = new GptKeyword("array", gptItem);
        return new GPTCommendDto(gptKeyword);
    }

    private static List<GPTMessage> getGptMessages(String search, String systemMessage) {
        List<GPTMessage> messages = new ArrayList<>();
        messages.add(new GPTMessage("system", systemMessage));
        messages.add(new GPTMessage("user", search));
        return messages;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + secureKey);
        return httpHeaders;
    }
}
