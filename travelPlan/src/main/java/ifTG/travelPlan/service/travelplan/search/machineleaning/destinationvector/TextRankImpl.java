package ifTG.travelPlan.service.travelplan.search.machineleaning.destinationvector;

import ifTG.travelPlan.service.travelplan.search.machineleaning.dictionary.Morpheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Component
@Slf4j
@RequiredArgsConstructor
public class TextRankImpl implements TextRank{
    private final Morpheme morpheme;
    private final TextRankWeight textRankWeight;

    @Value("${nlp.textrank.window}")
    private Integer windowSize;
    @Value("${nlp.epsilon}")
    private Double epsilon;

    @Value("${nlp.textrank.d}")
    private Double d;


    @Override
    public List<String> textRank(String text){
        List<String> noneByText = new ArrayList<>(morpheme.getNounByString(text));
        Map<String, Integer> wordIdxMap = getInputTextDictionary(noneByText);
        double[][] link = new double[wordIdxMap.size()][wordIdxMap.size()];
        double[] point = new double[wordIdxMap.size()];
        Arrays.fill(point, 1);
        initLink(noneByText, wordIdxMap, link);
        initWeight(wordIdxMap, link);
        calculating(link, point);
        return getResultKeywordList(wordIdxMap, point);
    }

    private static Map<String, Integer> getInputTextDictionary(List<String> noneByText) {
        Map<String, Integer> wordIdxMap = new HashMap<>();
        int count = 0;
        for (String s : noneByText) {
            if (!wordIdxMap.containsKey(s)) {
                wordIdxMap.put(s, count);
                count++;
            }
        }
        return wordIdxMap;
    }

    private void calculating(double[][] link, double[] point) {
        double threshold = 1;
        int size = point.length;
        while(epsilon<threshold){
            threshold = 0;
            for (int i = 0; i< size; i++){
                double score = 0;
                for (int j = 0; j< size; j++){
                    if (link[j][i]==0)continue;
                    double linkedNodeWeightSum = 0;
                    for (int k = 0; k< size; k++){
                        linkedNodeWeightSum += link[j][k];
                    }
                    score += link[j][i]/linkedNodeWeightSum* point[j];
                }
                double oldPoint = point[i];
                point[i] = (1-d) + d*score;
                threshold += (point[i]-oldPoint)*(point[i]-oldPoint);

            }
            threshold = Math.sqrt(threshold);
        }
    }
    private void initLink(List<String> noneByText, Map<String, Integer> wordIdxMap, double[][] link) {
        for (int i = 0; i< noneByText.size()-windowSize; i++){
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
                link[wordIdxMap.get(standWord)][wordIdxMap.get(linkedWord)] = 1;
                link[wordIdxMap.get(linkedWord)][wordIdxMap.get(standWord)] = 1;
            }
        }
    }
    private void initWeight(Map<String, Integer> wordIdxMap, double[][] link) {
        int inputTextDictionarySize= wordIdxMap.size();
        for (int i = 0; i< inputTextDictionarySize; i++){
            for (int j = 0; j< inputTextDictionarySize; j++){
                if (0==link[i][j])continue;
                int idxA = i;
                int idxB = j;
                String s1 = wordIdxMap.keySet().stream().filter(k-> wordIdxMap.get(k)==idxA).findFirst().orElseThrow(()->new NoSuchElementException("NoSuchElementException"));
                String s2 = wordIdxMap.keySet().stream().filter(k-> wordIdxMap.get(k)==idxB).findFirst().orElseThrow(()->new NoSuchElementException("NoSuchElementException"));
                link[i][j] *= textRankWeight.getScore(s1, s2)+1.0;
            }
        }
    }
    private static List<String> getResultKeywordList(Map<String, Integer> wordIdxMap, double[] point) {
        Integer[] idxList = IntStream.range(0, point.length)
                                     .boxed()
                                     .sorted(Comparator.comparingDouble(idx -> -point[idx]))
                                     .toArray(Integer[]::new);
        List<String> result  = new ArrayList<>();
        for (int i =0; i<10&&i<idxList.length; i++){
            int idx = i;
            result.add(wordIdxMap.keySet().stream().filter(k-> wordIdxMap.get(k).equals(idxList[idx])).findFirst().orElseThrow(()->new NoSuchElementException("NoSuchElementException")));
        }
        return result;
    }
}
