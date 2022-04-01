package com.example.licenseapp.service;

import com.example.licenseapp.dto.CopyrightDTO;
import com.example.licenseapp.model.Copyright;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

/**
 * Сервис взаимодействия с авторскими правами.
 * <p>
 * Реализация через JpaRepository
 */
public interface CopyrightService {

    /**
     * Получить все авторские права.
     *
     * @param pageable параметры пагинации
     * @return Список авторских прав
     */
    Page<Copyright> getAll(Pageable pageable);

    /**
     * Получить авторское право по идентификатору.
     *
     * @param copyrightId идентификатор авторского права
     * @return Авторское право
     */
    CopyrightDTO getById(long copyrightId);

    /**
     * Добавить новое авторское право.
     *
     * @param newCopyright новое авторское право
     * @return Созданное авторское право
     */
    CopyrightDTO create(CopyrightDTO newCopyright);

    /**
     * Обновить информацию об авторском праве.
     *
     * @param updatedCopyright обновлённое авторское право
     * @return Обновлённое авторское право
     */
    CopyrightDTO update(CopyrightDTO updatedCopyright);

    /**
     * Удалить авторское право по идентификатору.
     *
     * @param copyrightId идентификатор авторского права
     */
    void delete(long copyrightId);

    /**
     * Получить авторские права со сроком окончания действия между датами.
     *
     * @param from левая граница дат
     * @param to   правая граница дат
     * @return Список авторских прав
     */
    List<CopyrightDTO> copyrightsByDates(LocalDate from, LocalDate to);

    /**
     * Получить авторские права компании.
     *
     * @param companyName название компании
     * @return Список авторских прав
     */
    List<CopyrightDTO> copyrightByCompanyName(String companyName);
}
