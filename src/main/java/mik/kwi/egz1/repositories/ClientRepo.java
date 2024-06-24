package mik.kwi.egz1.repositories;

import mik.kwi.egz1.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepo extends JpaRepository<Client, Integer> {
}
