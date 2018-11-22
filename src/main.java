import models.Node;
import models.ProgramGraph;
import models.WorklistLIFO;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        System.out.println("Test Program");


        ProgramGraph G = new ProgramGraph(getSimpleProgram());
        G.printGraph();
        
        WorklistLIFO wlLIFO = new WorklistLIFO();
        ReachingDefinition rd = new ReachingDefinition();
        Influencer infl = new Influencer();
        
        for(Node node: G.getNodes()) {
        	wlLIFO.insert(node);
        	rd.analize(node);
        	infl.initFromNode(node);
        }
        
        for(Node node: G.getNodes()) {
        	infl.generateList(node);
        }

    }

    public static ArrayList<String> getSimpleProgram(){
        ArrayList<String> program = new ArrayList<String>();
        program.add("0,1,y:=1");
        program.add("1,2,x>0");
        program.add("2,3,y:=x*y");
        program.add("3,1,x:=x-1");
        program.add("1,4,!x>0");
        return program;
    }

    public static ArrayList<String> getAdvancedProgram(){

        System.out.println("" +
                "1,2,x := 0" +
                "2,3,A[0] := -1" +
                "3,4,A[1] := 0" +
                "4,5,A[2] := 1" +
                "5,6,R := (0, 0)" +
                "6,7,R.fst := 1" +
                "7,8,R.snd := 2" +
                "8,9,b1 = true" +
                "9,10,b2 = false" +

                "if [b1] then \n" +
                "\twhile [b2] do\n" +
                "\t\tx := A[0];\n" +
                "else\n" +
                "\twhile [!b2] do\n" +
                "\t\tx := A[2];\n" +
                "Read(int x)\n" +
                "Write(x)\n"
        );

        return new ArrayList<String>();
    }

    public void worklistAlgorithm(ArrayList<Node> G) {
        // Operations on worklist:
        // empty = is the worklist empty
        // Insert = returns a new worklist that is as W except it has a new constraint
        // extract = returns a pair whose first component is a constraint x w t in the worklist and whose second component is the smaller worklist obtained by removing an occurrence of x w t; it is normally used as in


        List<Node> W = new ArrayList<Node>(); // W := empty;
        List<Node> infl = new ArrayList<Node>();

        for(Node node : G) {  // for( x w t in S)
            W = WorklistLIFO.insert(node, W); //  := insert((x w t),W);       all constraints in the worklist
            //Analysis[x] := ⊥;   // the least element of L
            //infl[node.getToNode()] //infl[x] := ∅;
        }

        /*

        for (x w t in S) {
            for (x0 in FV(t)) {
                infl[x0] := infl[x0] ∪ {x w t}; // changes to x0 might influence x via the constraint x w t
            }
        }

        while (!W.empty()) { //while (W != empty)
            ((x w t),W) := extract(W); // consider the next constraint
            new := eval(t,Analysis);

            if (Analysis[x] 6w new) {        // any work to do?
                Analysis[x] := Analysis[x] t new;  // update the analysis info.
            }
        }

        for (x0 w t0 in infl[x]) {
            W := insert((x0 w t0),W);  // update the worklist
        }
    }*/
    }
}
