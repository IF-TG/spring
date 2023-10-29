package ifTG.travelPlan.api;

import ifTG.travelPlan.service.api.TourApi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class TourApiTest {
    @Autowired
    TourApi tourApi;

    @Test
    public void api호출확인() throws UnsupportedEncodingException {
        /*String response = tourApi.searchKeyword("chicken");
        System.out.println("response = " + response);*/
    }
}
