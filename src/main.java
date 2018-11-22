import models.Node;
import models.ProgramGraph;
import models.WorklistLIFO;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        System.out.println("Test Program");


        ProgramGraph G = new ProgramGraph(getSimpleProgram());      // Get program
        G.printGraph();                                             // Print program (Xn, Xn, "constrain")
        
        WorklistLIFO wlLIFO = new WorklistLIFO();                   // W := empty; creates an empty worklist with worklist alogorithm
        ReachingDefinition rd = new ReachingDefinition();           // Choose analysis definition (Initialize RD)
        Influencer infl = new Influencer();                         // Initialize empty influencer
        
        for(Node node: G.getNodes()) {                              // Iterate through set of Nodes
        	wlLIFO.insert(node);                                    // Insert nodes from graph into worklist
        	rd.analize(node);                                       // The least element of Lattice (Find variables from program)
        	infl.initFromNode(node);                                // Add every node to influencer list.
        }
        
        for(Node node: G.getNodes()) {                               // Iterate through set of Nodes
        	infl.generateList(node, (ArrayList<Node>)G.getNodes());
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
}
