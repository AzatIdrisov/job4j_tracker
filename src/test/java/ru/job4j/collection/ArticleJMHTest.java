package ru.job4j.collection;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArticleJMHTest {

    @BeforeClass
    public static void init() throws Exception {
        String[] args = new String[0];
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10)
    @Test
    public void jmhTestWhenShortText() {
        assertThat(
                Article.generateBy(
                        "Мама мыла раму и окно",
                        "мыла пол"
                ),
                is(false)
        );
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10)
    @Test
    public void jmhTestWhenLongText() {
        assertThat(
                Article.generateBy(
                        "Мой дядя самых честных правил, "
                                + "Когда не в шутку занемог, "
                                + "Он уважать себя заставил "
                                + "И лучше выдумать не мог. "
                                + "Его пример другим наука; "
                                + "Но, боже мой, какая скука "
                                + "С больным сидеть и день и ночь, "
                                + "Не отходя ни шагу прочь! "
                                + "Какое низкое коварство "
                                + "Полуживого забавлять, "
                                + "Ему подушки поправлять, "
                                + "Печально подносить лекарство, "
                                + "Вздыхать и думать про себя: "
                                + "Когда же черт возьмет тебя!",
                        "Мой дядя мог думать про тебя и день и ночь"
                ),
                is(true)
        );
    }
}
