import org.jgrapht.GraphPath;
import org.jgrapht.alg.tour.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.time.Duration;
import java.time.Instant;

public class GraphGenerator {

    private SimpleWeightedGraph<String, DefaultWeightedEdge> citiesGraph;
    private double totalPossiblePaths = 0.0;


    private void addToGraph(String city, String anotherCity, double distance) {
        if (citiesGraph == null) {
            citiesGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        }
        try {
            citiesGraph.addVertex(city);
            citiesGraph.addVertex(anotherCity);
            DefaultWeightedEdge edge = citiesGraph.addEdge(city, anotherCity);
            citiesGraph.setEdgeWeight(edge, distance);
        } catch (Exception ignored) {
        }
    }

    //"

    public void generateCitiesGraph() {
        String[] citiesList = {"Santa Maria", "Julio de Castilhos", "Santa Cruz do Sul", "Santiago", "São Gabriel", "São Vicente do Sul", "Cachoeira do Sul", "Rosário do Sul", "Cacequi", "Caçapava do Sul", "Três de Maio", "Alegrete", "Santana do Livramento", "Quaraí", "Dom Pedrito", "Uruguaiana", "São Francisco de Assis", "Barra do Quaraí", "Itaqui", "Porto Alegre", "Rio Grande", "Pelotas", "Gramado", "Canela"};
        int min = 60;
        int max = 250;
        for (int i = 0; i < citiesList.length; i++) {
            for (int j = 0; j < citiesList.length; j++) {
                double distance = Math.floor(Math.random() * (max - min + 1) + min);
                totalPossiblePaths += distance;
                this.addToGraph(citiesList[i], citiesList[j], distance);
            }
        }
    }

    public void printGraph() {
        System.out.println(citiesGraph.toString());
    }

    /**
     * method: Dynamic Programming
     * complexity: O(2^v x v)
     **/
    public void applyHeldKarp() {
        Instant start = Instant.now();
        HeldKarpTSP<String, DefaultWeightedEdge> heldKarpTSP = new HeldKarpTSP<>();
        printResult(heldKarpTSP.getTour(this.citiesGraph), "heldKarpTSP");
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish)
                .toMillis() + "ms");
    }

    /**
     * method: Minimum Spanning Three (Greedy)
     * complexity: V^2logV
     **/
    public void applyTwoApproxMetric() {
        Instant start = Instant.now();
        TwoApproxMetricTSP<String, DefaultWeightedEdge> twoApproxMetricTSP = new TwoApproxMetricTSP<>();
        printResult(twoApproxMetricTSP.getTour(this.citiesGraph), "twoApproxMetricTSP");
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish)
                .toMillis() + "ms");
    }

    /**
     * method: Greedy Algorithm
     * complexity: V^2logV
     **/
    public void applyGreedyHeuristic() {
        Instant start = Instant.now();
        GreedyHeuristicTSP<String, DefaultWeightedEdge> greedyHeuristicTSP = new GreedyHeuristicTSP<>();
        printResult(greedyHeuristicTSP.getTour(this.citiesGraph), "greedyHeuristicTSP");
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish)
                .toMillis() + "ms");
    }

    /**
     * method: Random
     * complexity: v
     **/
    public void applyRandomTour() {
        Instant start = Instant.now();
        RandomTourTSP<String, DefaultWeightedEdge> randomTourTSP = new RandomTourTSP<>();
        printResult(randomTourTSP.getTour(this.citiesGraph), "randomTourTSP");
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish)
                .toMillis() + "ms");
    }

    /**
     * method: NI
     * complexity: V^2
     **/
    public void applyNearestInsertion() {
        Instant start = Instant.now();
        NearestInsertionHeuristicTSP<String, DefaultWeightedEdge> nearestInsertionHeuristicTSP =
                new NearestInsertionHeuristicTSP<>();
        printResult(nearestInsertionHeuristicTSP.getTour(this.citiesGraph), "nearestInsertionHeuristicTSP");
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish)
                .toMillis() + "ms");
    }

    /**
     * method: NN
     * complexity: V^2
     **/
    public void applyNearestNeighbour() {
            Instant start = Instant.now();
            NearestNeighborHeuristicTSP<String, DefaultWeightedEdge> nearestNeighborHeuristicTSP =
                    new NearestNeighborHeuristicTSP<>();
            printResult(nearestNeighborHeuristicTSP.getTour(this.citiesGraph), "nearestNeighborHeuristicTSP");
            Instant finish = Instant.now();
            System.out.println("Time: " + Duration.between(start, finish)
                    .toMillis() + "ms");
    }

    /**
     * method: Minimum Spanning Three + Eulerian Cycle
     * complexity: V^2
     **/
    public void applyChristofidesThreeHalvesApproxMetricTSP() {
            Instant start = Instant.now();
            ChristofidesThreeHalvesApproxMetricTSP<String, DefaultWeightedEdge>
                    christofidesThreeHalvesApproxMetricTSP =
                    new ChristofidesThreeHalvesApproxMetricTSP<>();
            printResult(christofidesThreeHalvesApproxMetricTSP.getTour(this.citiesGraph), "christofidesThreeHalvesApproxMetricTSP");
            Instant finish = Instant.now();
            System.out.println("Time: " + Duration.between(start, finish)
                    .toMillis() + "ms");
    }

    public void applyTwoOptHeuristicTSP() {
         Instant start = Instant.now();
         TwoOptHeuristicTSP<String, DefaultWeightedEdge>
                 twoOptHeuristicTSP =
                 new TwoOptHeuristicTSP<>(new NearestNeighborHeuristicTSP<>());
         printResult(twoOptHeuristicTSP.getTour(this.citiesGraph), "twoOptHeuristicTSP");
         Instant finish = Instant.now();
         System.out.println("Time: " + Duration.between(start, finish)
                 .toMillis() + "ms");
    }


    private void printResult(GraphPath<String, DefaultWeightedEdge> graphPath, String method) {
        System.out.println("Método: " + method);
        System.out.println("O grafo tem distancia linear total de: " + totalPossiblePaths + "KM");
        System.out.println(
                "O menor percurso é de aproximadamente: " + graphPath.getWeight() + "KM");
        System.out.printf("O custo de gasolina é de: %.2f R$\n",
                (graphPath.getWeight() / 12) * 6.66);
    }
}
