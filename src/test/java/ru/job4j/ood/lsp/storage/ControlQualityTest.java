package ru.job4j.ood.lsp.storage;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    List<Store> storeList = List.of(new Warehouse(), new Shop(), new Trash());
    ControlQuality controlQuality = new ControlQuality(storeList);

    @Test
    public void whenPercentExpiryLess25() {
        LocalDateTime expired = LocalDateTime.of(2022, Month.NOVEMBER, 15, 18, 40);
        LocalDateTime created = LocalDateTime.of(2022, Month.OCTOBER, 5, 17, 38);
        Food milk = new Milk("moloko", expired, created, 84.10, 12.5);
        controlQuality.execute(milk);
        assertThat(storeList.get(0).getAllFood().get(0).getName()).isEqualTo("moloko");
    }

    @Test
    public void whenPercentExpiryMore25AndLess75() {
        LocalDateTime expired = LocalDateTime.of(2022, Month.NOVEMBER, 15, 18, 40);
        LocalDateTime created = LocalDateTime.of(2022, Month.SEPTEMBER, 12, 2, 14);
        Food meat = new Meat("meat", expired, created, 450.50, 25);
        controlQuality.execute(meat);
        assertThat(storeList.get(1).getAllFood().get(0).getName()).isEqualTo("meat");
    }

    @Test
    public void whenPercentExpiryMore75AndDoDiscount() {
        LocalDateTime expired = LocalDateTime.of(2022, Month.OCTOBER, 15, 18, 40);
        LocalDateTime created = LocalDateTime.of(2022, Month.SEPTEMBER, 12, 2, 14);
        Food meat = new Meat("meat", expired, created, 450.00, 25);
        controlQuality.execute(meat);
        assertThat(storeList.get(1).getAllFood().get(0).getName()).isEqualTo("meat");
        assertThat(storeList.get(1).getAllFood().get(0).getPrice()).isEqualTo(337.50);
    }

    @Test
    public void whenPercentExpiryMore100() {
        LocalDateTime expired = LocalDateTime.of(2022, Month.OCTOBER, 6, 22, 45);
        LocalDateTime created = LocalDateTime.of(2022, Month.SEPTEMBER, 28, 14, 50);
        Food tomato = new Tomato("tomato", expired, created, 80.00, 36);
        controlQuality.execute(tomato);
        assertThat(storeList.get(2).getAllFood().get(0).getName()).isEqualTo("tomato");
    }
}