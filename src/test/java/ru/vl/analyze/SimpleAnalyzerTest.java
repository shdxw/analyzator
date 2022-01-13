package ru.vl.analyze;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//проверка работы программы (просто вывод)
public class SimpleAnalyzerTest {

    @Test
    public void allIsGoodTest() {
        String good = "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 200 2 30 \"-\" \"@list-item-updater\" prio:0" +
                System.lineSeparator() +
                "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 200 2 30 \"-\" \"@list-item-updater\" prio:0" +
                System.lineSeparator() +
                "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 200 2 30 \"-\" \"@list-item-updater\" prio:0" +
                System.lineSeparator() +
                "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 200 2 30 \"-\" \"@list-item-updater\" prio:0";
        InputStream targetStream = new ByteArrayInputStream(good.getBytes());
        Analyzer analyzer = new SimpleAnalyzer(99.6f, 45);
        analyzer.analyze(new InputStreamReader(targetStream));
    }

    @Test
    public void allIsBadTest() {
        String good = "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 500 2 30 \"-\" \"@list-item-updater\" prio:0" +
                System.lineSeparator() +
                "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 500 2 30 \"-\" \"@list-item-updater\" prio:0" +
                System.lineSeparator() +
                "192.168.32.181 - - [14/06/2017:00:00:00 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 500 2 30 \"-\" \"@list-item-updater\" prio:0" +
                System.lineSeparator() +
                "192.168.32.181 - - [14/06/2017:00:00:10 +1000] \"PUT " +
                "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                "HTTP/1.1\" 500 2 30 \"-\" \"@list-item-updater\" prio:0";
        InputStream targetStream = new ByteArrayInputStream(good.getBytes());
        Analyzer analyzer = new SimpleAnalyzer(99.6f, 45);
        analyzer.analyze(new InputStreamReader(targetStream));
    }

    @Test
    public void testSmoke() {
        Analyzer analyzer = new SimpleAnalyzer(99.6f, 45);
        analyzer.analyze(new InputStreamReader(writeStream()));
    }

    public InputStream writeStream() {
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalTime endTime = LocalTime.of(23, 59, 0);
        List<Integer> givenList = Arrays.asList(100, 200, 300, 400, 500);
        Random rand = new Random();
        StringBuilder request = new StringBuilder();
        while (!time.equals(endTime)) {
            time = time.plusMinutes(1);
            String hour = String.valueOf(time.getHour());
            String min = String.valueOf(time.getMinute());
            String sec = String.valueOf(time.getSecond());
             request.append(String.format("192.168.32.181 - - [14/06/2017:%s:%s:%s +1000] \"PUT " +
                             "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                             "HTTP/1.1\" %s 2 %s \"-\" \"@list-item-updater\" prio:0" +
                     System.lineSeparator(),
                     (hour.length() == 1) ? "0" + hour : hour,
                     (min.length() == 1) ? "0" + min : min,
                     (sec.length() == 1) ? "0" + sec : sec,
                     givenList.get(rand.nextInt(givenList.size())),
                     rand.nextInt(100)
             ));
        }
        return new ByteArrayInputStream(request.toString().getBytes());
    }
}