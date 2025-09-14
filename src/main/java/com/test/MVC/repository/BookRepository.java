package com.test.MVC.repository;

import com.test.MVC.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // 1. Вызов процедуры получения всех книг
    @Procedure(procedureName = "GetAllBooks")
    List<Book> getAllBooks();

    // 2. Вызов процедуры получения книги по ID
    @Procedure(procedureName = "GetBookById")
    Book getBookById(@Param("id") Long id);

    // 3. Вызов процедуры добавления книги
    @Procedure(procedureName = "InsertBook")
    Long insertBook(@Param("title") String title,
                    @Param("author") String author,
                    @Param("publish_year") Integer publishYear,
                    @Param("content") String content);

    // 4. Вызов процедуры обновления книги
    @Procedure(procedureName = "UpdateBook")
    void updateBook(@Param("id") Long id,
                    @Param("title") String title,
                    @Param("author") String author,
                    @Param("publish_year") Integer publishYear,
                    @Param("content") String content);

    // 5. Вызов процедуры удаления книги
    @Procedure(procedureName = "DeleteBook")
    void deleteBook(@Param("id") Long id);
}
