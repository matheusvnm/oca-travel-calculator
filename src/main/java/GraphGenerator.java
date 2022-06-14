import org.jgrapht.GraphPath;
import org.jgrapht.alg.tour.*;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.time.Duration;
import java.time.Instant;

public class GraphGenerator {

    private SimpleWeightedGraph<String, DefaultWeightedEdge> citiesGraph;
    private final int N = 1000;
    private final String[] cities = new String[N];
    //private double totalPossiblePaths = 0.0;


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
        for(int i = 0; i < N; i++)
        {
            cities[i] = Integer.toString(i);
        }
        generate(cities);
    }


    private void generate(String[] cities) {
        for (String cityOne : cities) {
            for (String cityTwo : cities) {
                double distance = Math.floor(Math.random() * (250 - 60 + 1) + 60);
                //totalPossiblePaths += distance;
                this.addToGraph(cityOne, cityTwo, distance);
            }
        }
    }


    public void printGraph() {
        System.out.println(citiesGraph.toString());
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
     * method: NN
     * complexity: V^2
     **/
    public void applyNearestNeighbour() {
        Instant start = Instant.now();
        NearestNeighborHeuristicTSP<String, DefaultWeightedEdge> nearestNeighborHeuristicTSP =
                new NearestNeighborHeuristicTSP<>();
        printResult(nearestNeighborHeuristicTSP.getTour(this.citiesGraph),
                "nearestNeighborHeuristicTSP");
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
        printResult(christofidesThreeHalvesApproxMetricTSP.getTour(this.citiesGraph),
                "christofidesThreeHalvesApproxMetricTSP");
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish)
                .toMillis() + "ms");
    }


    public void run() {
        this.generateCitiesGraph();
        System.out.println("INPUT SIZE: " + N);
        this.applyRandomTour();
        this.applyChristofidesThreeHalvesApproxMetricTSP();
        this.applyGreedyHeuristic();
        this.applyNearestNeighbour();

        for (int i = 0; i < N-(N/10); i++) {
            this.citiesGraph.removeVertex(cities[i]);
        }

        System.out.println("\nINPUT SIZE: " + (N/10));
        this.applyRandomTour();
        this.applyChristofidesThreeHalvesApproxMetricTSP();
        this.applyGreedyHeuristic();
        this.applyNearestNeighbour();
    }

    private void printResult(GraphPath<String, DefaultWeightedEdge> graphPath, String method) {
        System.out.println("Método: " + method);
        System.out.println(
                "O menor percurso é de aproximadamente: " + graphPath.getWeight() + "KM");
    }
}
