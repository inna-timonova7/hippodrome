import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

//    @Disabled
    @Test
    @DisplayName("Check that execution is no longer that 22 seconds")
    @Timeout(value = 22)
    @SneakyThrows
    void executionMainMethodNoMoreThan22Sec(){
        Main.main(null);
    }
}
