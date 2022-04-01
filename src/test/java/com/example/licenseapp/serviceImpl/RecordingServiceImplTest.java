package com.example.licenseapp.serviceImpl;

import com.example.licenseapp.LicenseAppApplication;
import com.example.licenseapp.dto.RecordingDTO;
import com.example.licenseapp.service.RecordingService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LicenseAppApplication.class)
@ContextConfiguration(locations = {"classpath*:hibernate.cfg.xml"})
@Transactional
class RecordingServiceImplTest {

    @Autowired
    private RecordingService recordingService;

    @Test
    @Order(1)
    void getAll() {

        assertEquals(recordingService.getAll().size(), 4);
    }

    @Test
    @Order(2)
    void getById() {

        RecordingDTO recordingDTO = recordingService.getById(3);

        assertEquals(recordingDTO.id(), 3L);
        assertEquals(recordingDTO.title(), "Bad Guy");
        assertEquals(recordingDTO.version(), 1);
        assertEquals(recordingDTO.releaseTime(), LocalDate.parse("2019-01-15"));
    }

    @Test
    @Order(3)
    void update() {

        RecordingDTO recordingDTO = new RecordingDTO(3L, "Bad guy", "2", ZonedDateTime.now(), null, null);

        recordingService.update(recordingDTO);
        RecordingDTO savedRecording = recordingService.getById(3L);

        assertEquals(savedRecording.title(), "Bad guy");
        assertEquals(savedRecording.version(), "2");
    }

    @Test
    @Order(4)
    void create() {

        RecordingDTO recordingDTO = new RecordingDTO(null, "Title", "1", ZonedDateTime.now(), null, null);
        recordingService.create(recordingDTO);

        RecordingDTO savedRecording = recordingService.getById(5L);

        assertEquals(savedRecording.version(), "1");
        assertEquals(savedRecording.title(), "Title");
    }

    @Test
    @Order(5)
    void delete() {

        recordingService.delete(4L);
        assertEquals(recordingService.getAll().size(), 3);
    }

    @Test
    @Order(6)
    void removeSinger() {

        recordingService.removeSinger(3L);
        assertNull(recordingService.getById(3L).singer());
    }
}