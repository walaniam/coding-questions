package walaniam.coding;

import java.util.*;
import java.util.stream.Collectors;

public class WikiPageRecommendationsImpl implements WikiPageRecommendations {

    private record Holder(List<String> pages, int distance) {
    }

    private final Map<String, List<String>> pageTree;

    public WikiPageRecommendationsImpl(Map<String, List<String>> pageTree) {
        this.pageTree = pageTree;
    }

    @Override
    public List<String> recommend(String startPage, int maxDistance) {
//        System.out.println("Recommend for " + startPage);
        List<Holder> holders = new ArrayList<>();
        traverse(startPage, 0, maxDistance, holders, new HashSet<>());
        var ordered = holders.stream()
            .sorted(Comparator.comparing(Holder::distance))
            .map(Holder::pages)
            .flatMap(Collection::stream)
            .collect(Collectors.toCollection(LinkedHashSet::new));
        return ordered.stream()
            .filter(it -> !startPage.equals(it))
            .toList();
    }

    private void traverse(String startPage, int distance, int maxDistance, Collection<Holder> collector, Set<String> visited) {
        if (distance == maxDistance || visited.contains(startPage)) {
            return;
        }
        visited.add(startPage);
        List<String> children = pageTree.get(startPage);

//        System.out.println(distance + " " + startPage + " -> " + children);

        List<String> cleanedChildren = children.stream()
            .filter(it -> !startPage.equals(it))
            .toList();

        collector.add(new Holder(cleanedChildren, distance));

        for (String sibling : children) {
            traverse(sibling, distance + 1, maxDistance, collector, visited);
        }
    }

}
