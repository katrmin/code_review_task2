package codereview.katrmin.task2.controllers;

import codereview.katrmin.task2.dtos.LinkDto;
import codereview.katrmin.task2.dtos.UrlDto;
import codereview.katrmin.task2.services.UrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/generated-link")
    public LinkDto generate(@Valid @RequestBody UrlDto urlDto) {
        log.info("Получен запрос на генерацию короткой ссылки: {}", urlDto);
        LinkDto linkDto = urlService.generateLink(urlDto);
        log.info("Отправлена в ответ сгенеренная короткая ссылка: {}", linkDto);
        return linkDto;
    }

    @Cacheable("originalUrl")
    @GetMapping("/{link}")
    public ResponseEntity<Void> redirect(@Valid @PathVariable(name = "link") LinkDto linkDto) {
        log.info("Получен запрос на редирект по короткой ссылке: {}", linkDto);
        UrlDto urlDto = urlService.getOriginalUrl(linkDto);
        log.info("Клиент перенаправлен на оригинальный Url: {}", urlDto);
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlDto.getOriginal())).build();
    }
}