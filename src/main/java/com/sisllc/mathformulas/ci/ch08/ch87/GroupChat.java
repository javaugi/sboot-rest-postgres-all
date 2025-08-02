package com.sisllc.mathformulas.ci.ch08.ch87;

public class GroupChat extends Conversation {

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public void addParticipant(User user) {
        participants.add(user);
    }
}
