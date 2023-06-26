package ifTG.travelPlan.controller;

import ifTG.travelPlan.service.api.TourApi;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
public class test {
    @Autowired
    TourApi tourApi;

    @GetMapping("/1")
    public String s(@RequestParam("keyword") String keyword, @RequestParam("page") int page) throws UnsupportedEncodingException {
        return tourApi.searchKeyword(keyword, page);
    }

}
