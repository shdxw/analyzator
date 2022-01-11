package ru.vl.analyze;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.FileReader;

public class SimpleAnalyzerTest extends TestCase {
    @Test
    public void simpleSmokeTest() {
        Analyzer analyzer = new SimpleAnalyzer(new FileReader());

    }

}