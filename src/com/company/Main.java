package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long count = persons.stream()
                .filter(age -> age.getAge() < 18)
                .count();
        System.out.println("Количество совершеннолетних: " + count);

        List<String> conscripts = persons.stream()
                .filter(age -> age.getAge() > 18 && age.getAge() < 28)
                .filter(sex -> sex.getSex().equals(Sex.MAN))
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + conscripts);
        List<Person> workers = persons.stream()
                .filter(age -> age.getAge() > 18)
                .filter(s -> (s.getSex() == Sex.WOMAN && s.getAge() < 61) ||
                        (s.getSex() == Sex.MAN && s.getAge() < 66))
                .filter(education -> education.getEducation().equals(Education.HIGHER))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Cписок потенциально работоспособных людей с высшим образованием: " + workers);
    }
}
