package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class QueueServiceTest {

    @Test
    public void whenPostThenGetQueue() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";


        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );

        System.out.println(result.text());
        System.out.println(result.status());
        assertThat(result.text(), is("temperature=18"));
    }

    @Test
    public void whenPostThenGetQueue1() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";


        Resp result2 = queueService.process(new Req("GET", "queue", "weather", null));


        queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );
        Resp result = queueService.process(
                new Req("GET", "queue", "weather", null)
        );

        System.out.println(result.text());
        System.out.println(result.status());
        assertThat(result.text(), is("temperature=18"));
    }



}