package com.example.licenseapp.serviceImpl;

import com.example.licenseapp.LicenseAppApplication;
import com.example.licenseapp.dto.CopyrightDTO;
import com.example.licenseapp.service.CopyrightService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LicenseAppApplication.class)
@ContextConfiguration(locations = {"classpath*:hibernate.cfg.xml"})
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CopyrightServiceImplTest {

    @Autowired
    private CopyrightService copyrightService;

    @Test
    @Order(1)
    void getAll() {

        assertEquals(copyrightService.getAll(PageRequest.of(0,20)).getTotalElements(), 4L);
    }

    @Test
    @Order(2)
    void getById() {

        CopyrightDTO copyright = copyrightService.getById(1L);

        assertNotNull(copyright);
        assertEquals(copyright.title(), "Sony");
        assertEquals(copyright.cost(), 300L);
    }

    @Test
    @Order(3)
    void create() {

        CopyrightDTO copyrightDTO = new CopyrightDTO(null,
                "Universal Pictures",
                LocalDate.now(),
                500L,
                null,
                null);
        CopyrightDTO savedCopyright = copyrightService.create(copyrightDTO);

        assertNotNull(savedCopyright);

        assertEquals(copyrightService.getAll(PageRequest.of(0,20)).getTotalElements(), 5L);
    }

    @Test
    @Order(4)
    void update() {

        CopyrightDTO copyrightDTO = copyrightService.getById(2L);

        CopyrightDTO updatedCopyright = new CopyrightDTO(2L,
                "Universal Pictures",
                copyrightDTO.expireDt(),
                500L,
                copyrightDTO.company(),
                copyrightDTO.recordings());
        CopyrightDTO update = copyrightService.update(updatedCopyright);

        assertEquals(update.title(), "Universal Pictures");
        assertEquals(update.cost(), 500L);
    }

    @Test
    @Order(5)
    void delete() {

        copyrightService.delete(4L);
        assertEquals(copyrightService.getAll(PageRequest.of(0,20)).getTotalElements(), 3);
    }

    @Test
    @Order(6)
    void copyrightsByDates() {

        List<CopyrightDTO> copyrightDTOS = copyrightService.copyrightsByDates(LocalDate.now().minusDays(1), LocalDate.now().plusDays(2));

        assertEquals(copyrightDTOS.size(), 4);
    }

    @Test
    @Order(7)
    void copyrightByCompanyName() {

        List<CopyrightDTO> copyrightDTOS = copyrightService.copyrightByCompanyName("Black Company");

        CopyrightDTO copyrightDTO = copyrightDTOS.get(0);

        CopyrightDTO byId = copyrightService.getById(1L);

        assertEquals(byId, copyrightDTO);
    }
}