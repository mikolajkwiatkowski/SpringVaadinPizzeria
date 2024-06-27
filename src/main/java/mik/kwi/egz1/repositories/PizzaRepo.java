package mik.kwi.egz1.repositories;

import mik.kwi.egz1.model.Pizza;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@Repository
public interface PizzaRepo extends JpaRepository<Pizza, Integer> {

    @Query("SELECT p FROM Pizza p WHERE p.rozmiar= :rozmiar")
    List<Pizza> findPizzaByRozmiar(@Param("rozmiar") String rozmiar);
}
