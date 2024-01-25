package benjamin.model.response;

import benjamin.model.DeleteStatus;
import benjamin.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BoardResponse {

    private Long boardNum;
    private String title;
    private String content;
    private DeleteStatus deleteStatus;
    private List<CommentResponse> commentResponseList;



    public static BoardResponse from(Board board) {

        return new BoardResponse(
                board.getBoardNum(),
                board.getTitle(),
                board.getContent(),
                board.getDeleteStatus(),
                board.getCommentList().stream().map(CommentResponse::from).collect(Collectors.toList())
        );

    }

}
