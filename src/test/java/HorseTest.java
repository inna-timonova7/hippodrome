import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    @DisplayName("Check that IllegalArgument Exception is thrown if Constructor has null as first parameter")
    public void throwIllegalArgumentExceptionWithNullAsFirstParamInConstructor() {
        assertName();
    }

    @Test
    @DisplayName("Check that \"Name cannot be null.\" message is displayed if Constructor has null as first parameter")
    public void displayExceptionMessageWithNullAsFirstParamInConstructor() {
        IllegalArgumentException illArgException = assertName();
        assertEquals("Name cannot be null.", illArgException.getMessage());
    }

    @DisplayName("Check that Illegal Argument Exception is thrown if first parameter in Constructor is blank")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t"})
    public void throwIllegalArgumentExceptionWithBlankFirstParamInConstructor(String name) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 500.0, 200.0));
    }

    @DisplayName("Check that \"Name cannot be blank.\" message is displayed if first parameter in Constructor is blank")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "\t"})
    public void displayExceptionMessageWithBlankFirstParamInConstructor(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 500.0, 200.0));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Check that IllegalArgument Exception is thrown if second parameter in Constructor is negative")
    public void throwIllegalArgumentExceptionWithNegativeSecondParamInConstructor() {
        assertSpeed();
    }

    @Test
    @DisplayName("Check that \"Speed cannot be negative.\" message is displayed if second parameter in Constructor is negative")
    public void displayExceptionMessageWithWithNegativeSecondParamInConstructor() {
        IllegalArgumentException illArgException = assertSpeed();
        assertEquals("Speed cannot be negative.", illArgException.getMessage());
    }

    @Test
    @DisplayName("Check that IllegalArgument Exception is thrown if second parameter in Constructor is negative")
    public void throwIllegalArgumentExceptionWithNegativeThirdParamInConstructor() {
        assertDistance();
    }

    @Test
    @DisplayName("Check that \"Distance cannot be negative.\" message is displayed if second parameter in Constructor is negative")
    public void displayExceptionMessageWithWithNegativeThirdParamInConstructor() {
        IllegalArgumentException illArgException = assertDistance();
        assertEquals("Distance cannot be negative.", illArgException.getMessage());
    }

    @Test
    @DisplayName("Check that Name value equals the first param in Constructor")
    public void checkNameHasValueOfFirstParamInConstructor() {
        Horse horse = new Horse("Crow", 10.0, 15.0);
        assertEquals("Crow", horse.getName());
    }

    @Test
    @DisplayName("Check that Speed value equals the second param in Constructor")
    public void checkSpeedHasValueOfSecondParamInConstructor() {
        Horse horse = new Horse("Eagle", 14.0, 15.0);
        assertEquals(14.0, horse.getSpeed());
    }

    @Test
    @DisplayName("Check that Distance value equals the third param in Constructor")
    public void checkDistanceHasValueOfThirdParamInConstructor() {
        Horse horse = new Horse("Seagull", 10.0, 100.0);
        assertEquals(100.0, horse.getDistance());
    }

    @Test
    @DisplayName("Check that Distance value equals zero when Two params Constructor is used")
    public void checkDistanceValueIsZero() {
        Horse horse = new Horse("Crow", 10.0);
        assertEquals(0, horse.getDistance());
    }

    @Test
    @DisplayName("Check that getRandom method is invoked with parameters 0.2 and 0.9")
    public void checkGetRandomDoubleIsInvoked() {
        Horse horse = new Horse("Bamby", 1.0, 4.0);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @DisplayName("Check that Distance result equals the distance + speed * getRandomDouble(0.2, 0.9) formula")
    @ValueSource(doubles = {4.0, 5.0})
    public void checkDistanceResultCompliesWithFormula(double numbers) {
        Horse horse = new Horse("Spike", 13.0, 5.0);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(numbers);
            double distance = horse.getDistance();
            horse.move();
            assertEquals(distance + horse.getSpeed() * numbers, horse.getDistance());
        }
    }

    private IllegalArgumentException assertName() {
        return assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 160.0, 200.0));
    }

    private IllegalArgumentException assertSpeed() {
        return assertThrows(IllegalArgumentException.class,
                () -> new Horse("Seabiscuit", -100.0, 200.0));
    }

    private IllegalArgumentException assertDistance() {
        return assertThrows(IllegalArgumentException.class,
                () -> new Horse("Redneck", 100.0, -100.0));
    }
}
