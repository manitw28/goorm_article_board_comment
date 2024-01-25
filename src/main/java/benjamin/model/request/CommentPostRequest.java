package benjamin.model.request;

import benjamin.model.entity.Comment;
import benjamin.model.response.CommentResponse;
import lombok.Data;

import java.util.List;

@Data
public class CommentPostRequest {
    private Long boardNum;
    private String content;

    // 정적 팩토리 메서드
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getCommentNum(), comment.getContent());
    }
}
