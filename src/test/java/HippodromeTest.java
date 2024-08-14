import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    @DisplayName("Check that IllegalArgument Exception is thrown if Constructor is null")
    public void throwIllegalArgumentExceptionWithNullAsConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Check that \"Horses cannot be null.\" message is displayed if Constructor is null")
    public void displayExceptionMessageWithNullAsConstructor() {
        IllegalArgumentException illArgException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", illArgException.getMessage());
    }

    @Test
    @DisplayName("Check that IllegalArgument Exception is thrown if Constructor has empty list")
    public void throwIllegalArgumantExceptionWithEmptyListInConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    @DisplayName("Check that \"Horses cannot be empty.\" message is displayed if Constructor has empty list")
    public void displayExceptionMessageWithEmptyListInConstructor() {
        IllegalArgumentException illArgException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", illArgException.getMessage());
    }

    @Test
    @DisplayName("Check that List of Horses in Hippodrome equals the list of Horses in hippodrome constructor")
    public void hetListOfHorsesInHippodromeEqualsListOfHorsesInConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse " + i, 5.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    @DisplayName("Check move() method is called for every Horse in Hippodrome")
    public void moveMethodIsCalledForEveryHorseInHippodrome() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockedHorse = mock(Horse.class);
            horses.add(mockedHorse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse mockedHorse : horses){
            verify(mockedHorse).move();
        }
    }

    @Test
    @DisplayName("Check a Horse with max distance")
    public void getHorseWithMaxDistanceAsWinner() {
        List<Horse> horses = List.of(
                new Horse("Red", 5.0, 10.0),
                new Horse("White", 4.5, 9.5),
                new Horse("Black", 6.2, 11.0),
                new Horse("Sand", 3.0, 8.5)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse expectedWinner = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        Horse trueWinner = hippodrome.getWinner();

        assertEquals(expectedWinner, trueWinner);
    }
}
