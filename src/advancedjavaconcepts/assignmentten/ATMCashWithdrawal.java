package advancedjavaconcepts.assignmentten;

import advancedjavaconcepts.ColorCode;

class BankAccount {
    double initalBalance;

    BankAccount(double initalBalance) {
        this.initalBalance = initalBalance;
    }
}

class Transaction implements Runnable {
    BankAccount account;
    int transactionAmount;

    Transaction(BankAccount account,int transactionAmount) {
        this.account = account;
        this.transactionAmount = transactionAmount;
    }

    @Override
    public void run() {
        synchronized (account) {
            if (transactionAmount > 0 && transactionAmount <= account.initalBalance) {
                Thread thread = new Thread(() -> {
                    for (int i = 0; i < 9; i++) {
                        System.out.print(ColorCode.colored("yellow","\rTransfering" + ".".repeat(i%4)));
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println();
                });
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                account.initalBalance -= transactionAmount;
                System.out.println(ColorCode.right("Payment Successfull!"));
                System.out.println("Current Balance: " + account.initalBalance);
            }
        }
    }
}

public class ATMCashWithdrawal {
    public static void main(String[] args) {
        BankAccount jointAccount = new BankAccount(5000);
        Thread t1 = new Thread(new Transaction(jointAccount,3000));
        t1.setName(""+(char)(Math.random()));
        Thread t2 = new Thread(new Transaction(jointAccount,2000));
        t1.start();
        t2.start();
    }
}
