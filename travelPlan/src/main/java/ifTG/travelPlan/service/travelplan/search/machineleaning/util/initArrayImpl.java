package ifTG.travelPlan.service.travelplan.search.machineleaning.util;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class initArrayImpl implements InitArray{

    @Override
    public double[][] initArrayToRandom(int row, int col) {
        double[][] array = new double[row][col];
        Random random  = new Random();
        for (int i = 0; i<array.length; i++){
            for (int j = 0; j<array[i].length; j++){
                array[i][j] = -0.5 + random.nextDouble();
            }
        }
        return array;
    }
}
