package github.com.classes.raceClasses;

import static github.com.classes.constants.Consts.KOEF_TIME;

public class TimeCount {
    private long resultTime;
    private long beginTime;

    public void startTime() {
        beginTime = System.currentTimeMillis();
    }

    public long getResultTime() {
        resultTime = System.currentTimeMillis() - beginTime;
        return resultTime / KOEF_TIME;
    }

    public long getBeginTime() {
        return beginTime / KOEF_TIME;
    }
}
