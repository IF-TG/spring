package ifTG.travelPlan.aop;

import ifTG.travelPlan.controller.dto.RequestLikeDto;
import ifTG.travelPlan.exception.StatusCode;
import ifTG.travelPlan.domain.post.PostLike;
import ifTG.travelPlan.domain.post.PostLikeId;
import ifTG.travelPlan.domain.post.comment.CommentLike;
import ifTG.travelPlan.domain.post.comment.CommentLikeId;
import ifTG.travelPlan.domain.post.comment.NestedCommentLike;
import ifTG.travelPlan.domain.post.comment.NestedCommentLikeId;
import ifTG.travelPlan.dto.post.ToggleDto;
import ifTG.travelPlan.repository.springdata.comment.CommentLikeRepository;
import ifTG.travelPlan.repository.springdata.comment.CommentRepository;
import ifTG.travelPlan.repository.springdata.comment.NestedCommentLikeRepository;
import ifTG.travelPlan.repository.springdata.post.PostLikeRepository;
import ifTG.travelPlan.service.comment.CommentLikeService;
import ifTG.travelPlan.service.comment.NestedCommentLikeService;
import ifTG.travelPlan.service.post.PostLikeService;
import ifTG.travelPlan.service.travelplan.search.machineleaning.util.Check;
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
    private final CommentRepository commentRepository;

    @Around("@annotation(ToggleLike)")
    public ToggleDto toggleLike(ProceedingJoinPoint pjp){
        Long userId = (Long) pjp.getArgs()[0];
        RequestLikeDto requestLikeDto = (RequestLikeDto) pjp.getArgs()[1];

        if(pjp.getTarget() instanceof CommentLikeService){
            CommentLikeId commentLikeId = new CommentLikeId(userId, requestLikeDto.getObjectId());
            Optional<CommentLike> commentLike = commentLikeRepository.findById(commentLikeId);

            if(commentLike.isEmpty()){
                commentLikeRepository.save(new CommentLike(commentLikeId));
                return new ToggleDto(commentLikeId.getCommentId(), true);
            }else{
                CommentLike cl = commentLike.get();
                Check.is(commentRepository.isDeleted(cl.getCommentLikeId().getCommentId()), StatusCode.NOT_FOUND_COMMENT);
                commentLikeRepository.delete(cl);
                return new ToggleDto(commentLikeId.getCommentId(), false);
            }
        }

        else if (pjp.getTarget() instanceof NestedCommentLikeService){
            NestedCommentLikeId nestedCommentLikeId = new NestedCommentLikeId(userId, requestLikeDto.getObjectId());
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
            PostLikeId postLikeId = new PostLikeId(userId, requestLikeDto.getObjectId());
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
