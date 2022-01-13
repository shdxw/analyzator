package ru.vl.analyze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//проверка работы распознавания запроса
class RequestTest {
    @Test
    public void isGoodTest() {
        Request rq = Request.parseRequest("192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 200 2 30 \"-\" \"@list-item-updater\" prio:0");
        assertTrue(rq.isNotBadRequest(40));
    }

    @Test
    public void badTimeTest() {
        Request rq = Request.parseRequest("192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 200 2 30 \"-\" \"@list-item-updater\" prio:0");
        assertFalse(rq.isNotBadRequest(20));
    }

    @Test
    public void badCodeTest() {
        Request rq = Request.parseRequest("192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 500 2 30 \"-\" \"@list-item-updater\" prio:0");
        assertFalse(rq.isNotBadRequest(40));
    }

    @Test
    public void badCodeAndTimeTest() {
        Request rq = Request.parseRequest("192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 500 2 30 \"-\" \"@list-item-updater\" prio:0");
        assertFalse(rq.isNotBadRequest(10));
    }
}