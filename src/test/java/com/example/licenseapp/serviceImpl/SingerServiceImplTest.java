package com.example.licenseapp.serviceImpl;

import com.example.licenseapp.dto.SingerDTO;
import com.example.licenseapp.service.SingerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Unit тестирование с использованием Mockito.
 */
@ExtendWith(MockitoExtension.class)
class SingerServiceImplTest {

    @Mock
    private SingerService singerService;

    /**
     * Получить всех исполнителей.
     */
    @Test
    void givenNothing_whenGetsAllSingers_thenReturnAllSingers() {

        when(singerService.getAll()).thenReturn(List.of());

        List<SingerDTO> allSingers = singerService.getAll();
        assertEquals(allSingers.size(), 0);
    }

    /**
     * Получение исполнителя по id.
     */
    @Test
    void givenSingerId_whenGetsExactSinger_thenReturnSinger() {

        SingerDTO singer = new SingerDTO(4L, "Metallica");

        when(singerService.getById(4L)).thenReturn(singer);

        SingerDTO singerDTO = singerService.getById(4L);

        assertEquals(singerDTO.id(), 4L);
        assertEquals(singerDTO.name(), "Metallica");
    }

    /**
     * Добавление нового исполнителя.
     */
    @Test
    void givenNewSinger_whenInsertInDB_thenReturnNewSinger() {

        SingerDTO singerDTO = new SingerDTO(null, "Title");

        singerService.create(singerDTO);

        SingerDTO singerDTO5 = singerService.getById(5L);

        assertEquals(singerDTO5.id(), 5L);
        assertEquals(singerDTO5.name(), "Title");
    }

    /**
     * Обновление данных исполнителя.
     */
    @Test
    void givenNewNameToExistSinger_whenUpdateSinger_thenReturnUpdatedSinger() {

        SingerDTO singer = new SingerDTO(4L, "METALLICA");
        when(singerService.getById(4L)).thenReturn(singer);

        singerService.update(singer);

        SingerDTO updatedSinger = singerService.getById(4L);

        assertEquals(updatedSinger.name(), "METALLICA");
    }

    /**
     * Удаление исполнителя.
     */
    @Test
    void givenSingerId_whenDeleteSingerById_thenReturnNothing() {

        when(singerService.getAll()).thenReturn(List.of());
        singerService.delete(4L);

        assertEquals(singerService.getAll().size(), 0);
    }
}