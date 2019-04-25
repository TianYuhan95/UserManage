package com.tian.boot.entity;

import java.util.List;

public class FirstMenu {
    private String authorityId;
    private String firstName;
    private List<SecondMenu> secondMenuList;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<SecondMenu> getSecondMenuList() {
        return secondMenuList;
    }

    public void setSecondMenuList(List<SecondMenu> secondMenuList) {
        this.secondMenuList = secondMenuList;
    }
}
