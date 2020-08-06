package ru.job4j.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(profile -> profile.getAddress()).collect(Collectors.toList());
    }

    public List<Address> collectUnique(List<Profile> profiles) {
        Comparator<Address> city = (first, second) -> first.getCity().compareTo(second.getCity());
        return profiles.stream()
                .map(profile -> profile.getAddress())
                .sorted(city)
                .distinct()
                .collect(Collectors.toList());
    }
}
