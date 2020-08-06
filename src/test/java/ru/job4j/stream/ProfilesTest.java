package ru.job4j.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void whenCollectAddresses() {
        Address firstAd = new Address("Moscow", "Lenina", 5, 5);
        Address secondAd = new Address("Yekaterinburg", "Gagarina", 28, 10);
        Address thirdAd = new Address("Moscow", "Lenina", 5, 5);
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(firstAd));
        profilesList.add(new Profile(secondAd));
        profilesList.add(new Profile(thirdAd));
        Profiles profiles = new Profiles();
        List<Address> result = profiles.collect(profilesList);
        List<Address> expected = Arrays.asList(new Address("Moscow", "Lenina", 5, 5),
                new Address("Yekaterinburg", "Gagarina", 28, 10),
                new Address("Moscow", "Lenina", 5, 5));
        assertThat(result, is(expected));
    }

    @Test
    public void whenCollectUniqueAddresses() {
        Address firstAd = new Address("Moscow", "Lenina", 5, 5);
        Address secondAd = new Address("Yekaterinburg", "Gagarina", 28, 10);
        Address thirdAd = new Address("Moscow", "Lenina", 5, 5);
        List<Profile> profilesList = new ArrayList<>();
        profilesList.add(new Profile(firstAd));
        profilesList.add(new Profile(secondAd));
        profilesList.add(new Profile(thirdAd));
        Profiles profiles = new Profiles();
        List<Address> result = profiles.collectUnique(profilesList);
        List<Address> expected = Arrays.asList(new Address("Moscow", "Lenina", 5, 5),
                new Address("Yekaterinburg", "Gagarina", 28, 10));
        assertThat(result, is(expected));
    }
}