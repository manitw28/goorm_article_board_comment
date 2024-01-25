package benjamin.service;

import benjamin.model.entity.Board;
import benjamin.model.request.CommentPostRequest;
import benjamin.model.response.BoardResponse;
import benjamin.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;

    // write comment
    @Transactional
    public BoardResponse writeComment(CommentPostRequest request) {

        Optional<Board> findBoardByBoardNum = boardRepository.findBoardWithCommentListByBoardNum(request.getBoardNum());
        Board articleException = findBoardByBoardNum.orElseThrow(() -> new RuntimeException("게시글이 존재하지 않습니다."));

        articleException.addComment(request.getContent());
        boardRepository.save(articleException);

        return BoardResponse.from(articleException);
    }

    // search single article(content + comment)

}
