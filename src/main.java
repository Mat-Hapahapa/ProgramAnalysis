
public class main {

    public static void main(String[] args) {

        System.out.println("Test Program");

        System.out.println("" +
                "x := 0;\n" +
                "A[0] := -1;\n" +
                "A[1] := 0;\n" +
                "A[2] := 1;\n" +
                "R := (0, 0);\n" +
                "R.fst := 1;\n" +
                "R.snd := 2;\n" +
                "b1 = true;\n" +
                "b2 = false;\n" +
                "\n" +
                "if [b1] then \n" +
                "\twhile [b2] do\n" +
                "\t\tx := A[0];\n" +
                "else\n" +
                "\twhile [!b2] do\n" +
                "\t\tx := A[2];\n" +
                "Read(int x)\n" +
                "Write(x)\n"
        );
    }

}
