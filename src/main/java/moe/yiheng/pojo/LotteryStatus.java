package moe.yiheng.pojo;

public enum LotteryStatus {

    ACTIVE(0), FINISHED(1), CANCLED(2);

    private int index;

    LotteryStatus(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
