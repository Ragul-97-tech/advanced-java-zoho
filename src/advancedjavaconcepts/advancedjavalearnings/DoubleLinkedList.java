package advancedjavaconcepts.advancedjavalearnings;

class DoublyNode {
    DoublyNode prev;
    int data;
    DoublyNode next;
    DoublyNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data+"";
    }
}

public class DoubleLinkedList {
    static DoublyNode head;
    static DoublyNode tail;
    public static void main(String[] args) {
        DoublyNode newNode = new DoublyNode(10);
        head = newNode;
        DoublyNode temp = head;
        DoublyNode prevNode = temp;
        temp.next = new DoublyNode(20);
        temp = temp.next;
        temp.prev = prevNode;
        temp.next = new DoublyNode(30);
        prevNode = temp;
        temp = temp.next;
        temp.prev = prevNode;
        tail = temp;

        DoubleLinkedList dlList = new DoubleLinkedList();
        System.out.println(dlList.printForward());
        System.out.println(dlList.printBackward());
    }
    String printForward() {
        StringBuilder sb = new StringBuilder();
        DoublyNode current = head;
        while (current != null) {
            sb.append(current).append("\n");
            current = current.next;
        }
        return sb.toString();
    }

    String printBackward() {
        StringBuilder sb = new StringBuilder();
        DoublyNode current = tail;
        while (current != null) {
            sb.append(current).append("\n");
            current = current.prev;
        }
        return sb.toString();
    }
}
