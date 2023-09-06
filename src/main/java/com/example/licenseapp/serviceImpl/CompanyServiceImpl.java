package com.example.licenseapp.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.example.licenseapp.dto.CompanyDTO;
import com.example.licenseapp.mapper.CompanyMapper;
import com.example.licenseapp.model.Company;
import com.example.licenseapp.repository.CompanyRepository;
import com.example.licenseapp.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    
    private final CompanyMapper companyMapper;
    
    @Override
    public List<CompanyDTO> getAll() {
        log.debug("Getting all companies");
        
        List<Company> allCompanies = companyRepository.findAll();
        return companyMapper.ToDtoList(allCompanies);
    }
    
    @Override
    public CompanyDTO getById(long companyId) {
        log.debug("Getting a company with id = {}", companyId);
        
        Company companyEntity = Optional.ofNullable(companyRepository.findById(companyId))
                                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                                                                       "Company not found"));
        
        return companyMapper.toDTO(companyEntity);
    }
    
    @Override
    @Transactional
    /** Saving via HQL is not possible according to the documentation */
    public CompanyDTO create(CompanyDTO newCompany) {
        log.debug("Сохранение новой компании {}", newCompany);
        
        Company companyEntity = companyMapper.toEntity(newCompany);
        
        if (!CollectionUtils.isEmpty(companyRepository.findByName(companyEntity))) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                              "A company with this name already exists");
        }
        
        return companyMapper.toDTO(companyRepository.save(companyEntity));
    }
    
    @Override
    @Transactional
    public CompanyDTO update(CompanyDTO updatedCompany) {
        log.debug("Updating a company id = {}", updatedCompany.id());
        
        Company companyEntity = companyMapper.toEntity(updatedCompany);
        
        companyRepository.update(companyEntity);
        return updatedCompany;
    }
    
    @Override
    @Transactional
    public void delete(long companyId) {
        log.debug("Deleting company id = {}", companyId);
        
        companyRepository.deleteById(companyId);
    }
}
