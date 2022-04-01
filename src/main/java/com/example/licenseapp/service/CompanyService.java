package com.example.licenseapp.service;

import com.example.licenseapp.dto.CompanyDTO;

import java.util.List;

/**
 * Сервис для взаимодействия с компаниями.
 *
 * Реализация через HQL
 */
public interface CompanyService {

    /**
     * Получить все компании.
     *
     * @return список компаний
     */
    List<CompanyDTO> getAll();

    /**
     * Получить компанию по идентификатору.
     *
     * @param companyId идентификатор компании
     * @return Компания
     */
    CompanyDTO getById(long companyId);

    /**
     * Сохранить новую компанию.
     *
     * @param newCompany новая комппания
     * @return сохранённая компания
     */
    CompanyDTO create(CompanyDTO newCompany);

    /**
     * Обновить информацию о компании.
     *
     * @param updatedCompany новая информация о компании
     * @return Обновлённая сущность компании
     */
    CompanyDTO update(CompanyDTO updatedCompany);

    /**
     * Удалить сохранённую компанию.
     *
     * @param companyId Идентификатор компании
     */
    void delete(long companyId);
}
