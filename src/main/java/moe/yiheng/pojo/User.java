package moe.yiheng.pojo;

import java.util.Objects;
import java.util.Set;

public class User {
    private Integer id;
    private String username;
    private String firstname;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    private Set<Lottery> joinedLotteries;

    private Set<Lottery> createdLotteries;

    public Set<Lottery> getJoinedLotteries() {
        return joinedLotteries;
    }

    public void setJoinedLotteries(Set<Lottery> joinedLotteries) {
        this.joinedLotteries = joinedLotteries;
    }

    public Set<Lottery> getCreatedLotteries() {
        return createdLotteries;
    }

    public void setCreatedLotteries(Set<Lottery> createdLotteries) {
        this.createdLotteries = createdLotteries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", status=" + status +
                ", joinedLotteries=" + joinedLotteries +
                ", createdLotteries=" + createdLotteries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(status, user.status) &&
                Objects.equals(joinedLotteries, user.joinedLotteries) &&
                Objects.equals(createdLotteries, user.createdLotteries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstname, status, joinedLotteries, createdLotteries);
    }
}
