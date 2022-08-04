package codereview.katrmin.task2.repositories;

import codereview.katrmin.task2.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlEntityRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByLink(String link);
}