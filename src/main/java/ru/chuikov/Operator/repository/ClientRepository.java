package ru.chuikov.Operator.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.chuikov.Operator.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("select b from Client b where b.id = :id")
    Client findOne(@Param("id") long id);
}
