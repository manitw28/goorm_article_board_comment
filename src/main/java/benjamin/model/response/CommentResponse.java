package benjamin.model.response;

import benjamin.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
    private Long commentNum;
    private String commentContent;

    // 정적 팩토리 메서드
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getCommentNum(), comment.getContent());
    }
}
