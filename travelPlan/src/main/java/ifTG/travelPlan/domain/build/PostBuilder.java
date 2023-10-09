package ifTG.travelPlan.domain.build;

import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.user.User;

import java.time.LocalDate;

public class PostBuilder {
    private long id;
    private String title;
    private String content;
    private User user;
    private LocalDate startDate;
    private LocalDate endDate;

    public PostBuilder id(Long id){
        this.id = id;
        return this;
    }
    public PostBuilder title(String title){
        this.title = title;
        return this;
    }

    public PostBuilder content(String content){
        this.content = content;
        return this;
    }
    public PostBuilder user(User user){
        this.user = user;
        return this;
    }

    public PostBuilder startDate(LocalDate startDate){
        this.startDate = startDate;
        return this;
    }
    public PostBuilder endDate(LocalDate endDate){
        this.endDate = endDate;
        return this;
    }
    public Post build(){
        return new Post(id, title, content, user, startDate, endDate);
    }
}
