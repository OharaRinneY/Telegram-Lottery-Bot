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

    public static LotteryStatus getByIndex(Integer index) {
        for (LotteryStatus lotteryStatus : LotteryStatus.values()) {
            if (index.equals(lotteryStatus.index)) {
                return lotteryStatus;
            }
        }
        throw new IllegalArgumentException("No enum found");
    }
}
