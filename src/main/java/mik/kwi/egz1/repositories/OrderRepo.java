package mik.kwi.egz1.repositories;

import mik.kwi.egz1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepo extends JpaRepository<Order, Integer> {
}
