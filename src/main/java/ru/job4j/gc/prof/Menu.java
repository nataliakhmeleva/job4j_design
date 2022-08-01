package ru.job4j.gc.prof;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MILLIS;

public class Menu {
    RandomArray randomArray = new RandomArray(new Random());

    public void init(Scanner scanner) {
        boolean run = true;
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.parseInt(scanner.nextLine());
            if (select == 1) {
                randomArray.insert(250_000);
                System.out.println("Массив создан");
            } else if (select == 2) {
                if (randomArray.isEmpty()) {
                    System.out.println("Error");
                } else {
                    Sort bubbleSort = new BubbleSort();
                    LocalDateTime prev = LocalDateTime.now();
                    bubbleSort.sort(randomArray);
                    System.out.println("Сортировка пузырьком заняла " + MILLIS.between(prev, LocalDateTime.now()) + " миллисекунд.");
                }
            } else if (select == 3) {
                if (randomArray.isEmpty()) {
                    System.out.println("Error");
                } else {
                    Sort insertSort = new InsertSort();
                    LocalDateTime prev = LocalDateTime.now();
                    insertSort.sort(randomArray);
                    System.out.println("Сортировка вставками заняла " + MILLIS.between(prev, LocalDateTime.now()) + " миллисекунд.");
                }
            } else if (select == 4) {
                if (randomArray.isEmpty()) {
                    System.out.println("Error");
                } else {
                    Sort mergeSort = new MergeSort();
                    LocalDateTime prev = LocalDateTime.now();
                    mergeSort.sort(randomArray);
                    System.out.println("Сортировка слиянием заняла " + MILLIS.between(prev, LocalDateTime.now()) + " миллисекунд.");
                }
            } else if (select == 5) {
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.\n" + "1.Создание массива\n" + "2.Сортировка пузырьком\n" + "3. Сортировка вставками\n" + "4. Сортировка слиянием\n" + "5. Выход\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu start = new Menu();
        start.init(scanner);
    }
}
