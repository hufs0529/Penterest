package penterest.spring.domain.gif.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GifData {
    private String url;
    private String caption;

    public void validate() throws IllegalArgumentException {
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }
        if (caption == null || caption.isEmpty()) {
            throw new IllegalArgumentException("Caption cannot be empty");
        }
    }
}
