package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.user.SearchHistory;
import ifTG.travelPlan.domain.user.Sex;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.domain.user.UserAddress;
import ifTG.travelPlan.dto.travel.enums.Category;
import ifTG.travelPlan.dto.travel.enums.LargeCategory;
import ifTG.travelPlan.dto.travel.enums.MiddleCategory;
import ifTG.travelPlan.dto.travel.enums.SmallCategory;
import ifTG.travelPlan.elasticsearch.domain.EDestination;
import ifTG.travelPlan.elasticsearch.dto.ResponseEDestinationDto;
import ifTG.travelPlan.elasticsearch.repository.EDestinationCustomRepository;
import ifTG.travelPlan.repository.springdata.travel.DestinationScrapRepository;
import ifTG.travelPlan.repository.springdata.user.SearchHistoryRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.service.api.ChatGPT;
import ifTG.travelPlan.service.api.ChatGPTImpl;
import ifTG.travelPlan.service.api.dto.ContentType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
class DestinationSearchServiceImplTest {
    @Mock
    private EDestinationCustomRepository eDestinationCustomRepository;

    @InjectMocks
    private DestinationSearchServiceImpl destinationSearchService;

    @Mock
    private DestinationScrapRepository destinationScrapRepository;

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ChatGPT chatGPT;

    @Test
    void findAllByKeyword() {
        //given
        Long userId = 1L;
        String keyword = "test";
        Pageable pageable = PageRequest.of(0,10);

        List<EDestination> responseMock = getResponseEDestinationMocks(keyword);
        List<String> keywordByGPT = List.of(keyword);
        //when
        when(chatGPT.findRelatedKeywords(anyString())).thenReturn(CompletableFuture.completedFuture(keywordByGPT));
        when(eDestinationCustomRepository.findAllByUserKeywordAndGPTKeywordList(anyString(), anyList(), any(Pageable.class))).thenReturn(responseMock);
        when(destinationScrapRepository.findAllWithDestinationByUserId(userId)
                                       .stream().map(ds->ds.getDestination().getId()).toList()).thenReturn(List.of(1L));
        List<ResponseEDestinationDto> result = destinationSearchService.findAllByKeyword(userId, keyword, pageable);
        List<Long> resultIdList = result.stream().map(ResponseEDestinationDto::getId).toList();
        //then
        verify(searchHistoryRepository, atMostOnce()).save(any(SearchHistory.class));
        assertThat(resultIdList).contains(1L, 2L, 3L, 4L);
    }

    private static List<EDestination> getResponseEDestinationMocks(String keyword) {
        return Arrays.asList(
                EDestination.builder()
                        .id(1L)
                        .title("제목")
                        .keywordList(List.of("keyword"))
                        .thumbnailUrl("썸네일")
                        .info("대충 내용")
                        .blindInfo("대충 숨겨진 내용")
                        .address("주소")
                        .category(new Category(LargeCategory.A01, MiddleCategory.A0101, SmallCategory.A01010100))
                        .contentType(ContentType.Cultural_Facility)
                        .build(),
                EDestination.builder()
                            .id(2L)
                            .title(keyword)
                            .keywordList(null)
                            .thumbnailUrl("썸네일")
                            .info("대충 내용")
                            .blindInfo("대충 숨겨진 내용")
                            .address("주소")
                            .category(new Category(LargeCategory.A01, MiddleCategory.A0101, SmallCategory.A01010100))
                            .contentType(ContentType.Cultural_Facility)
                            .build(),
                EDestination.builder()
                            .id(3L)
                            .title("제목")
                            .keywordList(null)
                            .thumbnailUrl("썸네일")
                            .info("대충 내용")
                            .blindInfo("대충 숨겨진 내용")
                            .address(keyword)
                            .category(new Category(LargeCategory.A01, MiddleCategory.A0101, SmallCategory.A01010100))
                            .contentType(ContentType.Cultural_Facility)
                            .build(),
                EDestination.builder()
                            .id(4L)
                            .title("제목")
                            .keywordList(null)
                            .thumbnailUrl("썸네일")
                            .info("대충 내용")
                            .blindInfo("대충 숨겨진 내용")
                            .address("주소")
                            .category(new Category(LargeCategory.A01, MiddleCategory.A0101, SmallCategory.A01010100))
                            .contentType(ContentType.Cultural_Facility)
                            .build()
        );
    }
}