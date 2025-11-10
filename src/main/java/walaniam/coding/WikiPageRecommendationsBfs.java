package walaniam.coding;

import java.util.*;

public class WikiPageRecommendationsBfs implements WikiPageRecommendations {

    private final Map<String, List<String>> pageTree;

    public WikiPageRecommendationsBfs(Map<String, List<String>> pageTree) {
        this.pageTree = pageTree;
    }

    @Override
    public List<String> recommend(String startPage, int maxDistance) {

        Set<String> result = new LinkedHashSet<>();
        Set<String> visited = new HashSet<>();

        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(new Node(startPage, 0));

        Node current;
        while ((current = nodeQueue.poll()) != null) {
            if (visited.contains(current.value) || current.distance == maxDistance) {
                continue;
            }
            final int parentDistance = current.distance;
            List<String> childPages = pageTree.get(current.value);
            if (childPages != null) {
                childPages.stream()
                    .map(it -> new Node(it, parentDistance + 1))
                    .forEach(nodeQueue::add);
                result.addAll(childPages);
//                System.out.println(current + " -> " + childPages);
//                System.out.println(result);
            }

            visited.add(current.value);
        }

        return result.stream()
            .filter(it -> !startPage.equals(it))
            .toList();
    }

    private record Node(String value, int distance) {}

}
