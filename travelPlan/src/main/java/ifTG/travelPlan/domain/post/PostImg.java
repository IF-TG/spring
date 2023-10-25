package ifTG.travelPlan.domain.post;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

/**
 * id :     자동생성되는 post_imgs의 primary키
 * imgURL : 서버에 이미지가 담긴 주소를 담긴 url
 * post :   POSTS 테이블과 post_id로 매핑
 */
@Entity
@Getter
@Table(name = "post_imgs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostImg {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "post_img_id")
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(columnDefinition = "boolean default false")
    private boolean isThumbnail;


    public PostImg(String fileName, Post post, boolean isThumbnail) {
        this.fileName = fileName;
        this.post = post;
        this.isThumbnail = isThumbnail;
        post.addPostImgUri(this);
    }
}
