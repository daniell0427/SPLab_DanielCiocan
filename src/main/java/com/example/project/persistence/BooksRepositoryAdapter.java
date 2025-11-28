package com.example.project.persistence;

import com.example.project.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksRepositoryAdapter implements CrudRepository<Book, Integer> {

    private final BooksRepository jpaRepo;

    public BooksRepositoryAdapter(BooksRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Book> findAll() {
        return jpaRepo.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return jpaRepo.findById(id).orElse(null);
    }

    @Override
    public Book save(Book entity) {
        return jpaRepo.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public boolean existsById(Integer id) {
        return jpaRepo.existsById(id);
    }
}
