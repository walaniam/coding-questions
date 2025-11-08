package walaniam.coding;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class WikiPageRecommendationsTest {

    @Test
    void recommendSimple() {
        var recommendations = new WikiPageRecommendationsImpl(
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
        var recommendations = new WikiPageRecommendationsImpl(
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
}