import MicroC.Array;
import com.sun.org.apache.bcel.internal.generic.SWITCH;
import models.Node;
import models.VariableList;

import java.util.*;

public class SignDetection {

    VariableList vl = new VariableList();
    int variableCount = 0;

    Set<String> leastElement = new HashSet<>();
    public Map<Integer, ArrayList<String>> signList = new HashMap<>();

    public SignDetection() {
    }

    public void setInitialSign(ArrayList<String> initialSigns) {
        ArrayList<String> signs = new ArrayList<>();
        signs.addAll(initialSigns);
        signList.put(0, signs);
    }

    public void initAnalize(Node node) {
        ArrayList<String> varlist = vl.getVariables(node.getOperation());
        leastElement.addAll(varlist);
        variableCount = leastElement.size();
    }

    public boolean eval(Node node) {
        return false;
    }

    public void analize(Node node) {
        String op = node.getOperation();
        if (op.contains(":=")) {
            String changingVar = op.substring(0, op.indexOf(":"));
            String operation = op.substring(op.indexOf("=") + 1, op.length());
            calculateSign(node, changingVar, operation);


            ArrayList<String> newSign = new ArrayList<String>();
            newSign.addAll(signList.get(node.getToNode()));

            if (newSign.size() < variableCount) {
                for (String str : signList.get(node.getFromNode())) {
                    if (!str.contains(changingVar)) {
                        newSign.add(str);
                    }
                }
            }
            signList.put(node.getToNode(), newSign);

        } else { //Should check on conditions. if "<" or ">" or "!" then it will change the sign

            if (op.contains("!")) {
                ArrayList<String> newSign = new ArrayList<String>();
                String changingVar = op.substring(op.indexOf("!") + 1, op.indexOf("!") + 2);  //Doesn't work on array or record
                for (String str : signList.get(node.getFromNode())) {
                    if (str.contains(changingVar)) {
                        if (op.contains(">")) {
                            if (op.substring(op.length() - 1).contains("0")) {
                                newSign.add(changingVar + "..>{0}");
                            }
                        } else if (op.contains("<")) {
                            if (op.substring(op.length() - 1).contains("0")) {
                                newSign.add(changingVar + "..>{+}");
                            }
                        }
                    } else {
                        newSign.add(str);
                    }
                }
                signList.put(node.getToNode(), newSign);
            } else {
                ArrayList<String> newSign = new ArrayList<String>();
                newSign.addAll(signList.get(node.getFromNode()));
                signList.put(node.getToNode(), newSign);
            }
        }
    }


    private void calculateSign(Node node, String var, String operation) {
        //var = the value that is about to be assigned
        String operator = vl.getOperator(operation); //operator is the operator (+,-,/,*,%) between the potential variables
        if (operator.equals("")) {
            //A single integer is just assigned to the variable
            if (signList.get(node.getToNode()) != null) { //check if there is an entry (key) on that space and replace
                for (int i = 0; i < signList.get(node.getToNode()).size(); i++) {
                    if (signList.get(node.getToNode()).get(i).contains(var)) {
                        signList.get(node.getToNode()).remove(i);
                        signList.get(node.getToNode()).add(var + "..>{+}");
                    }
                }
            } else {        //No entry (key) - create new
                ArrayList<String> newSign = new ArrayList<String>();
                newSign.add(var + "..>{+}");    //Since the integer doesn't have a sign (+,-,*,/) it can only be a single integer making it {+}
                signList.put(node.getToNode(), newSign);
            }

            System.out.println("Assignment of single integer: " + var + " := " + operation);
        } else {
            String firstVar = operation.substring(0, operation.indexOf(operator));
            String secondVar = operation.substring(operation.indexOf(operator) + 1, operation.length());

            System.out.println("Assignment where two variables do stuff: " + var + " := " + firstVar + operator + secondVar);


            if (signList.get(node.getToNode()) == null) {
                // Create new entry
                System.out.println("From: " + node.getFromNode() + " -- To:" + node.getToNode() + " --TEST");

                ArrayList<String> newSign = new ArrayList<>();
                //Should check value of firstVar and SecondVar and compute result acordingly


                newSign.add(var + "..>" + "{+}");
                signList.put(node.getToNode(), newSign);


            } else {
                ArrayList<String> newSign = new ArrayList<>();
                for (String str : signList.get(node.getToNode())) {

                    System.out.println("From: " + node.getFromNode() + " -- To:" + node.getToNode() + " --test2");

                    String newVal = newSignVal(node, var, operator, firstVar, secondVar);


                    if (newVal.contains(str.substring(0, 1))) {
                        newSign.add(newVal);
                    } else {
                        newSign.add(str);
                    }
                }
                signList.put(node.getToNode(), newSign);
            }
        }
    }


    private String newSignVal(Node node, String var, String operator, String first, String second) {
        StringBuilder finalValue = new StringBuilder(var + "..>{");
        String firstVariableOrNumber = vl.isVariable(first);
        String secondVariableOrNumber = vl.isVariable(second);
        String firstOldVal = "";
        String secondOldVal = "";
        switch (operator) {
            case "+":
                // var = 1 + 1;
                System.out.println("");
                if (firstVariableOrNumber.isEmpty()) {
                    firstVariableOrNumber = first;
                }
                if (secondVariableOrNumber.isEmpty()) {
                    secondVariableOrNumber = second;
                }

            case "-":
                for (String str : signList.get(node.getFromNode())) {
                    if (str.contains(first)) {
                        firstOldVal = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
                    } else if (str.contains(second)) {
                        secondOldVal = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
                    }
                }
                if (firstVariableOrNumber.isEmpty()) {
                    firstVariableOrNumber = first;
                }
                if (secondVariableOrNumber.isEmpty()) {
                    secondVariableOrNumber = second;
                }

                if (firstOldVal.contains("+") && secondOldVal.contains("")) {
                    finalValue.append("+,");
                }

                if (firstOldVal.contains("0") && secondOldVal.contains("")) {
                    finalValue.append("0,");
                }

                if (firstOldVal.contains("-") && secondOldVal.contains("")) {
                    finalValue.append("-");
                }

                if (operator.contains("+")) {
                    finalValue.append("+");
                }
                if (!secondVariableOrNumber.isEmpty() && Integer.valueOf(secondVariableOrNumber) == 1) {
                    if (operator.contains("-") && firstOldVal.contains("+")) {
                        finalValue.append("0");
                    }
                } else if (!secondVariableOrNumber.isEmpty() && Integer.valueOf(secondVariableOrNumber) > 1) {
                    if (operator.contains("-") && firstOldVal.contains("+")) {
                        finalValue.append("0,-");
                    }
                }


            case "*":
                for (String str : signList.get(node.getFromNode())) {
                    if (str.contains(first)) {
                        firstOldVal = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
                    } else if (str.contains(second)) {
                        secondOldVal = str.substring(str.indexOf("{") + 1, str.indexOf("}"));
                    }
                }

                if (firstOldVal.contains("+") && secondOldVal.contains("+")) {
                    finalValue.append("+,");
                }

                if (firstOldVal.contains("0") && secondOldVal.contains("0")) {
                    finalValue.append("0,");
                }

                if (firstOldVal.contains("-") && secondOldVal.contains("-")) {
                    finalValue.append("-");
                }


            case "/":
            case "%":
        }

        return finalValue.append("}").toString();
    }

}
