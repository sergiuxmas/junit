package com.endava.internship.library;

import java.util.Objects;

public final class Book {
    private final String title;
    private final String author;
    private final String isbn;

    public Book(String title, String author, String isbn) {
        this.title = Objects.requireNonNull(title, "title cannot be null");
        this.author = Objects.requireNonNull(author, "author cannot be null");
        this.isbn = Objects.requireNonNull(isbn, "isbn cannot be null");
    }

    public String title() {
        return title;
    }

    public String author() {
        return author;
    }

    public String isbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return isbn.equalsIgnoreCase(book.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN: %s)", title, author, isbn);
    }
}
