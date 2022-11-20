package walidjek.glady.company;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompany(int companyId) throws CompanyNotFoundException {
        Optional<Company> companyOptional = companyRepository.get(companyId);
        return companyOptional.orElseThrow(() ->
                new CompanyNotFoundException(String.format(("Company with id [%s] not found"), companyId))
        );
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public void deleteCompany(int companyId) {
        companyRepository.delete(companyId);
    }

    public boolean isDistributionAllowed(Company company, int amount) {
        return company.getBalance() >= amount;
    }

}
