package benjamin.repository;

import benjamin.model.DeleteStatus;
import benjamin.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findAllByDeleteStatus(DeleteStatus deleteStatus, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.commentList c WHERE b.boardNum = boardNum")
    Optional<Board> findBoardWithCommentListByBoardNum(@Param("boardNum") Long boardNo);
}
