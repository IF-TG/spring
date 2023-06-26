package ifTG.travelPlan.service.api;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


public interface TourApi {
    public String searchKeyword(String keyword, int page) throws UnsupportedEncodingException;
}
