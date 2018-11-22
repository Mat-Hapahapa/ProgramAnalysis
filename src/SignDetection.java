import MicroC.Array;
import models.Node;
import models.VariableList;

import java.util.*;

public class SignDetection {

    VariableList vl = new VariableList();

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
    }

    public boolean eval(Node node) {
        return false;
    }

    public void analize(Node node) {
        String op = node.getOperation();
        if (op.contains(":=")){
            String changingVar =  op.substring(0, op.indexOf(":"));

            String operation = op.substring(op.indexOf("=") + 1, op.length());

            calculateSign(node.getToNode(), changingVar ,operation);

        }
    }

    private void calculateSign(int nodeNumber, String var, String operation) {
        //var = the value that is about to be assigned
        String operator = vl.getOperator(operation); //operator is the operator (+,-,/,*,%) between the potential variables
        if (operator.equals("")){
            //A single integer is just assigned to the variable
            if (signList.get(nodeNumber) != null) { //check if there is an entry (key) on that space and replace
                for (int i = 0; i < signList.get(nodeNumber).size(); i++) {
                    if (signList.get(nodeNumber).get(i).contains(var)) {
                        signList.get(nodeNumber).remove(i);
                        signList.get(nodeNumber).add(var + "->{+}");
                    }
                }
            } else {        //No entry (key) - create new
                ArrayList<String> newSign =  new ArrayList<String>();
                newSign.add(var + "->{+}");    //Since the integer doesn't have a sign (+,-,*,/) it can only be a single integer making it {+}
                signList.put(nodeNumber, newSign);
            }

            System.out.println("Assignment of single interger: " + var + " := " + operation);
        } else {
            String firstVar = operation.substring(0,operation.indexOf(operator));
            String secondVar = operation.substring(operation.indexOf(operator) + 1, operation.length());

            System.out.println("Assignment where two variables do stuff: " + var + " := " + firstVar + operator + secondVar );
        }


        someFunction(var,operator);
    }

    public void someFunction(String var, String operator){
        switch (operator) {
            case "+":
               // var = 1 + 1;
            case "-":
            case "*":
            case "/":
            case "%":
        }
    }

}
