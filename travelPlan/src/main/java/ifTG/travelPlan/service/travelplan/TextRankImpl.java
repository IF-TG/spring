package ifTG.travelPlan.service.travelplan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class TextRankImpl implements TextRank{
    private final Morpheme morpheme;
    private final Word2Vec word2Vec;
    private final int windowSize = 2;
    @Override
    public List<String> textRank(String text){
        List<String> noneByText = new ArrayList<>(morpheme.getNounByString(text));
        Map<String, Integer> wordIdxMap = new HashMap<>();
        int count = 0;
        for (String s : noneByText) {
            if (!wordIdxMap.containsKey(s)) {
                wordIdxMap.put(s, count);
                count++;
            }
        }

        int size = wordIdxMap.size();
        double[][] link = new double[size][size];
        double[] point = new double[size];
        Arrays.fill(point, 1);
        for (int i = 0; i<noneByText.size()-windowSize; i++){
            int startWindow = Math.max(0, i-windowSize);
            int endWindow = Math.min(noneByText.size(), i+windowSize+1);
            int windowIdx = switch (i) {
                case 0 -> 0;
                case 1 -> 1;
                default -> startWindow+windowSize;
            };
            String standWord = noneByText.get(windowIdx);
            for (int j = startWindow; j<endWindow; j++){
                if (j == windowIdx)continue;
                String linkedWord = noneByText.get(j);
                double weight = word2Vec.getScore(standWord, linkedWord);
                link[wordIdxMap.get(standWord)][wordIdxMap.get(linkedWord)] += 1;
                link[wordIdxMap.get(linkedWord)][wordIdxMap.get(standWord)] += 1;
            }
        }

        for (int i =0; i<size; i++){
            for (int j =0;j<size;j++){
                int idxA = i;
                int idxB = j;
                String s1 = wordIdxMap.keySet().stream().filter(k->wordIdxMap.get(k)==idxA).findFirst().get();
                String s2 = wordIdxMap.keySet().stream().filter(k->wordIdxMap.get(k)==idxB).findFirst().get();
                link[i][j] *= word2Vec.getScore(s1, s2);
            }
        }


        double threshold = 1;
        while(0.0001<threshold){
            threshold = 0;
            for (int i =0; i<size;i++){
                double score = 0;
                for (int j = 0; j<size; j++){
                    if (link[i][j]==0)continue;
                    double linkedNodeWeightSum = 0;
                    for (int k = 0; k<size; k++){
                        linkedNodeWeightSum += link[j][k];
                    }
                    score += link[i][j]/linkedNodeWeightSum*point[j];
                }
                threshold += (point[i]-score)*(point[i]-score);
                point[i] = score;
            }
            threshold = Math.sqrt(threshold);
        }
        Integer[] idxList = IntStream.range(0, point.length)
                .boxed()
                .sorted(Comparator.comparingDouble(idx -> -point[idx]))
                .toArray(Integer[]::new);
        List<String> result  = new ArrayList<>();
        for (int i =0; i<10&&i<idxList.length; i++){
            int idx = i;
            result.add(wordIdxMap.keySet().stream().filter(k->wordIdxMap.get(k).equals(idxList[idx])).findFirst().orElseThrow(()->new NoSuchElementException("NoSuchElementException")));
        }
        return result;
    }


}
