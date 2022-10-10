package ru.job4j.ood.isp;

public class Bird implements Action {
    @Override
    public void eat() {
        System.out.println("Птица ест зерно.");
    }

    @Override
    public void sleep() {
        System.out.println("Птица спит в темное время суток.");
    }

    @Override
    public void run() {
        System.out.println("Птица бегает в случае опасности.");
    }

    /**
     * Нарушает принцип ISP, т.к. птица не умеет читать книги, соотвественно, не верно выделена абстракция.
     */
    @Override
    public void readBook() {
        throw new UnsupportedOperationException();
    }

    /**
     * Нарушает принцип ISP, т.к. умение летать должно быть выделено в отдельный интерфейс.
     */
    public void fly() {
        System.out.println("Птица умеет летать.");
    }
}
