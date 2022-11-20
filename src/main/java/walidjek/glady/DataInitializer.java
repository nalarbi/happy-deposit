package walidjek.glady;

import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import walidjek.glady.company.Company;
import walidjek.glady.company.CompanyService;
import walidjek.glady.deposit.GiftDeposit;
import walidjek.glady.deposit.MealDeposit;
import walidjek.glady.user.User;
import walidjek.glady.user.UserService;

import javax.inject.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Initialize a data set for the rest api manual tests
 */
@Singleton
public class DataInitializer implements ApplicationEventListener<StartupEvent> {

    private final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final static int COMPANY_ID = 123;
    private final static int USER_ID = 456;

    private final CompanyService companyService;
    private final UserService userService;

    private User user;
    private Company company;

    public DataInitializer(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(StartupEvent event) {
        init();
    }

    public void reset() {
        this.companyService.deleteCompany(COMPANY_ID);
        this.userService.deleteUser(USER_ID);
        init();
    }

    private void init() {
        this.company = new Company();
        this.user = new User();

        this.company.setId(COMPANY_ID);
        this.company.setName("Glady");
        this.company.setBalance(1000);
        this.companyService.saveCompany(this.company);

        this.user.setId(USER_ID);
        this.user.setName("Walid");

        addGiftDeposit(100, "30-07-2020");
        addGiftDeposit(50, "11-11-2022");
        addGiftDeposit(200, "01-05-2022");

        addMealDeposit(20, "14-03-2022");
        addMealDeposit(30, "20-06-2022");
        addMealDeposit(40, "11-08-2022");

        this.userService.saveUser(this.user);
    }

    private void addGiftDeposit(int amount, String dateAsString) {
        Date date;
        try {
            date = formatter.parse(dateAsString);
        } catch (ParseException exception) {
            date = new Date();
        }
        GiftDeposit deposit =new GiftDeposit(
                amount,
                this.user,
                this.company,
                date
        );
        this.user.addDeposit(deposit);
    }

    private void addMealDeposit(int amount, String dateAsString) {
        Date date;
        try {
            date = formatter.parse(dateAsString);
        } catch (ParseException exception) {
            date = new Date();
        }
        MealDeposit deposit =new MealDeposit(
                amount,
                this.user,
                this.company,
                date
        );
        this.user.addDeposit(deposit);
    }
}
