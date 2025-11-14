package com.example.project.books.command.executors;

import com.example.project.books.async.AsyncRequest;
import com.example.project.books.async.AsyncStatus;
import com.example.project.books.command.Command;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@Service
public class AsyncCommandExecutor {

    private final BlockingQueue<AsyncRequest<?>> queue = new LinkedBlockingQueue<>();
    private final Map<String, AsyncRequest<?>> store = new ConcurrentHashMap<>();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    /**
     * Called by controllers: queues a command and returns the generated requestId.
     */
    public <R> String submit(Command<R> command) {
        String id = UUID.randomUUID().toString();
        AsyncRequest<R> req = new AsyncRequest<>(id, command);
        store.put(id, req);
        queue.add(req);
        return id;
    }

    /**
     * Background dispatcher: takes from queue and runs in thread pool.
     */
    @PostConstruct
    public void startDispatcher() {
        Thread dispatcher = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    AsyncRequest<?> req = queue.take();
                    req.setStatus(AsyncStatus.RUNNING);

                    threadPool.submit(() -> {
                        try {
                            // unchecked but safe here
                            Object result = req.getCommand().execute();
                            //noinspection unchecked
                            ((AsyncRequest<Object>) req).setResult(result);
                            req.setStatus(AsyncStatus.COMPLETED);
                        } catch (Throwable t) {
                            req.setError(t);
                            req.setStatus(AsyncStatus.FAILED);
                        }
                    });

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "async-command-dispatcher");

        dispatcher.setDaemon(true);
        dispatcher.start();
    }

    public AsyncStatus getStatus(String id) {
        AsyncRequest<?> req = store.get(id);
        return (req == null) ? null : req.getStatus();
    }

    public Object getResult(String id) {
        AsyncRequest<?> req = store.get(id);
        if (req == null) {
            return null;
        }
        if (req.getStatus() != AsyncStatus.COMPLETED) {
            return null;
        }
        return req.getResult();
    }

    public Throwable getError(String id) {
        AsyncRequest<?> req = store.get(id);
        return (req == null) ? null : req.getError();
    }
}
