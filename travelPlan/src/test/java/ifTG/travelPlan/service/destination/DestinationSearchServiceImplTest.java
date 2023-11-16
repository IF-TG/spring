package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.dto.travel.enums.LargeCategory;
import ifTG.travelPlan.dto.travel.enums.MiddleCategory;
import ifTG.travelPlan.dto.travel.enums.SmallCategory;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
@Slf4j
class DestinationSearchServiceTest {
    @Mock
    private EDestinationCustomRepository eDestinationCustomRepository;

    @InjectMocks
    private DestinationSearchServiceImpl destinationSearchService;

    @Test
    void findAllByKeyword() {
        //given
        Long userId = 1L;
        String keyword = "test";
        Pageable pageable = PageRequest.of(0,10);

        List<ResponseEDestinationDto> responseMock = getResponseEDestinationDtoMocks(keyword);

        //when
        when(destinationSearchService.findAllByKeyword(userId, keyword, pageable)).thenReturn(responseMock);
        //then
    }

    private static List<ResponseEDestinationDto> getResponseEDestinationDtoMocks(String keyword) {
        List<ResponseEDestinationDto> responseMock = Arrays.asList(
                ResponseEDestinationDto.builder()
                        .id(1L)
                        .contentType(ContentType.Cultural_Facility)
                        .title(keyword)
                        .thumbnailUrl("썸네일")
                        .address("주소")
                        .largeCategory("대분류")
                        .middleCategory("중분류")
                        .smallCategory("소분류")
                        .isScraped(false)
                        .isGptRelated(true)
                        .build(),
                ResponseEDestinationDto.builder()
                        .id(1L)
                        .contentType(ContentType.Cultural_Facility)
                        .title("제목")
                        .thumbnailUrl("썸네일")
                        .address(keyword)
                        .largeCategory("대분류")
                        .middleCategory("중분류")
                        .smallCategory("소분류")
                        .isScraped(false)
                        .isGptRelated(true)
                        .build(),
                ResponseEDestinationDto.builder()
                        .id(1L)
                        .contentType(ContentType.Cultural_Facility)
                        .title("제목")
                        .thumbnailUrl("썸네일")
                        .address("주소")
                        .largeCategory("대분류")
                        .middleCategory("중분류")
                        .smallCategory(keyword)
                        .isScraped(false)
                        .isGptRelated(true)
                        .build()
        );
        return responseMock;
    }
}