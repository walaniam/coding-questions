package walaniam.coding;

import java.util.List;

/**
 * Wiki Page Recommendations (Graph + BFS)
 * <p>
 * Data structures: Graph (adjacency list), BFS
 * <p>
 * Problem
 * You are given a map of wiki pages and links between them:
 * Map<String, List<String>> links,
 * where each key is a page, and the list contains pages it links to.
 * <p>
 * Implement:
 * <p>
 * List<String> recommend(String startPage, int maxDistance)
 * <p>
 * Return all pages reachable from startPage using at most maxDistance clicks (edges).
 * Return them in BFS order (closest first).
 * Do not include the startPage in the results.
 * <p>
 * Example
 * <code>
 * A -> [B, C]
 * B -> [D]
 * C -> []
 * D -> []
 * </code>
 * <p>
 * recommend("A", 2) -> [B, C, D]
 */
public interface WikiPageRecommendations {

    List<String> recommend(String startPage, int maxDistance);
}
