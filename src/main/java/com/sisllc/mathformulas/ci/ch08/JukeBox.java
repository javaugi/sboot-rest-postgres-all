package com.sisllc.mathformulas.ci.ch08;

import java.util.Set;

public class JukeBox {

    private CDPlayer cdPlayer;
    private Q83User user;
    private Set<CD> cdCollection;
    private SongSelector ts;

    public JukeBox(CDPlayer cdPlayer, Q83User user, Set<CD> cdCollection,
            SongSelector ts) {
        super();
        this.cdPlayer = cdPlayer;
        this.user = user;
        this.cdCollection = cdCollection;
        this.ts = ts;
    }

    public Song getCurrentSong() {
        return ts.getCurrentSong();
    }

    public void setUser(Q83User u) {
        this.user = u;
    }
}
