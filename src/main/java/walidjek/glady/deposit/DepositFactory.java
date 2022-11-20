package walidjek.glady.deposit;

import walidjek.glady.company.Company;
import walidjek.glady.user.User;

import java.util.Date;

public class DepositFactory {

    private DepositFactory() {}

    public static Deposit createDeposit(DepositType depositType,
                                        int amount,
                                        User user,
                                        Company company) throws UnhandledDepositTypeException {
        switch (depositType) {
            case GIFT:
                return new GiftDeposit(amount, user, company, new Date());
            case MEAL:
                return new MealDeposit(amount, user, company, new Date());
            default:
                throw new UnhandledDepositTypeException(String.format("Deposit type [%s] not handled", depositType.getValue()));
        }
    }

}
