package com.sisllc.mathformulas.ci.ch08.ch89;

public class File extends Entry {

    private String content;
    private int size;

    public File(String n, Directory p, int sz) {
        super(n, p);
        size = sz;
    }

    public int size() {
        return size;
    }

    public String getContents() {
        return content;
    }

    public void setContents(String c) {
        content = c;
    }
}
