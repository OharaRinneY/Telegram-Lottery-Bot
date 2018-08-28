package moe.yiheng.pojo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Lottery {
    private String uuid;
    private User createdByUser;
    private String name;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Set<User> joinedUsers = new HashSet<User>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getJoinedUsers() {
        return joinedUsers;
    }

    public void setJoinedUsers(Set<User> joinedUsers) {
        this.joinedUsers = joinedUsers;
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "uuid='" + uuid + '\'' +
                ", createdByUser=" + createdByUser +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery = (Lottery) o;
        return Objects.equals(uuid, lottery.uuid) &&
                Objects.equals(createdByUser, lottery.createdByUser) &&
                Objects.equals(name, lottery.name) &&
                Objects.equals(status, lottery.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, createdByUser, name, status);
    }
}
