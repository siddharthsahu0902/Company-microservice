package com.jobapp.companyms.company.impl;


import com.jobapp.companyms.company.Company;
import com.jobapp.companyms.company.CompanyRepository;
import com.jobapp.companyms.company.CompanyService;
import com.jobapp.companyms.company.dto.ReviewMessage;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            Company existingCompany = optionalCompany.get();
            existingCompany.setName(company.getName());
            existingCompany.setDescription(company.getDescription());;
            companyRepository.save(existingCompany);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if(optionalCompany.isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompany(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
    }

    @Override
    public void createCompany(@NonNull Company company) {
        companyRepository.save(company);
    }

}
