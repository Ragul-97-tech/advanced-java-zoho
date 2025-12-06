package advancedjavaconcepts.assignmentfive;

import advancedjavaconcepts.ColorCode;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    private Card currentCard;

    class Card {
        private final String cardNumber;
        private final String holderName;
        private final int pin;
        Card (String cardNumber, String holderName, int pin) {
            this.cardNumber = cardNumber;
            this.holderName = holderName;
            this.pin = pin;
        }

        @Override
        public String toString() {
            return String.format("Card Number: %s%nHolder Name: %s%nPin        : %d", cardNumber, holderName, pin);
        }

        public int getPin() {
            return pin;
        }
    }

    public void insertCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    boolean isValidPin(int enteredPin) {
        class PinValidator {
            boolean check() {
                return enteredPin == currentCard.getPin();
            }
        }
        return new PinValidator().check();
    }

    public static void main(String[] args) {
        Scanner userInputs = new Scanner(System.in);
        System.out.println(ColorCode.colored("green", ColorCode.boxDouble("  Welcome to ATM  ")));
        int i = 1;
        int a = 0;
        while (i < 4) {
            a = (int) ((Math.random() * 9001) + 1000);
            int pinGenerate = Double.hashCode(a);
            System.out.println(a);
            System.out.println("Find the PIN: " + "*".repeat(4-i) + (""+a).substring(4-i));
//            int pinGenerate = Integer.hashCode((int) (Math.random() * 10000));
            ATM atm = new ATM();
            ATM.Card c1 = atm.new Card("SBI00123", "John", pinGenerate);
            atm.insertCard(c1);

            System.out.print("Enter Pin: ");
            int inputPin;
            try {
                inputPin = userInputs.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(ColorCode.error("Invalid Input, PIN could be Number"));
                userInputs.nextLine();
                continue;
            }
            if (inputPin < 999 || inputPin > 9999)
                System.out.println(ColorCode.warning("Maximum 4 characters is allowed!"));
            else if (atm.isValidPin(Double.hashCode(inputPin))) {
                System.out.println(ColorCode.right("Access Granted"));
                System.out.println(c1);
                break;
            }
            else
                System.out.println(ColorCode.error("Invalid Pin. Access Denied"));
            i++;
        }
        System.out.println(ColorCode.right("Correct PIN: " + a));
    }
}