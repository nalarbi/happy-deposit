package walidjek.glady.company.inmemory;

import walidjek.glady.company.Company;
import walidjek.glady.company.CompanyRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Singleton
public class InMemoryCompanyRepository implements CompanyRepository {

    private final Map<Integer, Company> companies = new HashMap<>();

    @Override
    public Optional<Company> get(int id) {
        Company company = this.companies.get(id);
        if (company == null) {
            return Optional.empty();
        }
        return Optional.of(company);
    }

    @Override
    public void save(Company company) {
        this.companies.put(company.getId(), company);
    }

    @Override
    public void delete(int id) {
        this.companies.remove(id);
    }
}
