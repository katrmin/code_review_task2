package codereview.katrmin.task2.repositories;

import codereview.katrmin.task2.entities.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    @Query(value = "select * from (select id, link, original, count, row_number()  over (order by count desc) as \"rank\" from url) s where s.link = ?1", nativeQuery = true)
    Statistics getStatisticsByLink(String link);

    @Query(value = "select id, link, original, count, row_number()  over (order by count desc) as \"rank\" from url", nativeQuery = true)
    List<Statistics> getAllStatistics();

}