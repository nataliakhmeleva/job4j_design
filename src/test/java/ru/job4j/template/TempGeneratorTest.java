package ru.job4j.template;

<<<<<<< HEAD
import org.junit.jupiter.api.Disabled;
=======
>>>>>>> origin/master
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

<<<<<<< HEAD
@Disabled
=======
>>>>>>> origin/master
class TempGeneratorTest {
    Generator generator = new TempGenerator();
    Map<String, String> maps = new HashMap<>();

    @Test
    public void whenProduce() {
        maps = Map.of("name", "Natalia", "subject", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThat(generator.produce(template, maps)).isEqualTo("I am a Natalia, Who are you? ");
    }

    @Test
    public void whenIncorrectTemplate() {
        maps = Map.of("name", "Natalia", "subject", "you");
        String template = "I am a ${name}, Who are ${ha-ha}? ";
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, maps);
        });
    }

    @Test
    public void whenIncorrectKey() {
        maps = Map.of("name", "Natalia", "ha-ha", "you");
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, maps);
        });
    }

    @Test
    public void whenExtraKey() {
        maps = Map.of("name", "Natalia", "ha-ha", "you", "phone", "123-45-67");
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, maps);
        });
    }

    @Test
    public void whenMapIsEmpty() {
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThrows(IllegalArgumentException.class, () -> {
            generator.produce(template, maps);
        });
    }

    @Test
    public void whenValueIsNull() {
        maps = Map.of("name", "Natalia", "subject", null);
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThrows(NullPointerException.class, () -> {
            generator.produce(template, maps);
        });
    }
}