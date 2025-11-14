package com.example.books.web;

import com.example.project.books.async.*;
import com.example.project.books.command.*;
import com.example.project.books.command.executors.AsyncCommandExecutor;
import com.example.project.books.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;
    private final AsyncCommandExecutor asyncExecutor;

    public BooksController(BooksService booksService,
                           AsyncCommandExecutor asyncExecutor) {
        this.booksService = booksService;
        this.asyncExecutor = asyncExecutor;
    }

    // ---------- Synchronous endpoints ----------

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                new GetAllBooksCommand(booksService).execute()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(
                new GetBookCommand(booksService, id).execute()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id,
                                    @RequestBody String title) {
        return ResponseEntity.ok(
                new UpdateBookCommand(booksService, id, title).execute()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return ResponseEntity.ok(
                new DeleteBookCommand(booksService, id).execute()
        );
    }

    // ---------- Asynchronous processing example ----------

    @PostMapping
    public ResponseEntity<String> addAsync(@RequestBody String title) {
        AddBookCommand cmd = new AddBookCommand(booksService, title);
        String requestId = asyncExecutor.submit(cmd);
        return ResponseEntity.accepted().body(requestId);
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
                (error != null) ? error.getMessage() : null
        );

        return ResponseEntity.ok(dto);
    }
}
