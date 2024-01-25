package benjamin.controller;

import benjamin.model.request.CommentPostRequest;
import benjamin.model.response.BoardResponse;
import benjamin.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // add comment
    @PostMapping("comment")
    public BoardResponse writeComment(
            @RequestBody CommentPostRequest commentPostRequest
        ) {

        return commentService.writeComment(commentPostRequest);

    }



}
