package ru.job4j.serialization.json;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        JSONObject jsonPassport = new JSONObject(
            "{\"name\": \"Alexandr Pushkin\",\"age\": 30,\"numberPassport\": 80906702}");

        ArrayList<String> list = new ArrayList<>();
        list.add("VTBBank");
        list.add("Saint Petersburg");
        JSONArray jsonRequisites = new JSONArray(list);

        final Account account = new Account(true, 123456789,
            new Passport("Ivan Ivanov", 35, 40501639),
            new String[]{"SberBank", "Moscow"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("deposit", account.isDeposit());
        jsonObject.put("numberOfAccount", account.getNumberOfAccount());
        jsonObject.put("passport", jsonPassport);
        jsonObject.put("requisites", jsonRequisites);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(account).toString());
    }


}