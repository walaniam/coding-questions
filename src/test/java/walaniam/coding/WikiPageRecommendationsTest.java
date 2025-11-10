package walaniam.coding;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WikiPageRecommendationsTest {
    
    private WikiPageRecommendations newInstance(Map<String, List<String>> pageTree) {
        return new WikiPageRecommendationsBfs(pageTree);
//        return new WikiPageRecommendationsImpl(pageTree);
    }

    @Test
    void recommendSimple() {
        var recommendations = newInstance(
            Map.of(
                "A", List.of("B", "C"),
                "B", List.of("D"),
                "C", List.of(),
                "D", List.of()
            )
        );

        List<String> recommended = recommendations.recommend("A", 2);
        assertThat(recommended).containsExactly("B", "C", "D");
    }

    @Test
    void recommendMoreComplex() {
        var recommendations = newInstance(
            Map.of(
                "A", List.of("B", "C", "D"),
                "B", List.of("D", "A"),
                "C", List.of("B", "F", "D"),
                "D", List.of("E"),
                "E", List.of(),
                "F", List.of("G1"),
                "G1", List.of("G2")
            )
        );

        assertThat(recommendations.recommend("A", 0))
            .isEmpty();

        assertThat(recommendations.recommend("A", 1))
            .containsExactly("B", "C", "D");

        assertThat(recommendations.recommend("A", 2))
            .containsExactly("B", "C", "D", "F", "E");

        assertThat(recommendations.recommend("A", 3))
            .containsExactly("B", "C", "D", "F", "E", "G1");

        assertThat(recommendations.recommend("C", 1))
            .containsExactly("B", "F", "D");

        assertThat(recommendations.recommend("C", 3))
            .containsExactly("B", "F", "D", "A", "G1", "E", "G2");
    }

    @Test
    void cycleGraph() {
        var rec = newInstance(
            Map.of(
                "A", List.of("B"),
                "B", List.of("C"),
                "C", List.of("A")
            )
        );

        // BFS distance 1 from A
        assertThat(rec.recommend("A", 1))
            .containsExactly("B");

        // BFS distance 2 from A
        assertThat(rec.recommend("A", 2))
            .containsExactly("B", "C");
    }

    @Test
    void diamondGraph() {
        var rec = newInstance(
            Map.of(
                "A", List.of("B", "C"),
                "B", List.of("D"),
                "C", List.of("D"),
                "D", List.of()
            )
        );

        assertThat(rec.recommend("A", 2))
            .containsExactly("B", "C", "D"); // BFS
    }

    @Test
    void selfLoop() {
        var rec = newInstance(
            Map.of(
                "A", List.of("A", "B"),
                "B", List.of()
            )
        );

        assertThat(rec.recommend("A", 1))
            .containsExactly("B");
    }

    @Test
    void shorterPathDiscoveredLater() {
        var rec = newInstance(
            Map.of(
                "A", List.of("B", "C"),
                "C", List.of("B"),
                "B", List.of()
            )
        );

        assertThat(rec.recommend("A", 1))
            .containsExactly("B", "C"); // B at distance 1
    }

    @Test
    void disconnectedGraph() {
        var rec = newInstance(
            Map.of(
                "A", List.of("B"),
                "B", List.of(),
                "X", List.of("Y")
            )
        );

        assertThat(rec.recommend("A", 2))
            .containsExactly("B");
    }

    @Test
    void wideGraph() {
        var rec = newInstance(Map.of(
            "A", List.of("B1", "B2", "B3", "B4", "B5"),
            "B1", List.of("C1"),
            "B2", List.of("C2"),
            "B3", List.of("C3"),
            "B4", List.of("C4"),
            "B5", List.of("C5")
        ));

        assertThat(rec.recommend("A", 2))
            .containsExactly("B1", "B2", "B3", "B4", "B5",
                "C1", "C2", "C3", "C4", "C5");
    }


    @Test
    void diamondWithReversedEdgeOrder() {
        var rec = newInstance(Map.of(
            "A", List.of("B", "C"),
            "B", List.of("D"),
            "C", List.of("D") // shorter path discovered later
        ));

        assertThat(rec.recommend("A", 2))
            .containsExactly("B", "C", "D");
    }

    @Test
    void nodeReachedLaterAtShorterDistance() {
        var rec = newInstance(Map.of(
            "A", List.of("B", "C"),
            "B", List.of("D"),
            "C", List.of("E"),
            "E", List.of("D")
        ));

        assertThat(rec.recommend("A", 2))
            .containsExactly("B", "C", "D", "E");
    }

}