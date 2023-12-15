import github.com.classes.persistence.PlayerPersistence;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

public class updateBdTest {
    String nameTable = "update";
    PlayerPersistence playerPersistence = new PlayerPersistence();
    private void createTestBd() {
        String sql = """
                create table if not exists Race.update (
                    id serial primary key,
                    gameTime real
              
                )
                """;
        playerPersistence.getMyDatabase().execute(sql);
    }

    @Test
    public void checkUpdate() {
        createTestBd();
        long firstTime = 10;
        playerPersistence.addTime(nameTable, firstTime);
        long expected = 100;
        playerPersistence.updateTime(nameTable, 1, expected);
        long actual = playerPersistence.getById(nameTable, 1);
        then(expected == actual);
    }
}
