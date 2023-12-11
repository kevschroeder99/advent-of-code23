package Day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day08Part2 {

    private static String file = "src/main/resources/08_input.txt";
    //private static String file = "src/test/resources/08_input_test_part2.txt";

    public static void main(String[] args) throws Exception {
        Day08Part2 starter = new Day08Part2();
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
            long steps = 0;

            List<Node> nodes = setNodes(list);
            List<Node> endNodes = findAllEndNodes(nodes);
            List<String> endNodesNames = getAllEndNodesNames(endNodes);
            List<Node> currentNodes = findAllStartingNodes(nodes);

            while (!isFinished) {
                if (list.get(0).isEmpty()) {
                    list.set(0, copyInstructions);
                }
                String instruction = list.get(0);
                boolean nextStepIsLeft = isNextStepLeft(instruction);

                currentNodes = getCurrentNode(nextStepIsLeft, nodes, currentNodes);
                List<String> currentNodename = getCurrentNodeNames(currentNodes);
                steps++;
                list.set(0, list.get(0).substring(1));
                if (currentNodename.equals(endNodesNames)) {
                    isFinished = Boolean.TRUE;
                }
            }
            System.out.println(steps);
        }
    }

    private List<String> getCurrentNodeNames(List<Node> currentNodes) {
        List<String> currentNodeNames = new ArrayList<>();
        for (Node node : currentNodes) {
            currentNodeNames.add(node.getName());
        }
        return currentNodeNames;
    }

    public List<Node> getCurrentNode(boolean isLeft, List<Node> nodes, List<Node> currentNodes) {
        List<Node> resultNodes = new ArrayList<>();
        for (Node node : currentNodes) {
            if (isLeft) {
                String leftNode = node.getLeft();
                Node nodeLeft = nodes.stream()
                        .filter(obj -> obj.getName()
                                .equals(leftNode))
                        .findFirst()
                        .get();
                resultNodes.add(nodeLeft);
            } else {
                String rightNode = node.getRight();
                Node nodeRight = nodes.stream()
                        .filter(obj -> obj.getName()
                                .equals(rightNode))
                        .findFirst()
                        .get();
                resultNodes.add(nodeRight);
            }
        }
        return resultNodes;
    }

    private List<String> getAllEndNodesNames(List<Node> endNodes) {
        List<String> endNodesNames = new ArrayList<>();
        for (Node node : endNodes) {
            endNodesNames.add(node.getName());
        }
        return endNodesNames;
    }

    public boolean isNextStepLeft(String instruction) {
        if (instruction.startsWith("L")) {
            return true;
        }
        return false;
    }

    private List<Node> findAllEndNodes(List<Node> nodes) {
        List<Node> endNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getName().endsWith("Z")) {
                endNodes.add(node);
            }
        }
        return endNodes;
    }

    private List<Node> findAllStartingNodes(List<Node> nodes) {
        List<Node> startingNodes = new ArrayList<>();
        for (Node node : nodes) {
            if (node.getName().endsWith("A")) {
                startingNodes.add(node);
            }
        }
        return startingNodes;
    }

    public List<Node> setNodes(ArrayList<String> list) {
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

    public void setDirections(Node node, String s) {
        String[] leftAndRight = s.split(" = ");
        String[] leftAndRightSplitted = leftAndRight[1].split(",");
        String left = leftAndRightSplitted[0];
        String right = leftAndRightSplitted[1];
        node.setLeft(left.replaceAll("[^0-9A-Z]", ""));
        node.setRight(right.replaceAll("[^0-9A-Z]", ""));
    }

    private void setNodeName(Node node, String s) {
        String[] splitString = s.split(" = ");
        node.setName(splitString[0]);
    }
}
