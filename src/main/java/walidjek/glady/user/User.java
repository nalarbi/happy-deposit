package walidjek.glady.user;

import walidjek.glady.deposit.Deposit;

import java.util.ArrayList;
import java.util.List;

public class User {

    private Integer id;
    private String name;
    private final List<Deposit> deposits = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Deposit> getDeposits() {
        return new ArrayList<>(deposits);
    }

    public void addDeposit(Deposit deposit) {
        this.deposits.add(deposit);
    }
}
