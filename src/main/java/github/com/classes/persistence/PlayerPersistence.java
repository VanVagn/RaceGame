package github.com.classes.persistence;
import github.com.classes.database.MyDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static github.com.classes.constants.Consts.TABLE;

public class PlayerPersistence {
    private MyDatabase myDatabase = MyDatabase.getInstance();
    public void deleteAllTimes(String table) {
        String sql = """
                delete 
                from Race.%s
                """;
        myDatabase.execute(String.format(sql, table));
    }

    public void addTime(String table, long time) {
        String sql = """
                insert into Race.%s
                (gameTime)
                values (%d)
                """;
        myDatabase.execute(String.format(sql, table, time));
    }

    public ArrayList<Long> getAll() {
        List<HashMap<Integer, Long>> list = myDatabase.selectALL(TABLE);
        ArrayList<Long> times = new ArrayList<>();
        int index = 1;
        long time;
        if (list.size() > 0) {
            for (HashMap<Integer, Long> help : list) {
                time = help.get(index);
                times.add(time);
                index += 1;
            }
        }
        return times;
    }

    public long getById(String table, int id) {
        HashMap<Integer, Long> hashMap = myDatabase.selectById(table, id);
        return hashMap.get(id);
    }

    public void updateTime(String table, int id, long time) {
        String sql = """
                update Race.%s
                set gameTime = %d
                where id = %d
                """;
        myDatabase.execute(String.format(sql, table, time, id));
    }

    public void restartId(String table) {
        String sql = """
                ALTER SEQUENCE Race.%s_id_seq RESTART WITH 1;
                """;
        myDatabase.execute(String.format(sql, table));
    }

    public MyDatabase getMyDatabase() {
        return myDatabase;
    }
}
