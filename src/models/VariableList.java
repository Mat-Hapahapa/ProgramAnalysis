package models;

import java.util.ArrayList;
import java.util.Arrays;

public class VariableList {

    ArrayList<String> varList;

    public VariableList() {
        String alphabet = "qwertyuiopasdfghjklzxcvbnmA[]";
        String[] strArray = alphabet.split("");
        varList = new ArrayList<>(Arrays.asList(strArray));
        varList.add("R.fst");
        varList.add("R.snd");
        varList.add("A[0]");
        varList.add("A[1]");
        varList.add("A[2]");
        varList.add("A[3]");
        varList.add("A[4]");
        varList.add("A[5]");
        varList.add("A[6]");
        varList.add("A[7]");
    }

    public ArrayList<String> getVariables(String operation) {
        ArrayList<String> stringList = new ArrayList<>();
        for (String str : operation.split("")) {
            if (varList.contains(str)) {
                if (operation.contains("R.fst")) {
                    stringList.add("R.fst");
                } else if (operation.contains("R.snd")) {
                    stringList.add("R.snd");
                } else if (operation.contains("A[")) {
                    stringList.add("A["+ operation.split("")[operation.indexOf("[") + 1] + "]");
                } else {
                    stringList.add(str);
                }
            }
        }
        return stringList;
    }
}
