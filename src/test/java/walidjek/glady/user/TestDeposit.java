package walidjek.glady.user;

import walidjek.glady.deposit.Deposit;
import walidjek.glady.deposit.DepositType;

import java.util.Date;

/**
 * A Deposit implementation used for unit tests
 */
public class TestDeposit extends Deposit {

    private final boolean valid;
    private final DepositType depositType;

    protected TestDeposit(int amount, DepositType depositType, boolean valid) {
        super(amount, null, null, null);
        this.valid = valid;
        this.depositType = depositType;
    }

    @Override
    public boolean isValid() {
        return this.valid;
    }

    @Override
    public Date getExpirationDate() {
        return null;
    }

    @Override
    public DepositType getDepositType() {
        return this.depositType;
    }
}
