import models.Node;
import models.ProgramGraph;
import models.WorklistFIFO;
import models.WorklistLIFO;

import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        System.out.println("Test Program");


        ProgramGraph G = new ProgramGraph(getSimpleProgram());      // Get program
        G.printGraph();                                             // Print program (Xn, Xn, "constrain")
        
        WorklistLIFO wlLIFO = new WorklistLIFO();
        WorklistFIFO wlFIFO = new WorklistFIFO();
        ReachingDefinition rd = new ReachingDefinition();
        Influencer infl = new Influencer();

        for(Node node: G.getNodes()) {
        	if(!node.getOperation().contains("end") || node.getToNode() == -1) {
	        	wlLIFO.insert(node);
	        	wlFIFO.insert(node);
        	}
        	rd.initAnalize(node);
        	infl.initFromNode(node);
        }
        
        // Is adding the variables into the first Node
        rd.addDeclarations();

        System.out.println();
        System.out.print("Variables RD --> ");
        for(String s : rd.leastElement) {
        	System.out.print(s + ' ');
        }
        
        for(Node node: G.getNodes()) {
        	infl.generateList(node);
        }
        
        System.out.println();
        System.out.println("Influencer");
        System.out.println(infl);
        
        // LIFO
//        ArrayList<Node> visited = new ArrayList<Node>();
//        while(!wlLIFO.isEmpty()) {
//        	System.out.println("wlLIFO: " + wlLIFO.toString());
//        	Node n = wlLIFO.extract();
//        	if(rd.eval(n, visited)) {  // new info to evaluate?
//        		visited.add(n);
//        		rd.analize(n);
//        		for(Node constraint: infl.getInflByConstraint(n)) {
//        			wlLIFO.insert(constraint);
//        		}
//        	}
//        }
        
        // FIFO
        ArrayList<Node> visited = new ArrayList<Node>();
        int step = 0;
        while(!wlFIFO.isEmpty()) {
        	System.out.print("step " +step + " --> wlFIFO: ");
        	wlFIFO.pa4funDisplay();
        	System.out.println(wlFIFO.toString());
        	step++;
        	Node n = wlFIFO.extract();
            if(rd.eval(n, visited)) {  // new info to evaluate?
                visited.add(n);
                rd.analize(n);
        		for(Node constraint: infl.getInflByConstraint(n)) {
        			wlFIFO.insert(constraint);
        		}
        	}
        }

        System.out.println();
        System.out.println("Result RD");
        rd.printResult();


       // SignDetectionLIFO();
    }

    public static ArrayList<String> getSimpleProgram() {
        ArrayList<String> program = new ArrayList<String>();
        program.add("0,1,y:=1");
        program.add("1,2,x>0");
        program.add("2,3,y:=x*y");
        program.add("3,1,x:=x-1");
        program.add("1,-1,end x>0");
       // program.add("4,-1,y:=y+1");
        return program;
    }

    public static ArrayList<String> getAdvancedProgram() {

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

    public static void SignDetectionLIFO() {
        System.out.println("Sign Detection");


        ProgramGraph G = new ProgramGraph(getSimpleProgram());      // Get program
        ArrayList<String> initialVariableSigns = new ArrayList<>();
        initialVariableSigns.add("x..>{+}");
        initialVariableSigns.add("y..>{+,0,-}");

        //WorklistLIFO wlLIFO = new WorklistLIFO();
        WorklistFIFO wlLIFO = new WorklistFIFO();
        SignDetection sd = new SignDetection();
        sd.setInitialSign(initialVariableSigns);
        Influencer infl = new Influencer();

        for (Node node : G.getNodes()) {
            wlLIFO.insert(node);
            sd.initAnalize(node);
            infl.initFromNode(node);
        }

        for (String s : sd.leastElement) {
            System.out.println(s);              //Test that we collected all variables
        }

        for (Node node : G.getNodes()) {
            infl.generateList(node);
        }



        while(!wlLIFO.isEmpty()) {
            Node n = wlLIFO.extract();
            sd.analize(n);
            if(sd.eval(n)) {

                for(Node constraint: infl.getInflByConstraint(n)) {
                    wlLIFO.insert(constraint);
                }
            }
        }

        //Result
        for (int i = 0; i < sd.signList.values().size(); i++) {
            for (String str : sd.signList.get(i)) {
                System.out.println("Node " + i + " : " + str);
            }
        }
    }
}
