package com.example.licenseapp.service;

import com.example.licenseapp.dto.SingerDTO;

import java.util.List;

/**
 * Сервис для работы с исполнителями.
 *
 * SessionFactory
 */
public interface SingerService {

    /**
     * Получить всех исполнителей.
     *
     * @return Список исполнителей
     */
    List<SingerDTO> getAll();

    /**
     * Получить исполнителя по идентификатору.
     *
     * @param singerId идентификатор исполнителя
     * @return Исполнитель
     */
    SingerDTO getById(long singerId);

    /**
     * Добавить нового исполнителя.
     *
     * @param newSinger новый исполнитель
     */
    void create(SingerDTO newSinger);

    /**
     * Обновить информацию об исполнителе.
     *
     * @param updatedSinger Исполнитель с обновлёнными данными
     */
    void update(SingerDTO updatedSinger);

    /**
     * Удалить исполнителя по идентификатору.
     *
     * @param singerId Идентификатор исполнителя
     */
    void delete(long singerId);
}
