package ifTG.travelPlan.controller;

import ifTG.travelPlan.service.api.ChatGPT;
import ifTG.travelPlan.service.api.NaverApi;
import ifTG.travelPlan.service.api.dto.naver.NaverBlogApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final NaverApi naverApi;
    @GetMapping
    public NaverBlogApiDto test(@RequestParam String keyword){
        return naverApi.selectBlogInfo(keyword);
    }
}
