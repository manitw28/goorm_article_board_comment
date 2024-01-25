package benjamin.model.entity;

import benjamin.model.DeleteStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE board_no = ?")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;

    private String title;

    @Column(length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Where(clause = "DELETE_STATUS = 'ACTIVE'")
    private List<Comment> commentList = new ArrayList<>();

    //
    public Board addComment(String commentContent) {
        Comment comment = new Comment();
        comment.setContent(commentContent);
        comment.setBoard(this);
        comment.setDeleteStatus(DeleteStatus.ACTIVE);

        this.getCommentList().add(comment);
        return this;

    }

}
