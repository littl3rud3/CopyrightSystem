package com.example.licenseapp.repository;

import com.example.licenseapp.model.security.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {

    Client getByLogin(String login);

    @Query(nativeQuery = true, value = "INSERT INTO client(login, password) VALUES (:#{#client.login}, :#{#client.password})")
    @Modifying
    void insert(@Param("client") Client client);
}
