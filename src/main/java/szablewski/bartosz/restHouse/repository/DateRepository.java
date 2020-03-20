package szablewski.bartosz.restHouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import szablewski.bartosz.restHouse.model.Date;

@Repository
public interface DateRepository extends JpaRepository<Date, Long> {
}
