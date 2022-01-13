package ru.vl.analyze;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentAnalyzerTest {
    @Test
    void hasThreeParamsTest() {
        String[] args = {"1", "2", "3"};
        ArgumentAnalyzer aa = new ArgumentAnalyzer(args);
        assertFalse(aa.analyze());
    }

    @Test
    void notTrgTest() {
        String[] args = {"y", "2", "t", "3"};
        ArgumentAnalyzer aa = new ArgumentAnalyzer(args);
        assertFalse(aa.analyze());
    }

    @Test
    void allGoodTest() {
        String[] args = {"-u", "99.9", "-t", "3"};
        ArgumentAnalyzer aa = new ArgumentAnalyzer(args);
        assertTrue(aa.analyze());
    }

    @Test
    void badTimeTest() {
        String[] args = {"-u", "10", "-t", "-5"};
        ArgumentAnalyzer aa = new ArgumentAnalyzer(args);
        assertFalse(aa.analyze());
    }

    @Test
    void badPercentTest() {
        String[] args = {"-u", "1000", "-t", "5"};
        ArgumentAnalyzer aa = new ArgumentAnalyzer(args);
        assertFalse(aa.analyze());
    }
}