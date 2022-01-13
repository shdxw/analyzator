package ru.vl.analyze;

import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

//реализация анализатора (в целом можно добавить логгер)
public class SimpleAnalyzer implements Analyzer {
    private final float percent;
    private final int milisec;
    private final int LINES = 60;


    public SimpleAnalyzer(float percent, int milisec) {
        this.percent = percent;
        this.milisec = milisec;
    }

    @Override
    public void analyze(InputStreamReader input) {
        try (Scanner reader = new Scanner(input)) {
            long count = 0;
            long good = 0;
            float rsl;
            Date oldTime = null;

            while (reader.hasNextLine()) {
                Request currentRequest = Request.parseRequest(reader.nextLine());
                if (count == 0) {
                    oldTime = currentRequest.getDate();
                }

                if (currentRequest.isNotBadRequest(milisec)) {
                    good++;
                }
                count++;
                if (count >= LINES || !reader.hasNext()) {
                    rsl = ((float)good / count ) * 100;
                    good = 0;
                    count = 0;

                    if (rsl < percent) {
                        System.out.printf("%s %s %.1f%n", oldTime.toString().split(" ")[3],
                                currentRequest.getDate().toString().split(" ")[3], rsl);
                    }
                }

            }
        }
    }
}
