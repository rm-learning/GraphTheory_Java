import java.util.ArrayList;
import java.util.List;

public class al00_dg_adjacencyList {

    public static void main(String[] args) {

        al00_dg_adjacencyList outerObj = new al00_dg_adjacencyList();
        al00_dg_adjacencyList.DirectedGraph graph = outerObj.new DirectedGraph();

        // Adicionando os vértices a uma rede social hipotética
        graph.addNode("Harry Potter");
        graph.addNode("Hermione Granger");
        graph.addNode("Rony Weasley");
        graph.addNode("Voldemort");

        // Adicionando as arestas 'follow' da rede social hipotética
        graph.addEdge(graph.getNode("Harry Potter"), graph.getNode("Hermione Granger"));
        graph.addEdge(graph.getNode("Harry Potter"), graph.getNode("Rony Weasley"));

        graph.addEdge(graph.getNode("Hermione Granger"), graph.getNode("Harry Potter"));
        graph.addEdge(graph.getNode("Hermione Granger"), graph.getNode("Rony Weasley"));

        graph.addEdge(graph.getNode("Rony Weasley"), graph.getNode("Harry Potter"));
        graph.addEdge(graph.getNode("Rony Weasley"), graph.getNode("Hermione Granger"));

        graph.addEdge(graph.getNode("Voldemort"), graph.getNode("Harry Potter"));

        // Imprimindo o Grafo → fulano segue ciclano
        graph.printGraph();
        
    }

    /**
     * Classe de representação do Grafo Direcionado
     */
    class DirectedGraph {

        // Listas de vértices e arestas, respectivamente
        protected List<Node> vertices;
        protected List<Edge> edges;

        /**
         * Construtor de inicialização das listas.
         */
        public DirectedGraph() {
            vertices = new ArrayList<Node>();
            edges = new ArrayList<Edge>();
        }

        // Getters de encapsulamento
        public List<Node> getVertices() {
            return vertices;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        Node getNode(String label) {
            for (Node n : vertices)
                if (n.label == label)
                    return n;

            return null;
        }

        /**
         * Informa o número de arestas que entram no vértice especificado.
         * 
         * @param node - vértice desejado
         * @return grau de entrada do vértice (quantidade de predecessores)
         */
        int getNodeInDegree(Node node) {
            int answer = 0;

            for (Edge e : edges)
                if (e.destination == node)
                    answer++;

            return answer;
        }

        /**
         * Adiciona um novo vértice ao grafo.
         * 
         * @param label - rótulo do novo vértice
         * @return o novo vértice criado
         */
        Node addNode(String label) {
            Node v = new Node(label);
            vertices.add(v);

            return v;
        }

        /**
         * Adiciona uma nova aresta ao grafo entre os vértices especificados.
         * 
         * @param origNode - vértice de origem
         * @param destNode - vértice de destino
         * @return a aresta criada
         */
        Edge addEdge(Node origNode, Node destNode) {
            Edge e = new Edge(origNode, destNode);
            origNode.addAdj(e);
            edges.add(e);

            return e;
        }

        /**
         * Exibe os vértices acompanhados de suas respectivas arestas.
         */
        void printGraph() {
            for (Node n : vertices) {
                System.out.print(n.label + " segue: ");

                for (Edge e : n.adj)
                    System.out.print(e.destination.label + " ");

                System.out.println();
            }
        }

    }

    /**
     * Classe de representação dos vértices
     */
    class Node {

        // Os atributos referem-se ao rótulo e às adjacências do vértice
        protected String label;
        protected List<Edge> adj;

        /**
         * Construtor para a inicialização do vértice.
         * 
         * @param label - rótulo do vértice
         */
        Node(String label) {
            this.label = label;
            this.adj = new ArrayList<Edge>();
        }

        // Getters e Setters de encapsulamento
        public String getLabel() {
            return label;
        }

        public List<Edge> getAdj() {
            return adj;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        /**
         * Informa o número de arestas que saem do vértice.
         * 
         * @return grau de saída (tamanho da lista de adjacência)
         */
        int getNodeOutDegree() {
            return this.adj.size();
        }

        /**
         * Adiciona uma aresta à lista de adjacência do vértice.
         * 
         * @param e - aresta a ser adicionada
         */
        void addAdj(Edge e) {
            adj.add(e);
        }

    }

    /**
     * Classe de representação das arestas
     */
    class Edge {

        // Esses dois atributos referem-se às coordenadas da aresta no grafo
        protected Node origin, destination;

        /**
         * Construtor para a inicialização dos atributos.
         * 
         * @param origNode - origem da aresta
         * @param destNode - destino da aresta
         */
        Edge(Node origNode, Node destNode) {
            this.origin = origNode;
            this.destination = destNode;
        }

        // Getters e Setters de encapsulamento
        public Node getOrigin() {
            return origin;
        }

        public Node getDestination() {
            return destination;
        }

        public void setOrigin(Node origin) {
            this.origin = origin;
        }

        public void setDestination(Node destination) {
            this.destination = destination;
        }

    }

}