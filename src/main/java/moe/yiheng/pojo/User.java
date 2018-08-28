package moe.yiheng.pojo;

import java.util.Set;

public class User {
    private Integer id;
    private String username;
    private String firstname;

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


}
