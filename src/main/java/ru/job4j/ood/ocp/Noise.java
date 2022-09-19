package ru.job4j.ood.ocp;

/**
 * Нарушает принцип OCP. Нарушено отношение «is A» наследование, т.к. человек не является частью машины.
 * Необходимо использовать интерфейсы.
 */

public class Noise {
    private static class Car {
        public String sound() {
            return "beep-beep";
        }
    }

    private static class Person extends Car {
        @Override
        public String sound() {
            return "yahoo!";
        }
    }
}
