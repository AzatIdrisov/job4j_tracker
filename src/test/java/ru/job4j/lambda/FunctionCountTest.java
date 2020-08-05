package ru.job4j.lambda;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FunctionCountTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        List<Double> result = FunctionCount.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenQuadraticResults() {
        List<Double> result = FunctionCount.diapason(2, 5, x -> x * x + 1);
        List<Double> expected = Arrays.asList(5D, 10D, 17D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenexponentialFunctionThenexponentialResults() {
        List<Double> result = FunctionCount.diapason(2, 5, x -> Math.pow(5, x) + 1);
        List<Double> expected = Arrays.asList(26D, 126D, 626D);
        assertThat(result, is(expected));
    }
}