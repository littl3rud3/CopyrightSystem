package com.example.licenseapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.licenseapp.dto.CopyrightDTO;
import com.example.licenseapp.model.Copyright;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The service of interaction with copyrights.
 * <p>
 * Implementation via JPA
 */
public interface CopyrightService {
    
    /**
     * Get all copyrights.
     *
     * @param pageable pagination parameters
     *
     * @return List of copyrights
     */
    Page<Copyright> getAll(Pageable pageable);
    
    /**
     * Get copyright by ID.
     *
     * @param copyrightId copyright identifier
     *
     * @return Copyright
     */
    CopyrightDTO getById(long copyrightId);
    
    /**
     * Add a new copyright.
     *
     * @param newCopyright new copyright
     *
     * @return Created Copyright
     */
    CopyrightDTO create(CopyrightDTO newCopyright);
    
    /**
     * Update copyright information.
     *
     * @param updatedCopyright updated copyright
     *
     * @return Updated Copyright
     */
    CopyrightDTO update(CopyrightDTO updatedCopyright);
    
    /**
     * Delete copyright by ID.
     *
     * @param copyrightId copyright identifier
     */
    void delete(long copyrightId);
    
    /**
     * Get copyrights with expiration dates between dates.
     *
     * @param from from what date
     * @param to   until what date
     *
     * @return List of copyrights
     */
    List<CopyrightDTO> copyrightsByDates(LocalDate from, LocalDate to);
    
    /**
     * Get the copyright of the company.
     *
     * @param companyName company name
     *
     * @return List of copyrights
     */
    List<CopyrightDTO> copyrightByCompanyName(String companyName);
}
