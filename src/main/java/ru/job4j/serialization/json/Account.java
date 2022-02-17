package ru.job4j.serialization.json;

import java.util.Arrays;

public class Account {

    private final boolean deposit;
    private final int numberOfAccount;
    private final Passport passport;
    private final String[] requisites;

    public Account(boolean deposit, int numberOfAccount, Passport passport,
        String[] requisites) {
        this.deposit = deposit;
        this.numberOfAccount = numberOfAccount;
        this.passport = passport;
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return "Account{" + "deposit=" + deposit + ", numberOfAccount=" + numberOfAccount
            + ", passport=" + passport + ", requisites=" + Arrays.toString(requisites) + '}';
    }

    public boolean isDeposit() {
        return deposit;
    }

    public int getNumberOfAccount() {
        return numberOfAccount;
    }

    public Passport getPassport() {
        return passport;
    }

    public String[] getRequisites() {
        return requisites;
    }
}
