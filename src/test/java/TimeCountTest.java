import github.com.classes.raceClasses.TimeCount;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.then;


public class TimeCountTest {
    TimeCount timeCount = new TimeCount();
    @Test
    public void checkTime() throws InterruptedException {
        long expected = 1;
        long startTime = timeCount.getBeginTime();
        Thread.sleep(1000);
        long endTime = timeCount.getResultTime();
        long result = endTime - startTime;
        then(result).equals(expected);
    }
}
