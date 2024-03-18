package ifTG.travelPlan.service.comment;

import ifTG.travelPlan.converter.CommentDtoConverter;
import ifTG.travelPlan.domain.post.Post;
import ifTG.travelPlan.domain.post.comment.Comment;
import ifTG.travelPlan.domain.user.User;
import ifTG.travelPlan.dto.comment.CommentDtoWithUserInfo;
import ifTG.travelPlan.dto.comment.NestedCommentDto;
import ifTG.travelPlan.repository.springdata.comment.CommentRepository;
import ifTG.travelPlan.repository.springdata.user.UserBlockRepository;
import ifTG.travelPlan.repository.springdata.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    CommentServiceImpl commentService;

    @MockBean
    UserRepository userRepository;
    @MockBean
    CommentRepository commentRepository;

    @MockBean
    UserBlockRepository userBlockRepository;

    @MockBean
    CommentDtoConverter converter;

  /*  MockMvc mockMvc;
    @BeforeEach
    void init(){
        mockMvc = MockMvcBuilders.standaloneSetup(commentService).build();

    }*/

    @Test
    void getCommentListByPost() {
        //given
        User user = new User(1L);
        Post post = Post.builder()
                .title("test")
                .content("test")
                .user(user)
                .startDate(LocalDate.of(2022, 1, 1))
                .endDate(LocalDate.of(2022, 12, 31))
                .mapX(37.5665)
                .mapY(126.9780)
                .build();
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Comment> comments = Arrays.asList(new Comment("test1", user, post), new Comment("test2", user, post));
        Page<Comment> pageOfComments = new PageImpl<>(comments, PageRequest.of(0, 10), comments.size());
        doReturn(user).when(userRepository).findWithCommentLikeAndNestedCommentLikeByUserId(1L);
        doReturn(pageOfComments).when(commentRepository).findAllWithUserByPostId(pageRequest, 1L);
        doReturn(null).when(userBlockRepository).findBlockedUserIdListByUserId(1L);
        CommentDtoWithUserInfo test = CommentDtoWithUserInfo.builder()
                .commentId(1L)
                .nickname("test")
                .isLiked(true)
                .isBlocked(false)
                .createAt(LocalDateTime.now())
                .likeNum(5)
                .comment("목..데..이..터")
                .isDeleted(false)
                .build();
        List<CommentDtoWithUserInfo> ans= Arrays.asList(
                test
        );
        doReturn(ans).when(converter).getCommentDtoList(eq(pageOfComments), anyList(), anyList(), anyList());
        //when
        List<CommentDtoWithUserInfo> commentListByPost = commentService.getCommentListByPost(1L, 1L, pageRequest);
        //then
        System.out.println("commentListByPost = " + commentListByPost);
        System.out.println("commentListByPost.size() = " + commentListByPost.size());
    }

    @Test
    void testGetCommentListByPost() {
    }

    @Test
    void saveComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void updateComment() {
    }
}