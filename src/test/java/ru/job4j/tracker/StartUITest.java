package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StartUITest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    @Ignore
    public void whenCreateItem() throws SQLException {
        Input in = new StubInput(
                new String[]{"0", "Item name", "1"}
        );
        Output output = new ConsoleOutput();
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new ExitAction());
        tracker.init();
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("Item name"));
    }

    @Test
    @Ignore
    public void whenReplaceItem() throws SQLException {
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        Output output = new ConsoleOutput();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Replaced item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[]{"0", "1", "New item name", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new EditAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(String.valueOf(item.getId())).getName(), is(replacedName));
    }

    @Test
    @Ignore
    public void whenDeleteItem() throws SQLException {
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        Output output = new ConsoleOutput();
        /* Добавим в tracker новую заявку */
        Item item = tracker.add(new Item("Deleted item"));
        /* Входные данные должны содержать ID добавленной заявки item.getId() */
        Input in = new StubInput(
                new String[]{"0", "1", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(String.valueOf(item.getId())), is(nullValue()));
    }

    @Test
    @Ignore
    public void whenExit() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0"}
        );
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. === Exit ====" + System.lineSeparator()
        ));
    }

    @Test
    @Ignore
    public void whenFindById() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1", "1"}
        );
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIdAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. === Find by Id ====" + System.lineSeparator()
                        + "1. === Exit ====" + System.lineSeparator()
                        + "Item not found" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. === Find by Id ====" + System.lineSeparator()
                        + "1. === Exit ====" + System.lineSeparator()
        ));
    }

    @Test
    @Ignore
    public void whenFindByName() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "Fix", "1"}
        );
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. === Find by Name ====" + System.lineSeparator()
                        + "1. === Exit ====" + System.lineSeparator()
                        + "Item with such name not found" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. === Find by Name ====" + System.lineSeparator()
                        + "1. === Exit ====" + System.lineSeparator()
        ));
    }

    @Test
    @Ignore
    public void whenShowAll() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"0", "1"}
        );
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        Item newItem = new Item("Fix");
        tracker.add(newItem);
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAllAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. === Show all items ====" + System.lineSeparator()
                        + "1. === Exit ====" + System.lineSeparator()
                        + "Id: 1 Name: Fix" + System.lineSeparator()
                        + "Menu." + System.lineSeparator()
                        + "0. === Show all items ====" + System.lineSeparator()
                        + "1. === Exit ====" + System.lineSeparator()
        ));
    }

    @Test
    @Ignore
    public void whenInvalidExit() throws SQLException {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[]{"666", "0"}
        );
        Store tracker = new SqlTracker(ConnectionRollback.create(StartUI.init()));
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                String.format(
                        "Menu.%n"
                                + "0. === Exit ====%n"
                                + "Wrong input, you can select: 0 .. 0%n"
                                + "Menu.%n"
                                + "0. === Exit ====%n"
                )
        ));
    }
}