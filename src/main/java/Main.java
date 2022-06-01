public class Main {
    public static void main(String[] args) {
        GraphGenerator graphGenerator = new GraphGenerator();
        graphGenerator.generateCitiesGraph();
        //graphGenerator.printGraph();
        graphGenerator.applyHeldKarp();
        graphGenerator.applyGreedyHeuristic();
        graphGenerator.applyTwoApproxMetric();
        graphGenerator.applyRandomTour();
        graphGenerator.applyChristofidesThreeHalvesApproxMetricTSP();
        graphGenerator.applyNearestInsertion();
        graphGenerator.applyNearestNeighbour();
        graphGenerator.applyTwoOptHeuristicTSP();
    }
}
