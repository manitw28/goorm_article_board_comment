package benjamin.model.response;

import benjamin.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResponse {

    private Long boardNum;
    private String title;

    // static factory method
    public static BoardListResponse from(Board board) {
        return new BoardListResponse(
                board.getBoardNum(),
                board.getTitle()
        );
    }

}
