package ru.vl.analyze;

import java.util.HashMap;
import java.util.Map;

public class ArgumentAnalyzer {
    private String[] args;
    private Map<String, String> params = new HashMap<>();
    private float percent = 0;
    private int milisec = 0;

    public ArgumentAnalyzer(String[] args) {
        this.args = args;
    }

    public float getPercent() {
        return percent;
    }

    public int getMilisec() {
        return milisec;
    }

    public boolean analyze() {

        if (this.args.length != 4) {
            System.out.println("bad length");
            System.out.println("example (only -u 99.9 -t 45)");
            return false;
        }


        for (int i = 0; i < args.length;) {
            params.put(args[i], args[i + 1]);
            i+=2;
        }

        String t = params.get("-t");
        String u = params.get("-u");

        if (t == null || u == null) {
            System.out.println("wrong parameters (only -t, -u)");
            return false;
        }

        try {
            this.milisec = Integer.parseInt(t);
        } catch (NumberFormatException e) {
            System.out.println("bad time parameter");
            return false;
        }
        if (this.milisec <= 0) {
            System.out.println("bad time parameter (> 0)");
            return false;
        }

        try {
            this.percent = Float.parseFloat(u);
        } catch (NumberFormatException e) {
            System.out.println("bad percent parameter");
            return false;
        }
        if (this.percent <= 0 || this.percent >= 100) {
            System.out.println("bad percent parameter (> 0)");
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArgumentAnalyzer{" +
                "percent=" + percent +
                ", milisec=" + milisec +
                '}';
    }
}


