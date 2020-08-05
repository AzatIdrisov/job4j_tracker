package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profile {
    private Address address;

    public List<Address> collect(List<Profile> profiles) {
        Comparator<Address> city = (first, second) -> first.getCity().compareTo(second.getCity());
        return profiles.stream()
                .map(profile -> profile.address)
                .sorted(city)
                .distinct()
                .collect(Collectors.toList());
    }
}
