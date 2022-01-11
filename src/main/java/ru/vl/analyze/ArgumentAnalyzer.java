package ru.vl.analyze;

import java.util.HashMap;
import java.util.Map;

public class ArgumentAnalyzer {
    String[] args;
    Map<String, String> params = new HashMap<>();
    float percent = 0;
    int milisec = 0;

    public ArgumentAnalyzer(String[] args) {
        this.args = args;
    }

    public float getPercent() {
        return percent;
    }

    public int getMilisec() {
        return milisec;
    }

    public void analyze() {
        if (this.args.length != 4) {
            System.out.println("bad lenght");
        }

        for (int i = 0; i < args.length;) {
            params.put(args[i], args[i + 1]);
            i+=2;
        }
        this.milisec = Integer.parseInt(params.get("-t"));
        this.percent = Float.parseFloat(params.get("-u"));
    }

    @Override
    public String toString() {
        return "ArgumentAnalyzer{" +
                "percent=" + percent +
                ", milisec=" + milisec +
                '}';
    }
}


