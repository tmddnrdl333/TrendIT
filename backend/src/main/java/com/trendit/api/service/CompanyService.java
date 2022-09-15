package com.trendit.api.service;

import com.trendit.db.entity.Company;
import com.trendit.db.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public List<Company> getCompanyList() {
        return companyRepository.findAll();
    }
}
