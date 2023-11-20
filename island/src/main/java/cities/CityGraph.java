package cities;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import colors.IslandColors;
import meshcomponents.*;
import graphComponents.*;
import java.util.*;

public class CityGraph extends Graph {
    private List<MyVertex> vertices;
    private List<MyVertex> cities;
    public Map<MyVertex, Node> vertexToNodeMap;
    private MyMesh mesh;

    public CityGraph(MyMesh mesh) {
        super();
        this.mesh = mesh;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        adjacencyList = new HashMap<Node, List<Node>>();
        vertexToNodeMap = new HashMap<>();
    }
    @Override
    public void createNodes() {
        List<MyPolygon> polygons = mesh.getPolygons();
        for (MyPolygon p : polygons){
            if(!p.getColor().equals(IslandColors.OCEAN) && !p.getColor().equals(IslandColors.LAGOON) && !p.getColor().equals(IslandColors.LAKE) && !p.getColor().equals(IslandColors.BIOME_TUNDRA)) {
                int centroidId = p.getCentroidId();
                Node node = new Node(centroidId, p.getElevation());
                addNode(node);
                vertexToNodeMap.put(mesh.getVertexs().get(centroidId), node);
            }
        }
    }
    @Override
    public void createEdges() {
        int idCount = 0;
        List<MyPolygon> polygons = mesh.getPolygons();
        for (MyPolygon p : polygons){
            if(!p.getColor().equals(IslandColors.OCEAN) && !p.getColor().equals(IslandColors.LAGOON) && !p.getColor().equals(IslandColors.LAKE) && !p.getColor().equals(IslandColors.BIOME_TUNDRA)) {
                List<Integer> neighbours = p.getNeighbours();
                for(Integer i : neighbours) {
                    MyPolygon polygon1 = polygons.get(i);
                    if (!polygon1.getColor().equals(IslandColors.OCEAN) && !polygon1.getColor().equals(IslandColors.LAGOON) && !polygon1.getColor().equals(IslandColors.LAKE) && !polygon1.getColor().equals(IslandColors.BIOME_TUNDRA)) {
                        MyVertex centroid1 = mesh.getVertexs().get(p.getCentroidId());
                        Node n1 = vertexToNodeMap.get(centroid1);
                        for (Integer n : neighbours) {
                            MyPolygon polygon2 = polygons.get(n);
                            if (!polygon2.getColor().equals(IslandColors.OCEAN) && !polygon2.getColor().equals(IslandColors.LAGOON) && !polygon2.getColor().equals(IslandColors.LAKE) && !polygon2.getColor().equals(IslandColors.BIOME_TUNDRA)) {
                                MyPolygon neighbourP = mesh.getPolygons().get(n);
                                MyVertex centroid2 = mesh.getVertexs().get(neighbourP.getCentroidId());
                                Node n2 = vertexToNodeMap.get(centroid2);
                                double edgeWeight = findEdgeWeight(centroid1, centroid2);
                                Edge edge = new Edge(idCount, n1, n2, edgeWeight);
                                addEdge(edge);
                                idCount++;
                            }
                        }
                    }
                }
            }
        }
    }
    public Node getCapitalCity(MyVertex capital){
        Node capitalCity = vertexToNodeMap.get(capital);
        return capitalCity;
    }
    public List<Node> getCityNodes(List<MyVertex> cities){
        List<Node> wantedCities = new ArrayList<>();
        for (MyVertex v : cities){
            Node n = vertexToNodeMap.get(v);
            wantedCities.add(n);
        }
        return wantedCities;
    }
    private double findEdgeWeight(MyVertex v1, MyVertex v2) {
        double x1 = v1.getX();
        double y1 = v1.getY();
        double x2 = v2.getX();
        double y2 = v2.getY();

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        return distance;
    }
    public Map<MyVertex, Node> getVertexToNodeAssociation(){
        return vertexToNodeMap;
    }

}