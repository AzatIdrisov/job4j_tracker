package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;

public class MockitoTest {

    @Test
    public void replaceTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        EditAction rep = new EditAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);

        rep.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Successfully edit" + ln));
        assertThat(tracker.findAll().get(0).getName(), is(replacedName));

    }

    @Test
    public void deleteTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Deleted item"));
        DeleteAction delete = new DeleteAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        delete.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Successfully deleted" + ln));
    }

    @Test
    public void findByIdTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("New item"));
        FindByIdAction find = new FindByIdAction(out);

        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Id: 1 Name: New item" + ln));
    }

    @Test
    public void findByNameTest() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("New item"));
        FindByNameAction find = new FindByNameAction(out);

        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("New item");

        find.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Id: 1 Name: New item" + ln));
    }
}
