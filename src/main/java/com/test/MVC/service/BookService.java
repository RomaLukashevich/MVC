package com.test.MVC.service;

import com.test.MVC.repository.BookRepository;
import com.test.MVC.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Получение всех книг
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    // Получение книги по ID
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.getBookById(id));
    }

    // Создание новой книги
    public Long createBook(Book book) {
        return bookRepository.insertBook(
                book.getTitle(),
                book.getAuthor(),
                book.getPublishYear(),
                book.getContent()
        );
    }

    // Обновление книги
    public void updateBook(Book book) {
        Optional<Book> existingBook = getBookById(book.getId());
        if (existingBook.isPresent()) {
            bookRepository.updateBook(
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getPublishYear(),
                    book.getContent()
            );
        } else {
            throw new IllegalArgumentException("Книга с таким ID не найдена для обновления.");
        }
    }

    // Удаление книги
    public void deleteBook(Long id) {
        Optional<Book> existingBook = getBookById(id);
        if (existingBook.isPresent()) {
            bookRepository.deleteBook(id);
        } else {
            throw new IllegalArgumentException("Книга с таким ID не найдена для удаления.");
        }
    }
}
