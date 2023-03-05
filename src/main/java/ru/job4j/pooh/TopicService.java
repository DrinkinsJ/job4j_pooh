package ru.job4j.pooh;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {

    private final ConcurrentHashMap<String, ConcurrentHashMap<String, ConcurrentLinkedQueue<String>>> topics = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String text = "";
        String status = "404";

        if (POST.equals(req.httpRequestType())) {
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> topic = topics.getOrDefault(req.sourceName(), new ConcurrentHashMap<>());
            if (topic != null) {
                topic.forEach((k, v) ->
                        v.add(req.param())
                );
                status = "201";
            }
        }

        else if (GET.equals(req.httpRequestType())) {
            topics.putIfAbsent(req.sourceName(), new ConcurrentHashMap<>());
            ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> topic = topics.get(req.sourceName());
            topic.putIfAbsent(req.param(), new ConcurrentLinkedQueue<>());
            text = topic.get(req.param()).poll();
            if (text == null) {
                text = "";
                status = "204";
            } else {
                status = "200";
            }
        }
        return new Resp(text, status);
    }
}