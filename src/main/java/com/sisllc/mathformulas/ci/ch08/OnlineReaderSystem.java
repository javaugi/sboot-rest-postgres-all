package com.sisllc.mathformulas.ci.ch08;

public class OnlineReaderSystem {

    private Library library;
    private UserManager userManager;
    private Display display;

    private Book activeBook;
    private Q83User activeUser;

    public OnlineReaderSystem() {
        userManager = new UserManager();
        library = new Library();
        display = new Display();
    }

    public Library getLibrary() {
        return library;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public Display getDisplay() {
        return display;
    }

    public Book getActiveBook() {
        return activeBook;
    }

    public void setActiveBook(Book book) {
        display.displayBook(book);
        activeBook = book;
    }

    public Q83User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(Q83User user) {
        activeUser = user;
        display.displayUser(user);
    }
}
