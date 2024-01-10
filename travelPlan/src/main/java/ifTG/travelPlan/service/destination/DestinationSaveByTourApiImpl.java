package ifTG.travelPlan.service.destination;

import ifTG.travelPlan.domain.travel.Destination;
import ifTG.travelPlan.dto.travel.enums.Category;
import ifTG.travelPlan.dto.travel.enums.LargeCategory;
import ifTG.travelPlan.dto.travel.enums.MiddleCategory;
import ifTG.travelPlan.dto.travel.enums.SmallCategory;
import ifTG.travelPlan.repository.springdata.travel.DestinationRepository;
import ifTG.travelPlan.service.api.TourApi;
import ifTG.travelPlan.service.api.dto.ContentType;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncItem;
import ifTG.travelPlan.service.api.dto.tourapi.areabasedsync.AreaBasedSyncListDto;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DestinationSaveByTourApiImpl implements DestinationSaveByTourApi{
    private final DestinationRepository destinationRepository;
    private final TourApi tourApi;

    @Override
    public List<Destination> save(int page){
        return saveDestinationList(page);
    }

    private List<Destination> saveDestinationList(int page) {
        List<Destination> destinationList = new ArrayList<>();
        AreaBasedSyncListDto areaBasedSyncListDto = tourApi.selectAreaBasedSynList(page);
        areaBasedSyncListDto.getItem().stream()
                .filter(DestinationSaveByTourApiImpl::withoutTravelCourseAndAccommodation)
                .forEach(absi-> destinationList.add(getDestinationByTourApi(absi))

        );
        getDetailCommon(destinationList);
        destinationRepository.saveAll(destinationList);

        return destinationList;
    }

    private static boolean withoutTravelCourseAndAccommodation(AreaBasedSyncItem absi) {
        return !(ContentType.Travel_Course == ContentType.getContentType(absi.getContenttypeid()).orElseThrow(() -> new NoResultException("적합한 ContentTypeId를 찾을 수 없습니다."))
                || ContentType.Accommodation == ContentType.getContentType(absi.getContenttypeid()).orElseThrow(() -> new NoResultException("적합한 ContentTypeId를 찾을 수 없습니다.")));
    }

    private void getDetailCommon(List<Destination> destinationList) {
        destinationList.forEach(d->
                d.insertOverViewAtTourApiDetailCommon(replaceByTag(tourApi.selectDetailCommon(d.getTourApiContentId(), d.getContentType()).getItem().get(0).getOverview()))
        );
    }

    private Destination getDestinationByTourApi(AreaBasedSyncItem absi) {
        return Destination.builder()
                .tourApiContentId(Long.parseLong(absi.getContentid()))
                .contentType(ContentType.getContentType(absi.getContenttypeid()).orElseThrow(() -> new NoResultException("적합한 ContentTypeId를 찾을 수 없습니다.")))
                .address(absi.getAddr1())
                .addressDetail(absi.getAddr2())
                .thumbNail(absi.getFirstimage2())
                .title(absi.getTitle())
                .areaCode(Integer.parseInt(absi.getAreacode()))
                .mapX(absi.getMapx())
                .mapY(absi.getMapy())
                .zipcode(absi.getZipcode())
                .mLevel(absi.getMlevel())
                .tel(replaceByTag(absi.getTel()))
                .category(new Category(
                        LargeCategory.findByCode(absi.getCat1()),
                        MiddleCategory.findByCode(absi.getCat2()),
                        SmallCategory.findByCode(absi.getCat3())))
                .build();
    }

    private String replaceByTag(String input){
        return input.replaceAll("<\\s*/?\\s*br\\s*/?\\s*>", "\n");
    }


}
