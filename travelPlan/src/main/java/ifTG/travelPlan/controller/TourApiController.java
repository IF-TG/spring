package ifTG.travelPlan.controller;

import ifTG.travelPlan.service.api.TourApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/tourapi")
@RequiredArgsConstructor
public class TourApiController {
    private final TourApi tourApi;

   /* @GetMapping("searchKeyword")
    public String getSearchKeyword(@RequestParam String keyword, @RequestParam int page) throws UnsupportedEncodingException {
        return tourApi.searchKeyword(keyword, page);
    }*/

  /*  @GetMapping("introIntro")
    public String getIntroductionIntro(@RequestParam Long contentId, @RequestParam int contentTypeId) {
        return tourApi.selectIntroductionIntro(contentId, contentTypeId);
    }*/
}
