package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.pooh.Service.*;

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
        assertThat(result.text(), is("temperature=18"));
        assertThat(result.status(), is(STATUS_OK));
    }

    @Test
    public void whenPostCreated201() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";

        Resp result = queueService.process(
                new Req("POST", "queue", "weather", paramForPostMethod)
        );

        assertThat(result.text(), is(""));
        assertThat(result.status(), is(STATUS_CREATED));
    }

    @Test
    public void whenGetQueueNoContent() {
        QueueService queueService = new QueueService();
        String paramForPostMethod = "temperature=18";

        Resp result = queueService.process(
                new Req("GET", "queue", "weather", paramForPostMethod)
        );

        assertThat(result.text(), is(""));
        assertThat(result.status(), is(STATUS_NO_CONTENT));
    }
}