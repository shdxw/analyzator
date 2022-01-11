package ru.vl.analyze;

import java.io.IOException;
import java.io.InputStreamReader;

public class MainUi {
    public static void main(String[] args) throws IOException {
//        String[] args = {"-u", "99.0", "-t", "45"};
        ArgumentAnalyzer analyzer = new ArgumentAnalyzer(args);
        analyzer.analyze();
        Analyzer logWatcher = new SimpleAnalyzer(analyzer.percent, analyzer.milisec);
        logWatcher.analyze(new InputStreamReader(System.in));
    }
}
