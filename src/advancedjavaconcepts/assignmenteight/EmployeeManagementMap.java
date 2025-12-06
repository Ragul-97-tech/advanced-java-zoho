package advancedjavaconcepts.assignmenteight;

import java.util.*;

class Employee {
    String empName;
    int empId;
    List<Project> projects;
    Employee(String empName, int empId) {
        this.empName = empName;
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "\nEmployee Id: " + empId + "\nEmployee Name: " + empName;
    }
}

class Project {
    String projectName;
    String description;
    Project(String projectName, String description) {
        this.projectName = projectName;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project Name: " + projectName;
    }
}

public class EmployeeManagementMap {
    public static void main(String[] args) {
        List<Project> emp1Projects = new ArrayList<>();
        Project pro1 = new Project("Build Module", "For Nila Scaling");
        Project pro2 = new Project("Build new LLM", "For enhancing the Zia AI");

        List<Project> emp2Projects = new ArrayList<>();
        Project pro3 = new Project("Syntax Changes in Om", "For All in one Programming Language");

        emp1Projects.add(pro1);
        emp2Projects.add(pro2);
        Employee emp1 = new Employee("John", 12853);
        Employee emp2 = new Employee("Leon", 12098);

        HashMap<Employee, List<Project>> hm = new HashMap<>();
        hm.put(emp1, emp1Projects);
        hm.put(emp2, emp2Projects);
        System.out.println(hm);

        emp1Projects.add(pro2);
        hm.put(emp1, emp1Projects);
        System.out.println(hm);

        emp1Projects.remove(1);
        System.out.println(hm);

        Iterator<Map.Entry<Employee, List<Project>>> pairs = hm.entrySet().iterator();
        hm.put(new Employee("Raj",12342), emp2Projects);
//        System.out.println(pairs.next());
//        while (pairs.hasNext()) {
//            Employee e = pairs.next().getKey();
//            List<Project> list = hm.get(e);
//            System.out.println(e);
//            System.out.println(list);
//        }
    }
}
