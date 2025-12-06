package advancedjavaconcepts.advancedjavalearnings;

class Car {
    private String brand;
    private String model;
    private int year;

    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String display() {
        return  "Engine Details:\nBrand: " + brand + "\nModel: " + model + "\nYear: " + year;
    }

    class Engine {
        private int horsePower;
        private String type;
        Engine (int horsePower, String type) {
            this.horsePower = horsePower;
            this.type = type;
        }

        public String display() {
            return Car.this.display() + "\nHorse Power: " + horsePower + "\nFuel Type: " + type;
        }
        String showServiceHistory() {
            int lastServiceDate = 2024;
            class Service {
                String printHistory() {
                    return String.format("History of Service:%nLast Services History %d %nCar Name: %s", lastServiceDate, brand);
                }
            }
            return new Service().printHistory();
        }
    }
}

public class CarApplication {
    public static void main(String[] args) {
        Car c1 = new Car("Aadi", "ewr12", 2015);
        Car.Engine e1 = c1.new Engine(473, "Diesel");
        System.out.println(e1.display());
        System.out.println(c1.display());
        System.out.println(e1.showServiceHistory());
    }
}