package advancedjavaconcepts.assignmentthree;

import advancedjavaconcepts.ColorCode;

import java.util.Arrays;
import java.util.Comparator;

public class SortingObject {
    public static void main(String[] args) {
        City[] cities = {
                new City("Tenkasi", 836526308236L, 2.88 * Math.pow(10, 9), 0),
                new City("Chennai", 924563827451L, 4.26 * Math.pow(10, 8), 1),
                new City("Coimbatore", 715263948512L, 4.723 * Math.pow(10, 9), 1),
                new City("Madurai", 652184739521L, 3.71 * Math.pow(10, 9), 1),
                new City("Trichy", 581726349812L, 1.754 * Math.pow(10, 10), 1),
                new City("Salem", 498713652489L, 1.623 * Math.pow(10, 10), 0),
                new City("Tirunelveli", 562938471236L, 1.842 * Math.pow(10, 10), 0)
        };
        System.out.println(ColorCode.boxSingle("   Natural Sorting By Population   "));
        Arrays.sort(cities);
        System.out.println(Arrays.toString(cities));

        System.out.println(ColorCode.boxSingle("   Sorting By Area If two area equals sort by airports   "));
        Arrays.sort(cities, new AreaComparator().thenComparing(new NameComparator()));
        System.out.println(Arrays.toString(cities));

        System.out.println(ColorCode.boxSingle("   Sorting By Airports If two airports equals sort by population   "));
        Arrays.sort(cities, new AirportsComparator().reversed());
        System.out.println(Arrays.toString(cities));

        System.out.println(ColorCode.boxSingle("   Reverse sort by name   "));
        Arrays.sort(cities, new NameComparator().reversed());
        System.out.println(Arrays.toString(cities));
    }
}

class City implements Comparable<City> {
    String name;
    long population;
    double area;
    int numberOfAirports;

    City (String name, long population, double area, int numberOfAirports) {
        this.name = name;
        this.population = population;
        this.area = area;
        this.numberOfAirports = numberOfAirports;
    }

    @Override
    public int compareTo(City c) {
        return Long.compare(this.population, c.population);
    }

    @Override
    public String toString() {
        return String.format("%s (%fsq.m) population: %d Airports: %d %n", name, area, population, numberOfAirports);
    }
}

class AreaComparator implements Comparator<City> {
    @Override
    public int compare(City a, City b) {
        return Double.compare(a.area, b.area);
    }
}

class AirportsComparator implements Comparator<City> {
    @Override
    public int compare(City a, City b) {
        if (Integer.compare(a.numberOfAirports, b.numberOfAirports) == 0) {
            return a.compareTo(b);
        }
        return Integer.compare(a.numberOfAirports, b.numberOfAirports);
    }
}

class NameComparator implements Comparator<City> {
    @Override
    public int compare(City c1, City c2) {
        return c1.name.compareTo(c2.name);
    }
}