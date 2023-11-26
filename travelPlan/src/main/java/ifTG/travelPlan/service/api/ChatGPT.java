package ifTG.travelPlan.service.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ChatGPT {
    CompletableFuture<List<String>> findRelatedKeywords(String search);
}
