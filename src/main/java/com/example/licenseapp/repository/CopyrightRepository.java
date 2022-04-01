package com.example.licenseapp.repository;

import com.example.licenseapp.model.Copyright;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CopyrightRepository extends JpaRepository<Copyright, Long> {

    List<Copyright> getCopyrightsByExpireDtBetween(LocalDate from, LocalDate to);

    List<Copyright> getCopyrightsByCompanyName(String companyName);

    void deleteById(long id);
}
