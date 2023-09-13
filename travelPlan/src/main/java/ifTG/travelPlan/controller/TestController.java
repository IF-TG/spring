package ifTG.travelPlan.controller;

import ifTG.travelPlan.service.api.ChatGPT;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final ChatGPT chatGPT;
    @PostMapping
    public List<String> test(@RequestParam String keyword){
        return chatGPT.findRelatedKeywords(keyword);
    }
}
