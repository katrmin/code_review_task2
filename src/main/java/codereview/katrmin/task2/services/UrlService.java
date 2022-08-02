package codereview.katrmin.task2.services;

import codereview.katrmin.task2.dtos.LinkDto;
import codereview.katrmin.task2.dtos.UrlDto;

public interface UrlService {
    /**
     * Генерирует короткую ссылку на основе оригинального url
     *
     * @param urlDto оригинальный url
     * @return Dto короткой ссылки
     */
    LinkDto generateLink(UrlDto urlDto);

    /**
     * Возвращает оригинальный url, соответстующий короткой ссылке
     *
     * @param linkDto Dto короткой ссылки
     * @return UrlDto Dto оригинального url
     */
    UrlDto getOriginalUrl(LinkDto linkDto);
}
