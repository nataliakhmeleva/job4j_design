package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {
    Store warehouse = new Warehouse();
    Store shop = new Shop();
    Store trash = new Trash();
    ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));

    @Test
    public void whenPercentExpiryLess25() {
        LocalDateTime expired = LocalDateTime.now().plusDays(10);
        LocalDateTime created = LocalDateTime.now().minusDays(2);
        Food milk = new Milk("moloko", expired, created, 84.10, 12.5);
        controlQuality.execute(milk);
        assertThat(warehouse.getAllFood()).contains(milk);
    }

    @Test
    public void whenPercentExpiryMore25AndLess75() {
        LocalDateTime expired = LocalDateTime.now().plusDays(10);
        LocalDateTime created = LocalDateTime.now().minusDays(8);
        Food meat = new Meat("meat", expired, created, 450.50, 25);
        controlQuality.execute(meat);
        assertThat(shop.getAllFood()).contains(meat);
    }

    @Test
    public void whenPercentExpiryMore75AndDoDiscount() {
        LocalDateTime expired = LocalDateTime.now().plusDays(1);
        LocalDateTime created = LocalDateTime.now().minusDays(10);
        Food meat = new Meat("meat", expired, created, 450.00, 25);
        controlQuality.execute(meat);
        assertThat(shop.getAllFood()).contains(meat);
        assertThat(shop.getAllFood().get(0).getPrice()).isEqualTo(337.50);
    }

    @Test
    public void whenPercentExpiryMore100() {
        LocalDateTime expired = LocalDateTime.now().minusDays(1);
        LocalDateTime created = LocalDateTime.now().minusDays(10);
        Food tomato = new Tomato("tomato", expired, created, 80.00, 36);
        controlQuality.execute(tomato);
        assertThat(trash.getAllFood()).contains(tomato);
    }

    @Test
    public void whenAllFoodInStorage() {
        Food milk = new Milk("moloko", LocalDateTime.now().plusDays(10), LocalDateTime.now().minusDays(2),
                84.10, 12.5);
        Food pock = new Meat("pock", LocalDateTime.now().plusDays(10), LocalDateTime.now().minusDays(8),
                450.00, 25);
        Food beef = new Meat("beef", LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(10),
                450.00, 25);
        Food tomato = new Tomato("tomato", LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(10),
                80.00, 36);
        controlQuality.execute(milk);
        controlQuality.execute(pock);
        controlQuality.execute(beef);
        controlQuality.execute(tomato);
        assertThat(warehouse.getAllFood()).contains(milk);
        assertThat(shop.getAllFood()).containsAll(List.of(pock, beef));
        assertThat(trash.getAllFood()).contains(tomato);
    }

    @Test
    public void whenAllFoodInStorageToResort() {
        Food milk = new Milk("moloko", LocalDateTime.now().plusDays(10), LocalDateTime.now().minusDays(2),
                84.10, 12.5);
        Food pock = new Meat("pock", LocalDateTime.now().plusDays(10), LocalDateTime.now().minusDays(8),
                450.00, 25);
        Food beef = new Meat("beef", LocalDateTime.now().plusDays(1), LocalDateTime.now().minusDays(10),
                450.00, 25);
        Food tomato = new Tomato("tomato", LocalDateTime.now().minusDays(1), LocalDateTime.now().minusDays(10),
                80.00, 36);
        controlQuality.execute(milk);
        controlQuality.execute(pock);
        controlQuality.execute(beef);
        controlQuality.execute(tomato);
        assertThat(warehouse.getAllFood()).contains(milk);
        assertThat(shop.getAllFood()).containsAll(List.of(pock, beef));
        assertThat(trash.getAllFood()).contains(tomato);
        controlQuality.resort();
        assertThat(warehouse.getAllFood()).contains(milk);
        assertThat(shop.getAllFood()).containsAll(List.of(pock, beef));
        assertThat(trash.getAllFood()).contains(tomato);
    }
}