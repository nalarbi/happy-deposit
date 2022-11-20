package walidjek.glady.company;

import java.util.Optional;

public interface CompanyRepository {

    Optional<Company> get(int id);

    void save(Company company);

    void delete(int id);
}
