package walidjek.glady.deposit;

import walidjek.glady.company.Company;
import walidjek.glady.user.User;

import java.util.Date;

public abstract class Deposit {

    private int amount;
    private Date creationDate;
    private User user;
    private Company company;

    protected Deposit(int amount, User user, Company company, Date creationDate) {
        this.amount = amount;
        this.user = user;
        this.company = company;
        this.creationDate = creationDate;
    }

    abstract public Date getExpirationDate();

    abstract public DepositType getDepositType();

    public boolean isValid() {
        return new Date().before(this.getExpirationDate());
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

