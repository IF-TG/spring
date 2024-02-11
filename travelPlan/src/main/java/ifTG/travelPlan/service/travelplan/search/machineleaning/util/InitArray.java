package ifTG.travelPlan.service.travelplan.search.machineleaning.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InitArray{

    public static double[][] initArrayToRandom(int row, int col) {
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
