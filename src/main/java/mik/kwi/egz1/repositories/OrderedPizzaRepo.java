package mik.kwi.egz1.repositories;
import mik.kwi.egz1.model.Order;
import mik.kwi.egz1.model.OrderedPizza;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderedPizzaRepo extends JpaRepository<OrderedPizza, Integer>{

}
