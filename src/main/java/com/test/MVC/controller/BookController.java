package com.test.MVC.controller;

import com.test.MVC.service.BookService;
import com.test.MVC.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Получить список всех книг
    @GetMapping
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    // Получить информацию о книге по ID
    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        return "books/view";
    }

    // Страница редактирования книги
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable Long id, Model model) {
        Optional<Book> bookOptional = bookService.getBookById(id);
        Book book = bookOptional.orElseThrow(() -> new IllegalArgumentException("Книга с таким ID не найдена"));
        model.addAttribute("book", book);
        return "books/edit-book";
    }

    // Обновление книги
    @PostMapping("/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.updateBook(book);
        return "redirect:/books";
    }

    // Удалить книгу
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    // Страница для создания новой книги
    @GetMapping("/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/create-book";
    }

    // Создание книги (POST)
    @PostMapping
    public String createBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:/books";
    }
}
