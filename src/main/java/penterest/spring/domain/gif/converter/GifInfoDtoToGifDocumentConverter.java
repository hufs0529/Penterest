package penterest.spring.domain.gif.converter;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import penterest.spring.domain.gif.dto.GifInfoDto;
import penterest.spring.domain.gif.entity.Gif;
import penterest.spring.domain.gif.entity.GifDocument;


@Component
public class GifInfoDtoToGifDocumentConverter implements Converter<Gif, GifDocument> {
    @Override
    public GifDocument convert(Gif gif) {
        GifDocument gifDocument = new GifDocument();
        gifDocument.setId(gif.getId());
        gifDocument.setUrl(gif.getUrl());
        gifDocument.setCaption(gif.getCaption());

        return gifDocument;
    }
}

