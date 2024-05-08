package larryGraph;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*  Mia Walsh, mwalsh27, 119691915, CMSC132 - 0303 
 * 
 * The LarryGraph class holds information about the vertices in a directed
 * graph where edges are weighted. LarryGraphs are only allowed at most
 * one edge. A LarryGraph uses an adjacency map with a generic parameter
 * V representing the vertex. The second parameter is a HashMap representing
 * the neighbors of the associated vertex with parameters V, the neighbor 
 * vertex and an integer representing the weight of the edge. If any method
 * parameters are null, an IllegalArgumentException is thrown.
 * 
 * I pledge on my honor that I have not given nor received any unauthorized 
 * assistance on this assignment. */

public class LarryGraph<V> {

	private Map<V, HashMap<V, Integer>> adjMap = new HashMap<>();

	/* This method adds a new vertex to the graph if it is not already
	 * present. Initially, the added vertex will not have any edges. 
	 * @param vertexInfo The pending vertex being added to the graph
	 * @return true if the vertex was successfully added, false otherwise */
	
    public boolean newLarryGraphVertex(V vertexInfo) {
        
    	if (vertexInfo != null) {
    		
    		// vertexInfo is not present
    		if (adjMap.get(vertexInfo) == null) { 
    			
    			adjMap.put(vertexInfo, new HashMap<>());
    			// Empty map is not the same as null
    			return true;
    		}
    		
    		// vertexInfo is already present
    		return false; 
    	}
    	
    	throw new IllegalArgumentException("Illegal argument");
    }

    /* This method confirms whether or not a certain vertex in the current 
     * graph.
     * @param vertexInfo The vertex being found in the graph
     * @return true if vertex is found, false otherwise */
    
    public boolean isLarryGraphVertex(V vertexInfo) {
       
    	if (vertexInfo != null) {
    		
    		return adjMap.containsKey(vertexInfo);
    	}
    	
    	throw new IllegalArgumentException("Illegal Argument");
    }

    /* This method finds and adds all the vertices from the current graph 
     * to an independent set. 
     * @return an object of the Collection interface */
    
    public Collection<V> getLarryGraphVertices() {
        
    	Set<V> vertices = new HashSet<>();
    	
    	// Return value must be independent from current graph object
    	for (V vertex : adjMap.keySet()) {
    		vertices.add(vertex);
    	}
    	
    	return vertices;
    }

    /* This method creates a weighted edge that goes from one vertex
     * to another. 
     * @param goesFrom The vertex the edge starts from
     * @param goesTo The vertex the edge is directed toward
     * @return true if edge was added, false otherwise */
    
    public boolean newLarryGraphEdge(V goesFrom, V goesTo, int weight) {
        
    	boolean retVal = false;
    	
    	if (goesFrom != null && goesTo != null) {
    		
    		if (!adjMap.get(goesFrom).containsKey(goesTo) && weight >= 0) {
    			// Ensure both vertices are added
    			newLarryGraphVertex(goesFrom);
    			newLarryGraphVertex(goesTo);
    		
    			// If vertices are equal, weight must be 0
    			if (goesFrom.equals(goesTo) && weight == 0) {
    			
    				adjMap.get(goesFrom).put(goesTo, 0);
    				retVal = true;
    			
    			} else if (!goesFrom.equals(goesTo)) { 
    			
    				// Create edge from goesFrom to goesTo
    				adjMap.get(goesFrom).put(goesTo, weight);
    				retVal = true;
    			}
    		}
    		
    		return retVal;
    	}
    	
    	throw new IllegalArgumentException("Illegal argument");
    }

    /* This method finds the weight of the edge between two vertices. 
     * @param goesFrom The starting vertex of the directed edge
     * @param goesTo The vertex the outgoing edge is directed to
     * @return the weight value of the edge if it exists, -1 if not */
    
    public int larryGraphEdgeWeight(V goesFrom, V goesTo) {
        int retVal = -1;
        
        if (goesFrom != null && goesTo != null) {
        	
        	for (V vertex : adjMap.keySet()) { // Finds goesFrom
        		
        		if (vertex.equals(goesFrom)) {
        			
        			// Need to find goesTo
        			for (V neighbor : adjMap.get(vertex).keySet()) { 
        				
        				if (neighbor.equals(goesTo)) {
        					
        					// If both are found, weight is computed
        					retVal = adjMap.get(vertex).get(neighbor);
        				}
        			}
        		}
        	}
        	return retVal;
        }
        
        throw new IllegalArgumentException("Illegal argument");
    }

    /* This method attempts to remove the edge between two vertices.
     * @param goesFrom The starting vertex of the outgoing edge
     * @param goesTo The vertex the outgoing edge points to
     * @return true if edge was able to be removed, false otherwise */
    
    public boolean removeLarryGraphEdge(V goesFrom, V goesTo) {
        
        if (goesFrom != null && goesTo != null) {
        	
        	if (adjMap.containsKey(goesFrom)) { // Finds goesFrom
        		
        		// Try to remove edge from between the vertices, if applicable
        		return adjMap.get(goesFrom).remove(goesTo, 
        				larryGraphEdgeWeight(goesFrom, goesTo));
        	}
        }
        
        throw new IllegalArgumentException("Illegal argument");
    }
        
    /* This method removes a vertex from the graph with all associated
     * edges.
     * @param vertexInfo The vertex pending removal
     * @return true if the vertex is successfully removed, false otherwise */
    
    public boolean removeLarryGraphVertex(V vertexInfo) {
        boolean retVal = false;
        
    	if (vertexInfo != null) {
    		
    		if (adjMap.containsKey(vertexInfo)) {
    			
    			// Must remove all incoming edges
    			for (V neighbor : adjMap.get(vertexInfo).keySet()) {
    				
    				removeLarryGraphEdge(neighbor, vertexInfo);
    			}
    			// Outgoing edges are removed here
    			adjMap.remove(vertexInfo);
    			retVal = true;
    		}
    		return retVal;
    	}
    	
    	throw new IllegalArgumentException("Illegal argument");
    }

    /* This method changes the weight of an already existing edge. It does so
     * by removing the current edge and adding a new one with the new weight.
     * @param goesFrom The starting vertex of the outgoing edge
     * @param goesTo The vertex the outgoing edge points to
     * @return true if edge weight was changed, false otherwise */
    
    public boolean changeLarryGraphEdge(V goesFrom, V goesTo,
                                        int newWeight) {
        boolean retVal = false;
    	
        if (goesFrom != null && goesTo != null) {
        	
        	// Edge has to be an already existing edge
        	if (larryGraphEdgeWeight(goesFrom, goesTo) != -1) {
        		
        		removeLarryGraphEdge(goesFrom, goesTo);
        		retVal = newLarryGraphEdge(goesFrom, goesTo, newWeight);
        	}
        	
        	return retVal;
        }
        
        throw new IllegalArgumentException("Illegal argument");
    }

    /* This method finds all the neighbors of vertexInfo. A neighbor
     * is a vertex with an outgoing edge from vertexInfo. 
     * @param vertexInfo The target vertex in the graph
     * @return an object of the Collection interface containing all the
     * neighbors of the target vertex */
    
    public Collection<V> getNeighbors(V vertexInfo) {
        
        if (vertexInfo != null) {
        	
        	if (adjMap.containsKey(vertexInfo) && 
        			adjMap.get(vertexInfo) != null) {
        		
        		// Return value needs to be an independent set
                Set<V> neighbors = new HashSet<>();
        		
        		// Iterate through vertices with an outgoing edge
        		for (V neighbor : adjMap.get(vertexInfo).keySet()) {
        			
        			neighbors.add(neighbor);
        		}
        		
        		return neighbors;
        	}
        	
        	return null;
        }
        
        throw new IllegalArgumentException("Illegal argument");
    }

    /* This method finds all the predecessors of a certain vertex. A
     * predecessor is a neighbor of destVertex that has an outgoing edge
     * to destVertex.
     * @param destVertex The target vertex
     * @return an independent object of the Collection interface with
     * all the predecessors of the target vertex */
    
    public Collection<V> predecessorsOfVertex(V destVertex) {
    	
    	if (destVertex != null) {
    		
    		if (adjMap.containsKey(destVertex)) {
    			
    			// Return value needs to be an independent set
    	    	Set<V> predecessors = new HashSet<>();
    			
    			// Iterate through each vertex
    			for (V vertex : adjMap.keySet()) {
    				
    				// Check if there is an edge from vertex to destVertex
    				if (larryGraphEdgeWeight(vertex, destVertex) != -1) {
    					
    					predecessors.add(vertex);
    				}
    			}
    			
    			if (!predecessors.isEmpty()) 
    				return predecessors;
    		}
    		
    		return null;
    	}
    	
    	throw new IllegalArgumentException("Illegal argument");
    }

    /* This method adds up all the edges between the vertices of 
     * candidatePath only if it is a valid path through the graph. 
     * @param candidatePath A list of an ideal path through the graph
     * @return the sum of all the edge weights if a valid list was passed
     * in, -1 otherwise */
    
    public int costOfPath(List<V> candidatePath) {
        
    	int sum = 0;
    	V prev = null, current = null;
    	
    	if (candidatePath != null) {
    		
    		for (V vertex : candidatePath) {
    			
    			current = vertex;
    			
    			if (prev != null && current != null) {
    				
    				// Check for a valid edge
    				if (larryGraphEdgeWeight(prev, current) != -1) {
    					
    					sum += larryGraphEdgeWeight(prev, current);
    					
    				} else {
    					
    					// If there is ever an invalid edge, -1 can be 
    					// immediately returned
    					return -1;
    				}
    			}
    			prev = current;
    		}
    		return sum;
    	}
    	
    	throw new IllegalArgumentException("Illegal argument");
    }

    public String toString() {
    	String retVal = "";
    	
    	for (V vertex : adjMap.keySet())  {
    		retVal += vertex + adjMap.get(vertex).toString() + " ";
    	}
    	
    	return retVal;
    }
}
