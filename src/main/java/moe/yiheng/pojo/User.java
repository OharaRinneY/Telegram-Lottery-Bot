package moe.yiheng.pojo;

import java.util.Set;

public class User {
    private Integer id;
    private String username;
    private String firstname;

    private Set<Lottery> lotteries;

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

    public Set<Lottery> getLotteries() {
        return lotteries;
    }

    public void setLotteries(Set<Lottery> lotteries) {
        this.lotteries = lotteries;
    }
}
