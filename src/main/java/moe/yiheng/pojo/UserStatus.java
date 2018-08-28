package moe.yiheng.pojo;

public enum UserStatus {
    // NOTHING:没有操作
    // CREATING:正在创建抽奖
    NOTHING(0),CREATING(1);
    private Integer index;

    public Integer getIndex() {
        return index;
    }

    UserStatus(Integer index) {
        this.index = index;
    }

    public static UserStatus getByIndex(Integer index) {
        for (UserStatus userStatus : UserStatus.values()) {
            if (index.equals(userStatus.index)) {
                return userStatus;
            }
        }
        throw new IllegalArgumentException("No enum found");
    }
}
