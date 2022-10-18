package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParkTest {
    Car car1 = new PassengerCar();
    Car car2 = new PassengerCar();
    Car car3 = new Truck(2);
    Car car4 = new Truck(4);
    Car car5 = new Truck(5);

    @Test
    public void whenEnoughPlacesFor2PassengerCarAnd2Truck() {
        Park park = new Park(List.of(new UniversalParking(2, 2)));
        assertThat(park.execute(car1)).isTrue();
        assertThat(park.execute(car2)).isTrue();
        assertThat(park.execute(car3)).isTrue();
        assertThat(park.execute(car4)).isTrue();
    }


    @Test
    public void whenEnoughPlacesFor2Truck() {
        Park park = new Park(List.of(new UniversalParking(2, 1)));
        assertThat(park.execute(car3)).isTrue();
        assertThat(park.execute(car4)).isFalse();
    }

    @Test
    public void whenEnoughPlacesOnlyPassengerCarParking() {
        Park park = new Park(List.of(new UniversalParking(3, 0)));
        assertThat(park.execute(car1)).isTrue();
        assertThat(park.execute(car3)).isTrue();
    }

    @Test
    public void whenNoPlacesForPassengerCar() {
        Park park = new Park(List.of(new UniversalParking(0, 1)));
        assertThat(park.execute(car1)).isFalse();
        assertThat(park.execute(car3)).isTrue();
    }

    @Test
    public void whenNoEnoughPlacesForAllCars() {
        Park park = new Park(List.of(new UniversalParking(2, 1)));
        assertThat(park.execute(car1)).isTrue();
        assertThat(park.execute(car2)).isTrue();
        assertThat(park.execute(car3)).isTrue();
        assertThat(park.execute(car4)).isFalse();
        assertThat(park.execute(car5)).isFalse();
    }
}