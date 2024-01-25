package benjamin.controller;

import benjamin.model.request.BoardDeleteRequest;
import benjamin.model.request.BoardPostRequest;
import benjamin.model.response.BoardListResponse;
import benjamin.model.response.BoardResponse;
import benjamin.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // write article
    @PostMapping("board")
    public BoardResponse writeArticleOnBoard(
            @RequestBody BoardPostRequest request
            ) {

        return boardService.writeArticleOnBoard(request);

    }


    // search article list (page)
    @GetMapping("boards")
    public List<BoardListResponse> searchArticleList(
        @RequestParam("page") int page,
        @RequestParam("pageSize") int pageSize
    ) {

        return boardService.searchArticleList(page, pageSize);

    }


    // search single article (article content + comment)
    @GetMapping("board")
    public BoardResponse searchArticle(
            @RequestParam("boardNum") Long boardNum
    ) {
        return boardService.searchArticleFromBoard(boardNum);
    }


    // delete article from board
    @DeleteMapping("board")
    public String deleteArticleFromBoard(
            @RequestBody BoardDeleteRequest request
    ) {
        return boardService.deleteArticle(request);
    }


}



