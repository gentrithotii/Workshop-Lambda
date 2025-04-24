package se.lexicon;


import org.w3c.dom.ls.LSOutput;
import se.lexicon.data.DataStorage;
import se.lexicon.data.DataStorageImpl;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	TODO: Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        Predicate<Person> findErik = (p) -> p.getFirstName().equalsIgnoreCase("Erik");

        List<Person> listWithSpecificName = storage.findMany(findErik);

        listWithSpecificName.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        2.	TODO: Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        Predicate<Person> findAllFemales = f -> f.getGender().equals(Gender.FEMALE);
        List<Person> femaleFilterdList = storage.findMany(findAllFemales);
        femaleFilterdList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        3.	TODO: Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        Predicate<Person> findByBirthdate = b -> b.getBirthDate().isAfter(LocalDate.of(2000, 1, 1).minusDays(1));
        List<Person> listByBirthdate = storage.findMany(findByBirthdate);
        listByBirthdate.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        4.	TODO: Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        Predicate<Person> findById = i -> i.getId() == 123;
        Person personFound = storage.findOne(findById);
        System.out.println(personFound);

        //Write your code here

        System.out.println("----------------------");
    }

    /*
        5.	TODO: Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
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

//        Function<Person, String> testStuff = (p) -> p.toString();

        System.out.println(storage.findOneAndMapToString(findWithSpecificId, findAndChangeData));
        System.out.println("----------------------");
    }

    /*
        6.	TODO: Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);

        Predicate<Person> findByE = (p) -> p.getFirstName().startsWith("E") && p.getGender().equals(Gender.MALE);
        Function<Person, String> test = (p) -> p.getFirstName();

        System.out.println(storage.findManyAndMapEachToString(findByE, test));


        System.out.println("----------------------");
    }

    /*
        7.	TODO: Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        //Write your code here
        Predicate<Person> findByAge9 = (person) -> person.getBirthDate().isAfter(LocalDate.of(2015, 12, 31));
        Function<Person, String> test = (person) -> {
            int years = LocalDate.now().getYear() - person.getBirthDate().getYear();

            return person.getFirstName() + " " + person.getLastName() + " " + years + " years";
        };

        System.out.println(storage.findManyAndMapEachToString(findByAge9, test));

        System.out.println("----------------------");
    }

    /*
        8.	TODO: Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        Predicate<Person> withNameUlf = (p) -> p.getFirstName().equals("Ulf");
        Consumer<Person> printWithName = (p) -> System.out.println(p);

        storage.findAndDo(withNameUlf, printWithName);

        System.out.println("----------------------");
    }

    /*
        9.	TODO: Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        10.	TODO: Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        11.	TODO: Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        12.	TODO: Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        13.	TODO: Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

}