package ru.job4j.serialization.xml;


import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class Account {

    @XmlAttribute
    private boolean deposit;
    @XmlAttribute
    private int numberOfAccount;
    private Passport passport;
    @XmlElementWrapper(name = "requisites")
    @XmlElement(name = "requisite")
    private String[] requisites;

    public Account() {
    }

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

    public static void main(String[] args) throws Exception {
        Account account = new Account(true, 123456789,
            new Passport("Ivan Ivanov", 35, 40501639),
            new String[]{"SberBank", "Moscow"});

        JAXBContext context = JAXBContext.newInstance(Account.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(account, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Account result = (Account) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
