package ifTG.travelPlan.service.user;

import ifTG.travelPlan.domain.user.UserVector;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import ifTG.travelPlan.repository.springdata.user.UserVectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserVectorServiceImpl implements UserVectorService{
    private final UserVectorRepository userVectorRepository;
    private final UserRepository userRepository;
    @Value("${nlp.dimension}")
    private Integer dimension;

    @Value("${vector.learnRate}")
    private Double learnRate;

    @Override
    @Transactional
    public UserVector initUserVector(Long userId){
        HashMap<Integer, Double> initVector = new HashMap<>();
        for (int i =0 ;i<dimension; i++)initVector.put(i, 0.1);
        UserVector userVector = UserVector.builder().vector(initVector).user(userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("알 수 없는 사용자"))).build();
        return userVectorRepository.save(userVector);
    }

    @Override
    @Transactional
    public UserVector updateUserVector(UserVector userVector, double[] targetVector){
        if (!(userVector.getVector().size()==dimension&&targetVector.length==dimension))throw new IllegalArgumentException("Not correct Dims");
        Map<Integer, Double> newVector = new HashMap<>();
        for (int i = 0; i < dimension; i++){
            if (userVector.getVector().containsKey(i)){
                newVector.put(i, userVector.getVector().get(i) + learnRate * (targetVector[i] - userVector.getVector().get(i)));
            }else{
                throw new IllegalArgumentException("Not correct Dims");
            }
        }
        userVector.updateVector(newVector);
        return userVectorRepository.save(userVector);
    }
}
