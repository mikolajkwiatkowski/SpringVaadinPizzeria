package mik.kwi.egz1.repositories;

import mik.kwi.egz1.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {

    @Query("SELECT o FROM Orders o WHERE o.address= :address")
    List<Orders> findOrderByAdres(@Param("address") String address);
    @Query("SELECT o FROM Orders o WHERE o.telephoneNumber= :telephoneNumber")
    List<Orders> findOrderByNumerTelefonu(@Param("telephoneNumber") String telephoneNumber);
}
