package advancedjavaconcepts.assignmentten;

class BankAccount {
    double balanceAccount;

    BankAccount(double balanceAccount) {
        this.balanceAccount = balanceAccount;
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
            if (transactionAmount > 0 && transactionAmount <= account.balanceAccount) {
                Thread thread = new Thread(() -> {
                    for (int i = 0; i < 5; i++) {
                        System.out.print("\rTimer: " + i);
                        try {
                            Thread.sleep(1000);
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
                account.balanceAccount -= transactionAmount;
                System.out.println("Current Balance: " + account.balanceAccount);
            }
        }
    }
}

public class ATMCashWithdrawal {
    public static void main(String[] args) {
        BankAccount jointAccount = new BankAccount(5000);
        Thread t1 = new Thread(new Transaction(jointAccount,3000));
        t1.setName(""+(char)(Math.random()));
        Thread t2 = new Thread(new Transaction(jointAccount,4000));
        t1.start();
        t2.start();
    }
}
