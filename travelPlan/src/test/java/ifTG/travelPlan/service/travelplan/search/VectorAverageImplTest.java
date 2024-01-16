package ifTG.travelPlan.service.travelplan.search;

import ifTG.travelPlan.service.travelplan.search.machineleaning.VectorAverage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class VectorAverageImplTest {

    private final VectorAverage vectorAverage;

    @Autowired
    public VectorAverageImplTest(VectorAverage vectorAverage) {
        this.vectorAverage = vectorAverage;
    }


    @Test
    void getVectorAverage(){
        //given
        double ans = 0;
        List<Map<Integer, Double>> inputList = new ArrayList<>();
        for (int i =0; i<10; i++){
            Map<Integer, Double> input= new HashMap<>();
            for (int j =0; j<10; j++){
                double tmp = new Random().nextDouble();
                input.put(j, tmp);
                if (j ==0){
                    ans += tmp;
                }
            }
            inputList.add(input);
        }
        //when
        double[] result = vectorAverage.getVectorAverage(inputList);
        //then
        Assertions.assertThat(result[0]).isEqualTo(ans/10);

    }

}