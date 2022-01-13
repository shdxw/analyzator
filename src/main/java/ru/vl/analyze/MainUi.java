package ru.vl.analyze;

import java.io.IOException;
import java.io.InputStreamReader;

//входная точка
public class MainUi {
    public static void main(String[] args) throws IOException {
        //анализ входных аргументов
        ArgumentAnalyzer analyzer = new ArgumentAnalyzer(args);
        boolean rsl = analyzer.analyze();
        if (!rsl) return;
        //обработка потока согласно входным параметрам
        Analyzer logWatcher = new SimpleAnalyzer(analyzer.getPercent(), analyzer.getMilisec());
        logWatcher.analyze(new InputStreamReader(System.in));
    }
}
