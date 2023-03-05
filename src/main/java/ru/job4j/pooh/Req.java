package ru.job4j.pooh;

import java.util.ArrayList;
import java.util.List;

public class Req {

    private final static String POST = "POST";
    private final static String GET = "GET";
    private final static String QUEUE = "queue";
    private final static String TOPIC = "topic";
    private final String httpRequestType;
    private final String poohMode;
    private final String sourceName;
    private final String param;


    public Req(String httpRequestType, String poohMode, String sourceName, String param) {
        this.httpRequestType = httpRequestType;
        this.poohMode = poohMode;
        this.sourceName = sourceName;
        this.param = param;
    }

    public static Req of(String content) {
        List<String> res =  parser(content);
        return new Req(res.get(0), res.get(1), res.get(2), res.get(3));
    }

    public static void main(String[] args) {
        String ls = System.lineSeparator();
        Req req =  Req.of("GET /queue/weather HTTP/1.1" + ls
                + "Host: localhost:9000" + ls
                + "User-Agent: curl/7.72.0" + ls
                + "Accept: */*" + ls + ls + ls);
        System.out.println(req.httpRequestType());
        System.out.println(req.getPoohMode());
        System.out.println(req.getSourceName());
        System.out.println(req.getParam());
    }

    public static List<String> parser(String params) {
        List<String> res = new ArrayList<>();
        String[] whitespaceSplit = params.split(" ", 2);
        String[] slashSplit = whitespaceSplit[1].substring(1).split("/", 4);
        String[] lsSplit = slashSplit[3].split(System.lineSeparator());
        String sourceName = slashSplit[1].substring(0, slashSplit[1].indexOf(" "));

        if (POST.equals(whitespaceSplit[0])) {
            res.add(whitespaceSplit[0]);
        }
        if (GET.equals(whitespaceSplit[0])) {
            res.add(whitespaceSplit[0]);
        }
        if (QUEUE.equals(slashSplit[0])) {
            res.add(slashSplit[0]);
            res.add(sourceName);
        }
        if (TOPIC.equals(slashSplit[0])) {
            res.add(slashSplit[0]);
            res.add(sourceName);
        }
        for (String s :
             lsSplit) {
            System.out.println(s);
            System.out.println("_____---------------");
        }

        res.add(lsSplit[lsSplit.length-1]);
        return res;
    }

    public String httpRequestType() {
        return httpRequestType;
    }

    public String getPoohMode() {
        return poohMode;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getParam() {
        return param;
    }
}