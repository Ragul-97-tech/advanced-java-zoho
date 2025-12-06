package advancedjavaconcepts.advancedjavalearnings;

import org.json.simple.JSONObject;

public class LearnJSON {
    public static void main(String[] args) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("Student", "name");
        System.out.println(jsonObj);
    }
}
