package ifTG.travelPlan.controller;

import ifTG.travelPlan.service.api.ChatGPT;
import ifTG.travelPlan.service.api.NaverApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final NaverApi naverApi;
    @GetMapping
    public String test(@RequestParam String keyword){
        return naverApi.selectBlogInfo(keyword);
    }
}
