package walidjek.glady.deposit;

import walidjek.glady.company.Company;
import walidjek.glady.company.CompanyNotFoundException;
import walidjek.glady.company.CompanyService;
import walidjek.glady.user.User;
import walidjek.glady.user.UserNotFoundException;
import walidjek.glady.user.UserService;

import javax.inject.Singleton;

@Singleton
public class DepositService {

    private final CompanyService companyService;
    private final UserService userService;

    DepositService(CompanyService companyService,
                   UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    public void distribute(int companyId, int userId, int amount, DepositType depositType)
            throws CompanyNotFoundException, UserNotFoundException, InsufficientBalanceException {
        Company company = companyService.getCompany(companyId);
        if (!companyService.isDistributionAllowed(company, amount)) {
            throw new InsufficientBalanceException(
                    String.format("Company [%s] has insufficient balance to distribute [%s]", companyId, amount)
            );
        }

        User user = userService.getUser(userId);

        Deposit deposit = DepositFactory.createDeposit(depositType, amount, user, company);

        company.setBalance(company.getBalance() - amount);
        user.addDeposit(deposit);

        companyService.saveCompany(company);
        userService.saveUser(user);
    }
}
