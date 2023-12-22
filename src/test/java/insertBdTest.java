import github.com.classes.persistence.PlayerPersistence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class insertBdTest {
    PlayerPersistence playerPersistence = new PlayerPersistence();
    String testBd = "table_test";
    private void createTestBd() {
        String sql = """
                create table if not exists Race.table_test (
                    id serial primary key,
                    gameTime real
              
                )
                """;
        playerPersistence.getMyDatabase().execute(sql);
    }

    @Test
    public void checkInsert() {
        createTestBd();
        long expected = 100;
        playerPersistence.addTime(testBd, expected);
        long actual = playerPersistence.getById(testBd,1);
        then(actual == expected);
    }
}
