package com.example.licenseapp.serviceImpl;

import com.example.licenseapp.LicenseAppApplication;
import com.example.licenseapp.dto.CompanyDTO;
import com.example.licenseapp.service.CompanyService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LicenseAppApplication.class)
@ContextConfiguration(locations = {"classpath*:hibernate.cfg.xml"})
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyServiceImplTest {

    @Autowired
    private CompanyService companyService;

    @Test
    @Order(1)
    void getAll() {

        assertEquals(companyService.getAll().size(), 4);
    }

    @Test
    @Order(2)
    void getById() {

        CompanyDTO company = companyService.getById(1L);

        assertNotNull(company);
        assertEquals(company.name(), "Black Company");
    }

    @Test
    @Order(3)
    void create() {
        CompanyDTO company = new CompanyDTO(null, "Google", null);

        CompanyDTO companyDTO = companyService.create(company);
        assertNotNull(companyDTO);
        assertEquals(companyService.getAll().size(), 5);
    }

    @Test
    @Order(4)
    void update() {

        CompanyDTO companyDTO = companyService.getById(4L);

        CompanyDTO updatedCompany = new CompanyDTO(companyDTO.id(), "Imagine Label", companyDTO.copyrights());
        CompanyDTO updatedSavedCompany = companyService.update(updatedCompany);

        assertNotEquals(updatedCompany, companyService.getById(4L));
        assertEquals(updatedSavedCompany, updatedCompany);
    }

    @Test
    @Order(5)
    void delete() {
        companyService.delete(4L);
        assertEquals(companyService.getAll().size(), 4L);
    }
}