package codereview.katrmin.task2.services;

import codereview.katrmin.task2.dtos.LinkDto;
import codereview.katrmin.task2.dtos.UrlDto;
import codereview.katrmin.task2.repositories.UrlEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {
    private final UrlEntityRepository urlEntityRepository;

    /**
     * Генерирует короткую ссылку на основе оригинального url
     *
     * @param urlDto оригинальный url
     * @return Dto короткой ссылки
     */
    @Override
    public LinkDto generateLink(UrlDto urlDto) {
//        urlEntityRepository.save();
        return null;
    }

    /**
     * Возвращает оригинальный url, соответстующий короткой ссылке
     *
     * @param linkDto Dto короткой ссылки
     * @return UrlDto Dto оригинального url
     */
    @Override
    public UrlDto getOriginalUrl(LinkDto linkDto) {
        return null;
    }
}
