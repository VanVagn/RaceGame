package github.com.classes.raceClasses;

public class TimeCount {
    private long resultTime;
    private long beginTime;
    public void startTime() {
        beginTime = System.currentTimeMillis();
    }
    public long getResultTime() {
        resultTime = System.currentTimeMillis() - beginTime;
        return resultTime / 1000;
    }

    public long getBeginTime() {
        return beginTime / 1000;
    }
}
