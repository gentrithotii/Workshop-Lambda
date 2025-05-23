package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    public static void exercise1(String message) {
        System.out.println(message);

        Predicate<Person> findErik = (p) -> p.getFirstName().equalsIgnoreCase("Erik");

        List<Person> listWithSpecificName = storage.findMany(findErik);

        listWithSpecificName.forEach(System.out::println);

        System.out.println("----------------------");
    }

    public static void exercise2(String message) {
        System.out.println(message);

        Predicate<Person> findAllFemales = f -> f.getGender().equals(Gender.FEMALE);

        List<Person> femaleFilterdList = storage.findMany(findAllFemales);

        femaleFilterdList.forEach(System.out::println);

        System.out.println("----------------------");
    }


    public static void exercise3(String message) {
        System.out.println(message);

        Predicate<Person> findByBirthdate = b -> b.getBirthDate().isAfter(LocalDate.of(2000, 1, 1).minusDays(1));

        List<Person> listByBirthdate = storage.findMany(findByBirthdate);

        listByBirthdate.forEach(System.out::println);

        System.out.println("----------------------");
    }

    public static void exercise4(String message) {
        System.out.println(message);

        Predicate<Person> findById = i -> i.getId() == 123;

        Person personFound = storage.findOne(findById);

        System.out.println(personFound);

        System.out.println("----------------------");
    }

    public static void exercise5(String message) {
        System.out.println(message);

        Predicate<Person> findWithSpecificId = (p) -> p.getId() == 456;
        Function<Person, String> findAndChangeData = (p) -> {
            p.setFirstName("Nisse");
            p.setLastName("Nilsson");
            p.setGender(Gender.MALE);
            p.setBirthDate(LocalDate.of(1999, 9, 9));

            return "Name: " + p.getFirstName() + " " + p.getLastName() + " born " + p.getBirthDate();
        };

        System.out.println(storage.findOneAndMapToString(findWithSpecificId, findAndChangeData));

        System.out.println("----------------------");
    }

    public static void exercise6(String message) {
        System.out.println(message);

        Predicate<Person> findByE = (p) -> p.getFirstName().startsWith("E") && p.getGender().equals(Gender.MALE);
        Function<Person, String> test = (p) -> p.getFirstName();

        System.out.println(storage.findManyAndMapEachToString(findByE, test));

        System.out.println("----------------------");
    }

    public static void exercise7(String message) {
        System.out.println(message);

        Predicate<Person> findByAge9 = (person) -> person.getBirthDate().isAfter(LocalDate.of(2015, 12, 31));
        Function<Person, String> test = (person) -> {
            int years = LocalDate.now().getYear() - person.getBirthDate().getYear();

            return person.getFirstName() + " " + person.getLastName() + " " + years + " years";
        };

        System.out.println(storage.findManyAndMapEachToString(findByAge9, test));

        System.out.println("----------------------");
    }

    public static void exercise8(String message) {
        System.out.println(message);

        Predicate<Person> testByName = (person) -> person.getFirstName().equalsIgnoreCase("Ulf");
        Consumer<Person> print = System.out::println;

        storage.findAndDo(testByName, print);

        System.out.println("----------------------");
    }

    public static void exercise9(String message) {
        System.out.println(message);

        Predicate<Person> testLastNameContainFirstName = (person) -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase());
        Consumer<Person> print = System.out::println;

        storage.findAndDo(testLastNameContainFirstName, print);

        System.out.println("----------------------");
    }

    public static void exercise10(String message) {
        System.out.println(message);

        Predicate<Person> reverseNamePalindrome = (person) -> {
            StringBuilder sb = new StringBuilder(person.getFirstName());
            return person.getFirstName().equalsIgnoreCase(sb.reverse().toString());

        };
        Consumer<Person> print = (person) -> System.out.println(person.getFirstName() + " " + person.getLastName());

        storage.findAndDo(reverseNamePalindrome, print);

        System.out.println("----------------------");
    }

    public static void exercise11(String message) {
        System.out.println(message);

        Predicate<Person> byLetterA = (p) -> p.getFirstName().startsWith("A");
        Comparator<Person> byBirthDateAndLetterA = Comparator.comparing((Person::getBirthDate));

        System.out.println(storage.findAndSort(byLetterA, byBirthDateAndLetterA));

        System.out.println("----------------------");
    }

    public static void exercise12(String message) {
        System.out.println(message);

        Predicate<Person> byYear1950 = (p) -> p.getBirthDate().isBefore(LocalDate.of(1950, 1, 1));
        Comparator<Person> latestToEarliest = Comparator.comparing((Person::getBirthDate)).reversed();

        Comparator<Person> latestToEarliestArrowFunction = Comparator.comparing((Person p) -> p.getBirthDate()).reversed();

        List<Person> reversedList = storage.findAndSort(byYear1950, latestToEarliest);

        reversedList.forEach(System.out::println);

        System.out.println("----------------------");
    }

    public static void exercise13(String message) {
        System.out.println(message);

        Comparator<Person> bySomeStuff = Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate);

        List<Person> sortedByLastFirstBirth = storage.findAndSort(bySomeStuff);

        sortedByLastFirstBirth.forEach(System.out::println);

        System.out.println("----------------------");
    }

}