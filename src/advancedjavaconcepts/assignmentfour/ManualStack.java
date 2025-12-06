package advancedjavaconcepts.assignmentfour;

import advancedjavaconcepts.ColorCode;

import java.util.ArrayList;
import java.util.Scanner;

public class ManualStack {
    private final ArrayList<ZohoWriter> stackWords = new ArrayList<>();
    private final ArrayList<ZohoWriter> redoStackWords = new ArrayList<>();

    public void push(ZohoWriter writer) {
        stackWords.add(new ZohoWriter(writer.getWord()));
    }

    public ZohoWriter pop() {
        if (!stackWords.isEmpty()) {
            return stackWords.remove(stackWords.size() - 1);
        }
        return null;
    }

    public ZohoWriter redoPeek(int idx) {
        if (idx >= 0 && idx < redoStackWords.size()) {
            return redoStackWords.get(idx);
        }
        return null;
    }

    public ZohoWriter undoPeek(int idx) {
        if (idx >= 0 && idx < stackWords.size()) {
            return stackWords.get(idx);
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        ManualStack manualStack = new ManualStack();

        System.out.println("Write what you want: ");
        String givenTxt = inputs.nextLine();

        ZohoWriter writer = new ZohoWriter(givenTxt);
        manualStack.push(writer);

        int choice;
        outerLoop:
        while (true) {
            System.out.println("\n1. Bold\n2. Italic\n3. Underline\n4. Undo\n5. Redo\n6. Exit");
            System.out.print("Enter your choice: ");
            choice = inputs.nextInt();
            inputs.nextLine();

            switch (choice) {
                case 1:
                    if (writer.getWord() == null) {
                        System.out.println("Write what you want: ");
                        givenTxt = inputs.nextLine();
                        writer.setWord(givenTxt);
                        manualStack.push(writer);
                    }
                    ZohoWriter bold = new ZohoWriter(writer.bold());
                    manualStack.push(bold);
                    writer.setWord(bold.getWord());
                    System.out.println(manualStack.stackWords);
                    break;
                case 2:
                    if (writer.getWord() == null) {
                        System.out.println("Write what you want: ");
                        givenTxt = inputs.nextLine();
                        writer.setWord(givenTxt);
                    }
                    ZohoWriter italic = new ZohoWriter(writer.italic());
                    manualStack.push(italic);
                    writer.setWord(italic.getWord());
                    System.out.println(manualStack.stackWords);
                    break;
                case 3:
                    if (writer.getWord() == null) {
                        System.out.println("Write what you want: ");
                        givenTxt = inputs.nextLine();
                        writer.setWord(givenTxt);
                    }
                    ZohoWriter underline = new ZohoWriter(writer.underline());
                    manualStack.push(underline);
                    writer.setWord(underline.getWord());
                    System.out.println(manualStack.stackWords);
                    break;
                case 4:
                    ZohoWriter tempWriter = manualStack.pop();
                    if (tempWriter != null) {
                        try {
                            writer.setWord(manualStack.undoPeek(manualStack.stackWords.size()-1).getWord());
                            manualStack.redoStackWords.add(new ZohoWriter(tempWriter.getWord()));
                        } catch (NullPointerException e) {
                            manualStack.redoStackWords.add(new ZohoWriter(tempWriter.getWord()));
                            writer.setWord(null);
                        }
                    }
                    System.out.println(manualStack.stackWords);
                    System.out.println(manualStack.redoStackWords);
                    break;
                case 5:
                    ArrayList<ZohoWriter> temp = manualStack.redoStackWords;
                    if (!temp.isEmpty()) {
                        writer.setWord(manualStack.redoPeek(temp.size()-1).getWord());
                        manualStack.stackWords.add(new ZohoWriter(temp.remove(temp.size()-1).getWord()));
                    }
                    System.out.println(manualStack.stackWords);
                    System.out.println(manualStack.redoStackWords);
                    break;
                case 6:
                    System.out.println("Bye Bye...Have a nice day!");
                    break outerLoop;
                default:
                    System.out.println(ColorCode.warning("Invalid Inputs, Try Again!"));
            }
        }
    }
}


