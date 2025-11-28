package com.example.project.books.controller;

import com.example.project.books.async.*;
import com.example.project.books.command.*;
import com.example.project.books.command.executors.AsyncCommandExecutor;
import com.example.project.persistence.CrudRepository;
import com.example.project.Book;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
import com.example.project.observer.AllBooksSubject;
import com.example.project.Book;

public class BooksController {

    private final CrudRepository<Book, Integer> repo;
    private final AsyncCommandExecutor asyncExecutor;
    private final AllBooksSubject allBooksSubject;

    public BooksController(CrudRepository<Book, Integer> repo,
                           AsyncCommandExecutor asyncExecutor,
                           AllBooksSubject allBooksSubject) {
        this.repo = repo;
        this.asyncExecutor = asyncExecutor;
        this.allBooksSubject = allBooksSubject;
    }

    // ---------- Synchronous endpoints ----------

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                new GetAllBooksCommand(repo).execute()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(
                new GetBookCommand(repo, id).execute()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody String title) {
        return ResponseEntity.ok(
                new UpdateBookCommand(repo, id, title).execute()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(
                new DeleteBookCommand(repo, id).execute()
        );
    }

    // ---------- Asynchronous processing example ----------

    @PostMapping("/async")
    public ResponseEntity<String> addAsync(@RequestBody String title) {
        AddBookCommand cmd = new AddBookCommand(repo, title);
        String requestId = asyncExecutor.submit(cmd);
        return ResponseEntity.accepted().body(requestId);
    }

    @PostMapping
    public ResponseEntity<String> newBook(@RequestBody String title) {
        Book book = new Book(title);
        book = repo.save(book);
        allBooksSubject.add(book);
        return ResponseEntity.ok("Book saved [" + book.getId() + "] " + book.getTitle());
    }

    @GetMapping("/async/{requestId}")
    public ResponseEntity<AsyncResponse> getAsyncStatus(@PathVariable String requestId) {

        AsyncStatus status = asyncExecutor.getStatus(requestId);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }

        Object result = asyncExecutor.getResult(requestId);
        Throwable error = asyncExecutor.getError(requestId);

        AsyncResponse dto = new AsyncResponse(
                requestId,
                status,
                result,
                error != null ? error.getMessage() : null
        );

        return ResponseEntity.ok(dto);
    }
}
