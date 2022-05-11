package com.es;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Graph {
    public HashMap<String, EdgeNode> edges = new HashMap<>();
    public HashMap<String, Integer> degree = new HashMap<>();
    public Integer numEdges = 0;
    public Integer numVertices;

    public Graph(Integer numVertices, List<String> vertices) {
        initializeGraph(numVertices, vertices);
    }

    public void initializeGraph(Integer numVertices, List<String> vertices) {
        this.numVertices = numVertices;
        for (int i = 0; i < this.numVertices ; i++) {
            edges.put(vertices.get(i), null);
            degree.put(vertices.get(i), 0);
        }
    }


    public void insertEdge(String inicial, String destino, Integer distancia, Boolean isDirected) {
        EdgeNode aux = new EdgeNode();
        aux.distancia = distancia;
        aux.nomeCidade = destino;
        if (edges.get(inicial) == null || aux.distancia <= edges.get(inicial).distancia) {
            aux.proximaCidade = edges.get(inicial);
            this.edges.put(inicial, aux);
        } else {
            EdgeNode ultimaCidade = null;
            EdgeNode cidadeAtual = edges.get(inicial);
            while (cidadeAtual != null && cidadeAtual.distancia < aux.distancia) {
                ultimaCidade = cidadeAtual;
                cidadeAtual = cidadeAtual.proximaCidade;
            }
            Objects.requireNonNull(ultimaCidade).proximaCidade = aux;
            aux.proximaCidade = cidadeAtual;
        }


        this.degree.put(inicial, degree.get(inicial) + 1);

        if (!isDirected)
            insertEdge(destino, inicial, distancia, true);
        else
            numEdges++;
    }

    public void printGraph() {
        EdgeNode aux;
        for (HashMap.Entry<String, EdgeNode> pair : edges.entrySet()) {
            aux = pair.getValue();
            System.out.print(pair.getKey() + ": ");
            while (aux != null) {
                System.out.print(aux.nomeCidade + " (" + aux.distancia + ")");
                aux = aux.proximaCidade;
                if (aux != null)
                    System.out.print(" -> ");
            }
            System.out.println("");
        }
    }
}
