package com.sisllc.mathformulas.ci.ch08;

public class Display {

    private Book activeBook;
    private Q83User activeUser;
    private int pageNumber = 0;

    public void displayUser(Q83User user) {
        activeUser = user;
        refreshUsername();
    }

    public void displayBook(Book book) {
        pageNumber = 0;
        activeBook = book;

        refreshTitle();
        refreshDetails();
        refreshPage();
    }

    public void refreshUsername() {
        /* updates username display */
    }

    public void refreshTitle() {
        /* updates title display */
    }

    public void refreshDetails() {
        /* updates details display */
    }

    public void refreshPage() {
        /* updated page display */
    }

    public void turnPageForward() {
        pageNumber++;
        refreshPage();
    }

    public void turnPageBackward() {
        pageNumber--;
        refreshPage();
    }
}
