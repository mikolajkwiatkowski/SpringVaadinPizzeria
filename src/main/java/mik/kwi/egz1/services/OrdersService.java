package mik.kwi.egz1.services;

import mik.kwi.egz1.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrdersService {
    Optional<Orders> getOrder(Integer order_id);
    Orders setOrder(Orders pizza);
    void deleteOrder(Orders pizza);
    Page<Orders> getOrders(Pageable pageable);
    List<Orders> getOrdersByAdres(String adres);
    List<Orders> getOrdersByNrTelefonu(String nrTelefonu);

}
