package com.sisllc.mathformulas.ci.ch08;

public class Q83User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long iD) {
        ID = iD;
    }
    private long ID;

    public Q83User(String name, long iD) {
        this.name = name;
        ID = iD;
    }

    public Q83User getUser() {
        return this;
    }

    public static Q83User addUser(String name, long iD) {
        return new Q83User(name, iD);
    }
}
