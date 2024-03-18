package ifTG.travelPlan.service.travelplan.search.machineleaning.util;

import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.exception.CustomErrorException;

public class Check {
    public static void notNull(Object object, StatusCode statusCode){
        if (object==null){
            throw new CustomErrorException(statusCode);
        }
    }

    public static void is(boolean b, StatusCode statusCode){
        if (b){
            throw new CustomErrorException(statusCode);
        }
    }
}
