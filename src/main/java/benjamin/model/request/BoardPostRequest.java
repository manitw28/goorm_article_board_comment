package benjamin.model.request;

import lombok.Data;

@Data
public class BoardPostRequest {
    private String title;
    private String content;
}
