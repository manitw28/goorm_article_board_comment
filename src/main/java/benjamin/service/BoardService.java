package benjamin.service;

import benjamin.model.DeleteStatus;
import benjamin.model.entity.Board;
import benjamin.model.request.BoardDeleteRequest;
import benjamin.model.request.BoardPostRequest;
import benjamin.model.response.BoardListResponse;
import benjamin.model.response.BoardResponse;
import benjamin.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // write article
    public BoardResponse writeArticleOnBoard(BoardPostRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setDeleteStatus(DeleteStatus.ACTIVE);
        return BoardResponse.from(boardRepository.save(board));
    }


    // search board article list
    public List<BoardListResponse> searchArticleList(int page, int pageSize) {
        return boardRepository.findAllByDeleteStatus(
                DeleteStatus.ACTIVE,
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "boardNum"))
                ).map(BoardListResponse::from).toList();
    }


    // search single board article (content + comment)
    public BoardResponse searchArticleFromBoard(Long boardNum) {
        return boardRepository.findBoardWithCommentListByBoardNum(boardNum)
                .map(BoardResponse::from)
                .orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));
    }


    // delete article
    @Transactional
    public String deleteArticle(BoardDeleteRequest request) {
        Optional<Board> findBoardById = boardRepository.findById(request.getBoardNum());
        Board boardException = findBoardById.orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        boardRepository.delete(boardException);

        return "OK";
    }



}
