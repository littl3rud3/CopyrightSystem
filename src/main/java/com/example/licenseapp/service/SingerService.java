package com.example.licenseapp.service;

import java.util.List;

import com.example.licenseapp.dto.SingerDTO;

/**
 * Service for working with singers.
 *
 * Implementation via Hibernate
 */
public interface SingerService {

    /**
     * Get all singers.
     *
     * @return List of singers
     */
    List<SingerDTO> getAll();

    /**
     * Get singer by identifier.
     *
     * @param singerId singer identifier
     * @return Singer
     */
    SingerDTO getById(long singerId);

    /**
     * Add new singer.
     *
     * @param newSinger new singer
     */
    void create(SingerDTO newSinger);

    /**
     * Update singer info.
     *
     * @param updatedSinger Updated singer
     */
    void update(SingerDTO updatedSinger);

    /**
     * Delete singer by identifier.
     *
     * @param singerId singer identifier
     */
    void delete(long singerId);
}
