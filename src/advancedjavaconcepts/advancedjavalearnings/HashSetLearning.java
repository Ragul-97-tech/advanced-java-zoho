package advancedjavaconcepts.advancedjavalearnings;

import java.util.HashSet;
import java.util.Objects;

public class HashSetLearning {
    public static void main(String[] args) {
        HashSet<Bank> hs = new HashSet<>();
        hs.add(new Bank("ZS", "123ZS"));
        hs.add(new Bank("ZS", "123ZS"));
        System.out.println(hs);

    }
}

class Bank {
    String bankName;
    String IFSCode;
    Bank(String bankName, String IFSCode) {
        this.bankName = bankName;
        this.IFSCode = bankName;
    }
}