package Day08;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Day08Part2 {

    //private static String file = "src/main/resources/08_input.txt";
    private static String file = "src/test/resources/08_input_test_part2.txt";

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
            int steps = 0;
            Day08 starter = new Day08();

            List<Node> nodes = starter.setNodes(list);
            List<Node> endNodes = findAllEndNodes(nodes);
            List<Node> startingNodes = findAllStartingNodes(nodes);
            while (!isFinished) {
                if (list.get(0).isEmpty()) {
                    list.set(0, copyInstructions);
                }
                String instruction = list.get(0);
                boolean nextStepIsLeft = starter.isNextStepLeft(instruction);
                List<String> currentNodename = getCurrentNode(nextStepIsLeft, nodes, startingNodes);


            }
        }
    }

    public List<String> getCurrentNode(boolean isLeft, List<Node> nodes, List<Node> startingNodes) {
        List<String> currentNodes = new ArrayList<>();
        for (Node node : startingNodes) {
            Optional<Node> currentNode = nodes.stream()
                    .filter(obj -> obj.getName()
                            .equals(node.getName()))
                    .findFirst();

            if (isLeft) {
                currentNodes.add(currentNode.get().getLeft());
            } else {
                currentNodes.add(currentNode.get().getRight());
            }
        }
        return currentNodes;
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
}
