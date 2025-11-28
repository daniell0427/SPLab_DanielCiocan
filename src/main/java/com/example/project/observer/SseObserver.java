package com.example.project.observer;

import com.example.project.Book;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class SseObserver implements Observer {

    private final SseEmitter emitter;
    private final Subject subject;

    public SseObserver(SseEmitter emitter, Subject subject) {
        this.emitter = emitter;
        this.subject = subject;

        emitter.onCompletion(() -> subject.unregisterObserver(this));
        emitter.onTimeout(() -> subject.unregisterObserver(this));
        emitter.onError((ex) -> subject.unregisterObserver(this));
    }

    @Override
    public void update(Book book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (Exception e) {
            subject.unregisterObserver(this);
        }
    }
}
