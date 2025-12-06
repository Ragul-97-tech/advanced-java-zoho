package advancedjavaconcepts.assignmentseven;

import java.util.PriorityQueue;

class Query implements Comparable<Query> {
    int ticketId;
    String empName;
    String description;
    Query(int ticketId, String empName, String description) {
        this.ticketId = ticketId;
        this.empName = empName;
        this.description = description;
    }

    @Override
    public int compareTo(Query q1) {
        if (q1.description.length() == this.description.length())
            return Integer.compare(this.ticketId, q1.ticketId);
        return Integer.compare(this.description.length(), q1.description.length());
    }

    @Override
    public String toString() {
        return "\nTicket Id     : " + ticketId + "\nEmployee Name : " + empName + "\nDescription   : " + description;
    }
}

public class SupportTicketProcessing {
    public static void main(String[] args) {
        PriorityQueue<Query> queries = new PriorityQueue<>();
        queries.offer(new Query(1, "John", "Charger Fault"));
        queries.offer(new Query(2, "Leon", "Charger Fault"));
        queries.offer(new Query(3, "Clara", "Mouse pad misfunction"));

        while (!queries.isEmpty())
            System.out.println(queries.poll());
    }
}