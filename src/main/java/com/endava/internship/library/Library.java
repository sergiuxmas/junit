package com.endava.internship.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Library {

    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        Objects.requireNonNull(book, "book cannot be null");
        if (books.stream().anyMatch(b -> b.isbn().equalsIgnoreCase(book.isbn()))) {
            throw new IllegalArgumentException("Duplicate ISBN: " + book.isbn());
        }
        books.add(book);
    }

    public boolean removeBookByIsbn(String isbn) {
        return books.removeIf(b -> b.isbn().equalsIgnoreCase(isbn));
    }

    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(b -> b.author().equalsIgnoreCase(author))
                .toList();
    }

    public List<Book> allBooks() {
        return Collections.unmodifiableList(books);
    }

    public int count() {
        return books.size();
    }

    public Book findLongestTitle() {
        return books.stream()
                .max((b1, b2) -> Integer.compare(b1.title().length(), b2.title().length()))
                .orElse(null);
    }

}

