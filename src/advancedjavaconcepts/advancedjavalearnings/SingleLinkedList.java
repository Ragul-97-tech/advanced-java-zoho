package advancedjavaconcepts.advancedjavalearnings;

class SinglyNode {
    int data;
    SinglyNode next;
    SinglyNode(int date) {
        this.data = date;
        next = null;
    }
}

public class SingleLinkedList {
    SinglyNode head;

    void insertAtEnd(int data) {
        SinglyNode newNode = new SinglyNode(data);
        if (head == null) {
            head = newNode;
            return;
        }

        SinglyNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    void insertAtBeginning(int data) {
        SinglyNode newNode = new SinglyNode(data);
        newNode.next = head;
        head = newNode;
    }

    void print() {
        SinglyNode temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        list.insertAtEnd(3);
        list.insertAtBeginning(4);
        list.insertAtBeginning(5);
        list.print();
    }
}

