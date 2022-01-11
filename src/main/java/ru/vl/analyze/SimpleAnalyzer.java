package ru.vl.analyze;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

public class SimpleAnalyzer implements Analyzer {
    private float percent;
    private int milisec;
    private final int LINES = 10;
    private Request currentRequest;


    public SimpleAnalyzer(float percent, int milisec) {
        this.percent = percent;
        this.milisec = milisec;
    }

    @Override
    public void analyze(InputStreamReader input) {
        try (Scanner reader = new Scanner(input)) {
            long count = 0;
            long bad = 0;
            float rsl;
            Date oldTime = null;

            if (reader.hasNextLine()) {
                currentRequest = Request.parseRequest(reader.nextLine());
                oldTime = currentRequest.date;
                count++;
            }

            while (reader.hasNextLine()) {
                if (currentRequest.isBadRequest(milisec)) {
                    bad++;
                }
                count++;
                if (count >= LINES || !reader.hasNextLine()) {
                    rsl = (float) bad / count;
                    bad = 0;
                    count = 0;

                    if ((100 - percent) < rsl) {
                        System.out.println(oldTime.toString());
                        System.out.println(currentRequest.date.toString());
                        System.out.println((100 - rsl));
                    }
                }

                currentRequest = Request.parseRequest(reader.nextLine());

            }
        }
    }
}
