package ru.vl.analyze;

import java.io.IOException;
import java.io.InputStreamReader;

public interface Analyzer {
    public void analyze(InputStreamReader input) throws IOException;
}
