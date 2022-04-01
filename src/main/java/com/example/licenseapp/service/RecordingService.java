package com.example.licenseapp.service;

import com.example.licenseapp.dto.RecordingDTO;

import java.util.List;

/**
 * Сервис для работы с композициями.
 *
 * Реализация через Criteria API
 */
public interface RecordingService {

    /**
     * Получить все композиции.
     *
     * @return список композиций
     */
    List<RecordingDTO> getAll();

    /**
     * Получить композицию по идентификатору.
     *
     * @param recordId идентификатор композиций
     * @return Композиция
     */
    RecordingDTO getById(long recordId);

    /**
     * Добавить новую композицию.
     *
     * @param newRecording новая композиция
     */
    void create(RecordingDTO newRecording);

    /**
     * Обновить информацию о композиции.
     *
     * @param updatedRecord композиция с обновлёнными данными
     * @return композиция с обновлёнными данными
     */
    RecordingDTO update(RecordingDTO updatedRecord);

    /**
     * Удалить композицию по идентификатору.
     *
     * @param recordId идентификатор композиции
     */
    void delete(long recordId);

    /**
     * Удалить исполнителя композиции по идентификатору композиции.
     *
     * @param recordId идентификатор композиции
     */
    void removeSinger(long recordId);

    /**
     * Получить стоимость лицензии на композицию.
     *
     * @param recordId идентификатор композиции
     * @return Список цен
     */
    List<Long> getPriceForRecording(Long recordId);
}
