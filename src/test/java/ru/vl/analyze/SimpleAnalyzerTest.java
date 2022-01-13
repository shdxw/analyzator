package ru.vl.analyze;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//проверка работы программы (просто вывод)
public class SimpleAnalyzerTest {

    @TempDir
    static Path tempDir;
    static Path tempFile;


    public static void init(String filename) throws IOException {
        tempFile = Files.createFile(tempDir.resolve(filename));
        writeFile(tempFile.toFile());
    }

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
    public void allIsBadTest()  {
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
    public void testSmoke() throws IOException {
        init("test1.txt");
        Analyzer analyzer = new SimpleAnalyzer(99.6f, 45);
        analyzer.analyze(new InputStreamReader(new FileInputStream(tempFile.toFile())));
        tempFile.toFile().delete();
    }

    public static void writeFile(File dir) throws IOException {
        LocalTime time = LocalTime.of(0, 0, 0);
        LocalTime endTime = LocalTime.of(23, 59, 0);
        Writer wr = new FileWriter(tempFile.toFile());
        PrintWriter printWriter = new PrintWriter(wr);
        List<Integer> givenList = Arrays.asList(100, 200, 300, 400, 500);
        Random rand = new Random();
        String hour = null;
        String min = null;
        String sec = null;
        while (!time.equals(endTime)) {
            time = time.plusMinutes(1);
            hour = String.valueOf(time.getHour());
            min = String.valueOf(time.getMinute());
            sec = String.valueOf(time.getSecond());
            String request = String.format("192.168.32.181 - - [14/06/2017:%s:%s:%s +1000] \"PUT " +
                            "/rest/v1.4/documents?zone=default&_rid=6076537c " +
                            "HTTP/1.1\" %s 2 %s \"-\" \"@list-item-updater\" prio:0",
                    (hour.length() == 1) ? "0" + hour : hour,
                    (min.length() == 1) ? "0" + min : min,
                    (sec.length() == 1) ? "0" + sec : sec,
                    givenList.get(rand.nextInt(givenList.size())),
                    rand.nextInt(100)
            );
            printWriter.println(request);
        }
        printWriter.close();
    }
}