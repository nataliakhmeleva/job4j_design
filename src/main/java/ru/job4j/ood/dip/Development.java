package ru.job4j.ood.dip;

public class Development {
    /**
     * Нарушает принцип DIP, т.к. поля класса не являются абстракцией, а зависят от реализации.
     */
    private Programmer programmer;
    private Designer designer;
    private Analyst analyst;
    private Website website;

    /**
     * Нарушает принцип DIP, т.к. аргументы конструктора и метода класса не являются абстракцией, а зависят от реализации.
     */
    public Development(Programmer programmer, Designer designer, Analyst analyst, Website website) {
        this.programmer = programmer;
        this.designer = designer;
        this.analyst = analyst;
        this.website = website;
    }

    public void collectAnalytics(Analyst analyst, Website website) {

    }

    /**
     * Нарушает принцип DIP, т.к. возвращаемый тип метода класса не являются абстракцией, а зависят от реализации.
     */
    public Designer draw(Designer designer) {
        this.designer = designer;
        return designer;
    }
}
