package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        String status = STATUS_NO_CONTENT;
        if (POST.equals(req.httpRequestType())) {
            queue.putIfAbsent(req.sourceName(), new ConcurrentLinkedQueue<>());
            queue.get(req.sourceName()).add(req.param());
            status = STATUS_CREATED;
        } else if (GET.equals(req.httpRequestType())) {
            text = queue.getOrDefault(req.sourceName(), new ConcurrentLinkedQueue<>()).poll();
            if (text == null) {
                text = "";
            } else {
                status = STATUS_OK;
            }
        }
        return new Resp(text, status);
    }


}