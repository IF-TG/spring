package ifTG.travelPlan.service.crawling;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Deprecated
public class NaverBlogCrawlingImpl implements NaverBlogCrawling {

    private final String WEB_DRIVER_PATH = "C:\\Users\\ImKyeongWan\\Documents\\chromedriver-win64\\chromedriver.exe";

    @Override
    public String getNaverBlogContent(String url) {
        if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {
            System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);
        }
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(chromeOptions);
        List<String> list = new ArrayList<>();

        driver.get(url);    //브라우저에서 url로 이동한다.
        try {
            Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> elements = null;
        try{
            elements = driver.findElements(By.cssSelector("div.se-main-container"));
        }catch(NoSuchElementException e){
            elements = driver.findElements(By.cssSelector("div#content-area"));
        }

        log.info("end = {}", elements);

        /*Document document = null;
        try{
            document = Jsoup.connect(url).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        log.info("{}", document.getAllElements());*/
        return null;
    }
}
