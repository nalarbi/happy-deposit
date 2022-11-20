package walidjek.glady.deposit;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import walidjek.glady.company.Company;
import walidjek.glady.company.CompanyNotFoundException;
import walidjek.glady.company.CompanyService;
import walidjek.glady.user.User;
import walidjek.glady.user.UserNotFoundException;
import walidjek.glady.user.UserService;

import javax.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test.
 * Testing the distribute function through the different layers and the framework.
 */
@MicronautTest
public class DepositServiceTest {

    private static final int COMPANY_ID = 123;
    private static final int USER_ID = 456;

    @Inject
    DepositService depositService;
    @Inject
    CompanyService companyService;
    @Inject
    UserService userService;

    @BeforeEach
    void setup() {
        Company company = new Company();
        company.setId(COMPANY_ID);
        company.setName("Glady");
        company.setBalance(1000);
        this.companyService.saveCompany(company);

        User user = new User();
        user.setId(USER_ID);
        user.setName("Walid");
        this.userService.saveUser(user);
    }

    @AfterEach
    void clean() {
        this.companyService.deleteCompany(COMPANY_ID);
        this.userService.deleteUser(456);
    }

    @Test
    @DisplayName("Simple distribute deposit")
    void simpleDistributeDeposit() {
        this.depositService.distribute(COMPANY_ID, USER_ID, 300, DepositType.GIFT);

        User user = this.userService.getUser(USER_ID);
        List<Deposit> deposits = user.getDeposits();
        assertNotNull(deposits);
        assertEquals(deposits.size(), 1);

        Deposit deposit = deposits.get(0);
        assertEquals(deposit.getDepositType(), DepositType.GIFT);
        assertEquals(deposit.getAmount(), 300);
        assertNotNull(deposit.getUser());
        assertEquals(deposit.getUser().getId(), USER_ID);
        assertNotNull(deposit.getCompany());
        assertEquals(deposit.getCompany().getId(), COMPANY_ID);

        Company company = this.companyService.getCompany(COMPANY_ID);
        assertEquals(company.getBalance(), 700); // 1000 - 300
    }

    @Test
    @DisplayName("Distribute with insufficient balance")
    void distributeWithInsufficientBalance() {
        Throwable exception = assertThrows(InsufficientBalanceException.class, () ->
                this.depositService.distribute(COMPANY_ID, USER_ID, 1500, DepositType.GIFT)
        );
        assertEquals(
                String.format("Company [%s] has insufficient balance to distribute [%s]", COMPANY_ID, 1500),
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Distribute for unknown user")
    void distributeForUnknownUser() {
        Throwable exception = assertThrows(UserNotFoundException.class, () ->
                this.depositService.distribute(COMPANY_ID, 111, 200, DepositType.GIFT)
        );
        assertEquals(
                String.format(("User with id [%s] not found"), 111),
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("Distribute from uknown company")
    void distributeFromUnknownCompany() {
        Throwable exception = assertThrows(CompanyNotFoundException.class, () ->
                this.depositService.distribute(222, USER_ID, 200, DepositType.GIFT)
        );
        assertEquals(
                String.format(("Company with id [%s] not found"), 222),
                exception.getMessage()
        );
    }
}
