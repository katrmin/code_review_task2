package codereview.katrmin.task2.repositories;

import codereview.katrmin.task2.entities.UrlEntity;
import codereview.katrmin.task2.entities.UrlStatatistics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UrlEntityRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByLink(String link);

    @Query(value = "select * from (select link, original, count, dense_rank()  over (order by count desc) as \"rank\" from url) s where s.link = ?1", nativeQuery = true)
    UrlStatatistics getStatisticsByLink(String link);

    @Query(value = "select link, original, count, dense_rank() over (order by count desc) as \"rank\" from url",
//            countProjection = "SELECT count(*) FROM url",
            nativeQuery = true)
//countQuery = "SELECT count(*) FROM url",
    Page<UrlStatatistics> getAllUrlStatistics(Pageable pageable);

}