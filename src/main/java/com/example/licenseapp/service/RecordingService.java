package com.example.licenseapp.service;

import java.util.List;

import com.example.licenseapp.dto.RecordingDTO;

/**
 * A service for working with compositions.
 *
 * Implementation via Criteria API
 */
public interface RecordingService {

    /**
     * Get all compositions.
     *
     * @return List of records
     */
    List<RecordingDTO> getAll();

    /**
     * Get the composition by ID.
     *
     * @param recordId record identifier
     * @return Record
     */
    RecordingDTO getById(long recordId);

    /**
     * Add new record.
     *
     * @param newRecording New record
     */
    void create(RecordingDTO newRecording);

    /**
     * Update record info.
     *
     * @param updatedRecord record with updated data
     * @return record with updated data
     */
    RecordingDTO update(RecordingDTO updatedRecord);

    /**
     * Delete record by ID.
     *
     * @param recordId record identifier
     */
    void delete(long recordId);

    /**
     * Delete the artist of the composition by the record ID.
     *
     * @param recordId record identifier
     */
    void removeSinger(long recordId);

    /**
     * Get the cost of the license for the record.
     *
     * @param recordId record identifier
     * @return List of prices
     */
    List<Long> getPriceForRecording(Long recordId);
}
