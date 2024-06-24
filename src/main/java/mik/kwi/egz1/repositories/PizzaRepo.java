package mik.kwi.egz1.repositories;

import mik.kwi.egz1.model.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

//po id
    Optional<Pizza> findBypizzaId(Integer pizza_id);
    @Query("SELECT p FROM Pizza p ORDER BY p.price ASC")
    Page<Pizza> findByLowestPrice(Pageable pageable);
    @Query("SELECT p FROM Pizza p ORDER BY p.price ASC")
    List<Pizza> findByLowestPrice();

}
