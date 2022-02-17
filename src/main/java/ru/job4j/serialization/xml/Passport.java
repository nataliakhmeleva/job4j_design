package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passport")
public class Passport {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private int age;
    @XmlAttribute
    private int numberPassport;

    public Passport() {
    }

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
}
