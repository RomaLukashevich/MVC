package com.test.MVC.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Books")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "Book.getAllBooks",
                procedureName = "GetAllBooks",
                resultClasses = Book.class
        ),
        @NamedStoredProcedureQuery(
                name = "Book.getBookById",
                procedureName = "GetBookById",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name = "id")
                },
                resultClasses = Book.class
        )
})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publish_year")
    private Integer publishYear;

    @Column(columnDefinition = "XML")
    private String content;

    public Book() {
    }

    public Book(String title, String author, Integer publishYear, String content) {
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.content = content;
    }
}