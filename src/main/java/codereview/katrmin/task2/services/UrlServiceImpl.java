package codereview.katrmin.task2.services;

import codereview.katrmin.task2.dtos.LinkDto;
import codereview.katrmin.task2.dtos.UrlDto;
import codereview.katrmin.task2.entities.UrlEntity;
import codereview.katrmin.task2.repositories.UrlEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Slf4j
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
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLink(generateLink());
        urlEntity.setOriginal(urlDto.getOriginal());
        urlEntityRepository.save(urlEntity);
        return new LinkDto(urlEntity.getLink());
    }

    /**
     * Возвращает оригинальный url, соответстующий короткой ссылке
     *
     * @param linkDto Dto короткой ссылки
     * @return UrlDto Dto оригинального url
     */
    @Override
    public UrlDto getOriginalUrl(LinkDto linkDto) {
        Optional<UrlEntity> optionalUrlEntity = urlEntityRepository.findByLink(linkDto.getLink());
        if (optionalUrlEntity.isPresent()) {
            optionalUrlEntity.get().setCount(optionalUrlEntity.get().getCount() + 1);
            urlEntityRepository.save(optionalUrlEntity.get());
        }
        return new UrlDto(optionalUrlEntity.orElseThrow().getOriginal());
    }

    public static void main(String[] args) {
        generateLink();
    }

    public static String generateLink() {
        final int LEFT_LIMIT_NUMERAL_0 = 48;
        final int RIGHT_LIMIT_LETTER_Z = 122;
        final int NUMERAL_9 = 57;
        final int LETTER_UPPER_A = 65;
        final int LETTER_UPPER_Z = 90;
        final int LETTER_SMALL_A = 97;

        int leftLimit = LEFT_LIMIT_NUMERAL_0; // numeral '0'
        int rightLimit = RIGHT_LIMIT_LETTER_Z; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= NUMERAL_9 || i >= LETTER_UPPER_A) && (i <= LETTER_UPPER_Z || i >= LETTER_SMALL_A))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        log.info("Сгенерена ссылка: {}", generatedString);
        return generatedString;
    }
}
