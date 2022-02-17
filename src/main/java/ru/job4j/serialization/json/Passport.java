package ru.job4j.serialization.json;

public class Passport {

    private final String name;
    private final int age;
    private final int numberPassport;

    public Passport(String name, int age, int numberPassport) {
        this.name = name;
        this.age = age;
        this.numberPassport = numberPassport;
    }

    @Override
    public String toString() {
        return "Passport{" + "name='" + name + '\'' + ", age=" + age + ", numberPassport="
            + numberPassport + '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNumberPassport() {
        return numberPassport;
    }
}
