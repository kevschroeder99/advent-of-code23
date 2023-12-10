package Day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day08 {

    private static String file = "src/main/resources/08_input.txt";
    //private static String file = "src/test/resources/08_input_test2.txt";

    public static void main(String[] args) throws Exception {
        Day08 starter = new Day08();
        starter.doPart1(file);
    }

    private void doPart1(String file) throws Exception {
        FileReader fileReader = new FileReader(file);
        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            ArrayList<String> list = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                list.add(line.trim());
            }
            String copyInstructions = list.get(0);
            Boolean isFinished = Boolean.FALSE;
            int steps = 0;
            String currentNodename = "AAA";
            while (!isFinished) {
                if (list.get(0).isEmpty()) {
                    list.set(0, copyInstructions);
                }
                String instruction = list.get(0);
                List<Node> nodes = setNodes(list);
                boolean nextStepIsLeft = isNextStepLeft(instruction);
                currentNodename = getCurrentNode(nextStepIsLeft, nodes, currentNodename);
                steps++;
                list.set(0, list.get(0).substring(1));
                if (currentNodename.equals("ZZZ")) {
                    isFinished = Boolean.TRUE;
                }
            }
            System.out.println(steps);
        }
    }

    private boolean isNextStepLeft(String instruction) {
        if (instruction.startsWith("L")) {
            return true;
        }
        return false;
    }

    private String getCurrentNode(boolean isLeft, List<Node> nodes, String nodeName) {
        Optional<Node> node = nodes.stream()
                .filter(obj -> obj.getName()
                        .equals(nodeName))
                .findFirst();
        if (isLeft) {
            return node.get().getLeft();
        }
        return node.get().getRight();
    }

    private List<Node> setNodes(ArrayList<String> list) {
        List<Node> nodesList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            String s = list.get(i);
            if (s != null && !s.isEmpty()) {
                Node node = new Node();
                //Set Name
                setNodeName(node, s);
                //Set Left & Right
                setDirections(node, s);
                nodesList.add(node);
            }
        }
        return nodesList;
    }

    private void setDirections(Node node, String s) {
        String[] leftAndRight = s.split(" = ");
        String[] leftAndRightSplitted = leftAndRight[1].split(",");
        String left = leftAndRightSplitted[0];
        String right = leftAndRightSplitted[1];
        node.setLeft(left.replaceAll("[^A-Z]", ""));
        node.setRight(right.replaceAll("[^A-Z]", ""));
    }

    private void setNodeName(Node node, String s) {
        String[] splitString = s.split(" = ");
        node.setName(splitString[0]);
    }
}
