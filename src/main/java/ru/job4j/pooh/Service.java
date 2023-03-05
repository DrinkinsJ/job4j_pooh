package ru.job4j.pooh;

public interface Service {

    String POST = "POST";
    String GET = "GET";
    String TOPIC = "topic";

    Resp process(Req req);
}