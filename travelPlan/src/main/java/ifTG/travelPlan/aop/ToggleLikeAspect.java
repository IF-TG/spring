package ifTG.travelPlan.aop;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.post.PostLikeId;
import ifTG.travelPlan.domain.post.comment.CommentLike;
import ifTG.travelPlan.domain.post.comment.CommentLikeId;
import ifTG.travelPlan.domain.post.comment.NestedCommentLike;
import ifTG.travelPlan.domain.post.comment.NestedCommentLikeId;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.CommentLikeRepository;
import ifTG.travelPlan.repository.springdata.NestedCommentLikeRepository;
import ifTG.travelPlan.repository.springdata.PostLikeRepository;
import ifTG.travelPlan.service.comment.CommentLikeService;
import ifTG.travelPlan.service.comment.NestedCommentLikeService;
import ifTG.travelPlan.service.post.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 나중에 추상 팩토리로 만들자
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ToggleLikeAspect {
    private final NestedCommentLikeRepository nestedCommentLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final PostLikeRepository postLikeRepository;

    @Around("@annotation(ToggleLike)")
    public ToggleDto toggleLike(ProceedingJoinPoint pjp){
        RequestLikeDto requestLikeDto = (RequestLikeDto) pjp.getArgs()[0];

        if(pjp.getTarget() instanceof CommentLikeService){
            CommentLikeId commentLikeId = new CommentLikeId(requestLikeDto.getUserId(), requestLikeDto.getObjectId());
            Optional<CommentLike> commentLike = commentLikeRepository.findById(commentLikeId);

            if(commentLike.isEmpty()){
                commentLikeRepository.save(new CommentLike(commentLikeId));
                return new ToggleDto(commentLikeId.getCommentId(), true);
            }else{
                commentLikeRepository.delete(commentLike.get());
                return new ToggleDto(commentLikeId.getCommentId(), false);
            }
        }

        else if (pjp.getTarget() instanceof NestedCommentLikeService){
            NestedCommentLikeId nestedCommentLikeId = new NestedCommentLikeId(requestLikeDto.getUserId(), requestLikeDto.getObjectId());
            Optional<NestedCommentLike> nestedCommentLike = nestedCommentLikeRepository.findById(nestedCommentLikeId);

            if(nestedCommentLike.isEmpty()){
                nestedCommentLikeRepository.save(new NestedCommentLike(nestedCommentLikeId));
                return new ToggleDto(nestedCommentLikeId.getNestedCommentId(), true);
            }else{
                nestedCommentLikeRepository.delete(nestedCommentLike.get());
                return new ToggleDto(nestedCommentLikeId.getNestedCommentId(), false);
            }

        }

        else if (pjp.getTarget() instanceof PostLikeService){
            PostLikeId postLikeId = new PostLikeId(requestLikeDto.getUserId(), requestLikeDto.getObjectId());
            Optional<PostLike> postLike = postLikeRepository.findById(postLikeId);

            if(postLike.isEmpty()){
                postLikeRepository.save(new PostLike(postLikeId));
                return new ToggleDto(postLikeId.getPostId(), true);
            }else{
                postLikeRepository.delete(postLike.get());
                return new ToggleDto(postLikeId.getPostId(), false);
            }
        }

        return new ToggleDto(requestLikeDto.getObjectId(), false);

    }
}
