package codereview.katrmin.task2.repositories;

import codereview.katrmin.task2.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlEntityRepository extends JpaRepository<UrlEntity, Long> {
}