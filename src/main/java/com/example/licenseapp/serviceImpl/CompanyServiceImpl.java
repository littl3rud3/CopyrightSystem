package com.example.licenseapp.serviceImpl;

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

import java.util.List;
import java.util.Optional;

/**
 * Сервис для взаимодействия с компаниями.
 *
 * Реализация через HQL
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDTO> getAll() {
        log.debug("Получение всех компаний");

        List<Company> allCompanies = companyRepository.findAll();
        return companyMapper.ToDtoList(allCompanies);
    }

    @Override
    public CompanyDTO getById(long companyId) {
        log.debug("Получение компании с id = {}", companyId);

        Company companyEntity = Optional.ofNullable(companyRepository.findById(companyId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Компания не найдена"));

        return companyMapper.toDTO(companyEntity);
    }

    @Override
    @Transactional
    /** Сохранение через HQL невозможно согласно документации */
    public CompanyDTO create(CompanyDTO newCompany) {
        log.debug("Сохранение новой компании {}", newCompany);

        Company companyEntity = companyMapper.toEntity(newCompany);

        if (!CollectionUtils.isEmpty(companyRepository.findByName(companyEntity))) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Компания с таким названием уже существует");
        }

        return companyMapper.toDTO(companyRepository.save(companyEntity));
    }

    @Override
    @Transactional
    public CompanyDTO update(CompanyDTO updatedCompany) {
        log.debug("Обновление компании с id = {}", updatedCompany.id());

        Company companyEntity = companyMapper.toEntity(updatedCompany);

        companyRepository.update(companyEntity);
        return updatedCompany;
    }

    @Override
    @Transactional
    public void delete(long companyId) {
        log.debug("Удаление компании с id = {}", companyId);

        companyRepository.deleteById(companyId);
    }
}
