package ru.job4j.pooh;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static ru.job4j.pooh.Service.*;

public class TopicServiceTest {

    @Test
    public void whenTopic() {
        TopicService topicService = new TopicService();
        String paramForPublisher = "temperature=18";
        String paramForSubscriber1 = "client407";
        String paramForSubscriber2 = "client6565";
        topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result3 = topicService.process(
                new Req("POST", "topic", "weather", paramForPublisher)
        );
        Resp result1 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber1)
        );
        Resp result2 = topicService.process(
                new Req("GET", "topic", "weather", paramForSubscriber2)
        );

        assertThat(result1.text(), is("temperature=18"));
        assertThat(result1.status(), is(STATUS_OK));
        assertThat(result2.text(), is(""));
        assertThat(result2.status(), is(STATUS_NO_CONTENT));
        assertThat(result3.text(), is(""));
        assertThat(result3.status(), is(STATUS_CREATED));
    }
}