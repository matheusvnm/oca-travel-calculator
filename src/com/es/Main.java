package com.es;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> cidades = new ArrayList<>();
        cidades.add("Alegrete");
        cidades.add("Rosario");
        cidades.add("Manoel Viana");
        cidades.add("Uruguaiana");
        cidades.add("São Gabriel");

        Graph graph = new Graph(cidades.size(), cidades);
        graph.insertEdge(cidades.get(0), cidades.get(1), 50, false);
        graph.insertEdge(cidades.get(0), cidades.get(2), 30, false);
        graph.insertEdge(cidades.get(0), cidades.get(3), 150, false);
        graph.insertEdge(cidades.get(0), cidades.get(4), 100, false);
        graph.insertEdge(cidades.get(1), cidades.get(4), 200, false);
        graph.printGraph();
    }
}
