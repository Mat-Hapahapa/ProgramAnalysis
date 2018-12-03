package models;

import java.util.ArrayList;
import java.util.Arrays;

public class VariableList {

    ArrayList<String> varList;
    ArrayList<String> operatorList;

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

        operatorList = new ArrayList<String>();
        operatorList.add("<=");
        operatorList.add(">=");
        operatorList.add("==");
        operatorList.add("!=");
        operatorList.add("+");
        operatorList.add("-");
        operatorList.add("*");
        operatorList.add("/");
        operatorList.add("%");
        operatorList.add("<");
        operatorList.add(">");

    }

    public ArrayList<String> getVariables(String operation) {
        ArrayList<String> variableList = new ArrayList<>();
        for (String str : operation.split("")) {
            if (varList.contains(str)) {
                if (operation.contains("R.fst")) {
                    variableList.add("R.fst");
                } else if (operation.contains("R.snd")) {
                    variableList.add("R.snd");
                } else if (operation.contains("A[")) {
                    variableList.add("A[" + operation.split("")[operation.indexOf("[") + 1] + "]");
                } else if (operation.contains("end")) {
                    // do nothing
                } else
                    variableList.add(str);
            }
        }
        return variableList;
    }

    public String isVariable(String varOrConst) {
        for (String str : varList) {
            if (varList.contains(str)) {
                return str;
            }
        }
        return "";
    }

    public String getOperator(String operation) {
        for (String operator : operatorList) {
            if (operation.contains(operator)) {
                return operator;
            }
        }
        return "";
    }
}
