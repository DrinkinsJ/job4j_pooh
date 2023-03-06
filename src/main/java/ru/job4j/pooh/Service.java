package ru.job4j.pooh;

public interface Service {

    String POST = "POST";
    String GET = "GET";
    String TOPIC = "topic";
    String QUEUE = "queue";
    String STATUS_OK = "200";
    String STATUS_CREATED = "201";
    String STATUS_NO_CONTENT = "204";

    Resp process(Req req);
}