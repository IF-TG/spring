package ifTG.travelPlan.service.travelplan.search.machineleaning.vector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VectorAverageImpl implements VectorAverage{

    @Override
    public double[] getVectorAverage(List<Map<Integer, Double>> vecList){
        Map<Integer, Double> averageMap = vecList.stream().flatMap(vec->vec.entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingDouble(Map.Entry::getValue)
                ));

        return averageMap.values().stream()
                  .mapToDouble(Double::doubleValue)
                  .toArray();
    }
}
