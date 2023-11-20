package cities;

import graphComponents.Graph;
import graphComponents.Node;
import cities.*;
import algorithm.*;
import meshcomponents.*;
import colors.IslandColors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class RoadNetworkGenerator {
    private Graph graph;
    private Node capital;
    private List<Node> cities;
    private List<Node> path = new ArrayList<>();
    private MyMesh mesh;

    public RoadNetworkGenerator(MyMesh mesh, CityGraph graph, Node capital, List<Node> cities) {
        this.graph = graph;
        this.capital = capital;
        this.cities = cities;
        this.mesh = mesh;
    }
    public void findShortestPath(CityGraph graph,Node destination, Map<MyVertex, Node> vertexToNodeMap){
        ShortestPathAlgorithm pathFinder = new ShortestPathAlgorithm(graph);
        for (Node c : cities) {
            path = pathFinder.findPath(c, destination);
            System.out.println("created paths");
            List<MyVertex> verticesInPath = new ArrayList<>();
            for (Node n : path){
                // search for the vertex associated with the given node
                MyVertex vertex = null;

                for (Map.Entry<MyVertex, Node> entry : vertexToNodeMap.entrySet()) {
                    if (entry.getValue().equals(n)) {
                        vertex = entry.getKey();
                        break;
                    }
                }
                verticesInPath.add(vertex);
            }
            System.out.println(verticesInPath.size());
            System.out.println("about to create segments");
            List<MySegment> segmentsInPath = new ArrayList<MySegment>();
            for (int i = 0; i < verticesInPath.size() - 1; i++) {
                MyVertex v1 = verticesInPath.get(i);
                MyVertex v2 = verticesInPath.get(i + 1);
                MySegment segment = new MySegment();
                segment.setColor(IslandColors.CITY);
                segment.setWeight(5);
                segment.setV1(v1);
                segment.setV2(v2);
                mesh.addSegment(segment);
                // set other properties of the segment if necessary
                segmentsInPath.add(segment);
            }
        }
    }
}

