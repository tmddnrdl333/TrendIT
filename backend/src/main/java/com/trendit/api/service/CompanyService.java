package com.trendit.api.service;

import com.trendit.db.entity.Company;
import com.trendit.db.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }

    public Page<Company> searchCompanyWithPageable(String companyName, Pageable pageable) {
        return companyRepository.findByCompanyNameContains(companyName, pageable);
    }
}
