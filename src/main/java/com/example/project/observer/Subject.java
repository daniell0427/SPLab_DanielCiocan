package com.example.project.observer;

import com.example.project.Book;

public interface Subject {
    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers(Book book);
}
