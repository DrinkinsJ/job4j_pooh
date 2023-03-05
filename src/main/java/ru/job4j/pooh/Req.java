package ru.job4j.pooh;

import static ru.job4j.pooh.Service.*;

public record Req(String httpRequestType, String poohMode, String sourceName, String param) {

    public static Req of(String content) {
        String[] lsSplit = content.split(System.lineSeparator());
        String[] postLine = lsSplit[0].split(" ");
        String reqType = postLine[0];
        String[] topicOrQueue = postLine[1].split("/");
        String poohType = topicOrQueue[1];
        String paramName = topicOrQueue[2];
        String text = "";

        if (POST.equals(reqType)) {
            text = lsSplit[lsSplit.length - 1];
        }
        if (GET.equals(reqType) && TOPIC.equals(poohType)) {
            text = topicOrQueue[topicOrQueue.length - 1];
        }

        return new Req(reqType, poohType, paramName, text);
    }
}