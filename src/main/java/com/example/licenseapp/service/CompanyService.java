package com.example.licenseapp.service;

import java.util.List;

import com.example.licenseapp.dto.CompanyDTO;

/**
 * A service for interacting with companies.
 *
 * Implementation via HQL
 */
public interface CompanyService {
    
    /**
     * Get all companies.
     *
     * @return list of companies
     */
    List<CompanyDTO> getAll();
    
    /**
     * Get a company by ID.
     *
     * @param companyId company ID
     *
     * @return Company
     */
    CompanyDTO getById(long companyId);
    
    /**
     * Save new company.
     *
     * @param newCompany new company
     *
     * @return Saved company
     */
    CompanyDTO create(CompanyDTO newCompany);
    
    /**
     * Update company info.
     *
     * @param updatedCompany new information about the company
     *
     * @return The updated essence of the company
     */
    CompanyDTO update(CompanyDTO updatedCompany);
    
    /**
     * Delete a saved company.
     *
     * @param companyId Company ID
     */
    void delete(long companyId);
}
