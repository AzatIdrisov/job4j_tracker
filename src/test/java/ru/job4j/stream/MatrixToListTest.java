package ru.job4j.stream;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MatrixToListTest {

    @Test
    public void whenConvertMatrixToList() {
        Integer[][] matrix = {{1, 2}, {3, 4}};
        List<Integer> result = MatrixToList.convert(matrix);
        List<Integer> expected = List.of(1, 2, 3, 4);
        assertThat(result, is(expected));
    }

}