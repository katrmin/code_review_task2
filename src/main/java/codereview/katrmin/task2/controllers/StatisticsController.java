package codereview.katrmin.task2.controllers;

import codereview.katrmin.task2.dtos.LinkDto;
import codereview.katrmin.task2.dtos.StatisticsDto;
import codereview.katrmin.task2.entities.UrlStatistics;
import codereview.katrmin.task2.repositories.UrlEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatisticsController {
    private final UrlEntityRepository urlEntityRepository;

    @GetMapping("/{link}")
    public StatisticsDto getStatisticsByLink(@Valid @PathVariable("link") LinkDto linkDto) {
        log.info("Получен запрос на получение статистики по короткой ссылки: {}", linkDto);
        UrlStatistics statistics = urlEntityRepository.getStatisticsByLink(linkDto.getLink());
        StatisticsDto statisticsDto = StatisticsDto.builder()
                .link(statistics.getLink())
                .original(statistics.getOriginal())
                .count(statistics.getCount())
                .rank(statistics.getRank())
                .build();
        log.info("Отправлена в ответ статистика по короткой ссылке: {}", statisticsDto);
        return statisticsDto;
    }

    @GetMapping
    public List<StatisticsDto> getAllStatistics(@RequestParam int page, @RequestParam int count) {
        log.info("Получен запрос на получение статистики по всем коротким ссылкам");
        Page<UrlStatistics> statisticsList = urlEntityRepository.getAllUrlStatistics(PageRequest.of(page, count));
        List<StatisticsDto> statisticsDtoList = statisticsList.stream()
                .map(st -> new StatisticsDto(st.getLink(), st.getOriginal(), st.getCount(), st.getRank()))
                .collect(Collectors.toList());
        log.info("Отправлена в ответ статистика по короткой ссылке: {}", statisticsDtoList);
        return statisticsDtoList;
    }
}
