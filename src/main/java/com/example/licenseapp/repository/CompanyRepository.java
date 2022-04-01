package com.example.licenseapp.repository;

import com.example.licenseapp.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

    @Query("FROM Company")
    List<Company> findAll();

    @Query("SELECT c FROM Company c WHERE c.id = :id")
    Company findById(long id);

    @Query("SELECT c FROM Company c WHERE c.name = :#{#companyEntity.name}")
    List<Company> findByName(@Param("companyEntity") Company companyEntity);

    @Modifying
    @Query("UPDATE Company SET name = :#{#company.name} WHERE id = :#{#company.id}")
    void update(@Param("company") Company company);

    @Modifying
    @Query("DELETE FROM Company c WHERE c.id = :id ")
    void deleteById(@Param("id") long id);

}
