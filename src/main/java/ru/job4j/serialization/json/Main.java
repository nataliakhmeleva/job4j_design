package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {
        final Account account = new Account(true, 123456789,
            new Passport("Ivan Ivanov", 35, 40501639),
            new String[]{"SberBank", "Moscow"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(account));

        final String accountJson = "{" + "\"account\": true," + "\"numberOfAccount\": 987654321,"
            + "\"passport\":" + "{" + "\"name\": \"Alexandr Pushkin\"," + "\"age\": 30,"
            + "\"numberPassport\": 80906702" + "}," + "\"requisites\":"
            + "[\"VTBBank\",\"Saint Petersburg\"]"
            + "}";

        final Account accountMod = gson.fromJson(accountJson, Account.class);
        System.out.println(accountMod);
    }


}