package tests;

import org.junit.*;

import larryGraph.LarryGraph;

import static org.junit.Assert.*;

import java.util.Arrays;

public class StudentTests {

  @Test public void testingNewVertex() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  
	  assertTrue(graph.newLarryGraphVertex("dog"));
	  
	  assertFalse(graph.newLarryGraphVertex("dog"));
	  
	  try {
		  graph.newLarryGraphVertex(null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingIsVertex() {
	  assertTrue(TestData.exampleGraph1().isLarryGraphVertex("manatee"));
	  
	  assertFalse(TestData.exampleGraph1().isLarryGraphVertex("dog"));
	  
	  try {
		  TestData.exampleGraph1().isLarryGraphVertex(null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }

  @Test public void testingNewEdge() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  
	  assertTrue(graph.newLarryGraphEdge("dog", "cat", 3));
	  
	  assertTrue(graph.newLarryGraphEdge("dog", "dog", 0));
	  
	  assertFalse(graph.newLarryGraphEdge("dog", "cat", 10));
	  
	  assertFalse(graph.newLarryGraphEdge("cat", "cat", 12));
	  
	  assertFalse(graph.newLarryGraphEdge("cat", "dog", -20));
	  
	  try {
		  TestData.exampleGraph1().newLarryGraphEdge(null, "dog", 12);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
	 
  }
  
  @Test public void testingGetVertices() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  graph.newLarryGraphVertex("monkey");
	  
	  assertTrue(TestData.compareCollections(
              Arrays.asList("cat", "dog", "monkey"),
              graph.getLarryGraphVertices()));
  }
  
  @Test public void testingEdgeWeight() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  
	  assertEquals(20, graph.larryGraphEdgeWeight("dog", "cat"));
	  
	  assertEquals(-1, graph.larryGraphEdgeWeight("cat", "dog"));
	  
	  assertEquals(-1, graph.larryGraphEdgeWeight("cat", "monkey"));
	  
	  try {
		  TestData.exampleGraph1().larryGraphEdgeWeight(null, null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingRemoveEdge() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  
	  assertTrue(graph.removeLarryGraphEdge("dog", "cat"));
	  
	  assertFalse(graph.removeLarryGraphEdge("dog", "cat"));
	  
	  try {
		  TestData.exampleGraph1().removeLarryGraphEdge(null, null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingRemoveVertex() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  graph.newLarryGraphEdge("cat", "dog", 20);
	  
	  assertTrue(graph.removeLarryGraphVertex("cat"));
	  
	  assertEquals("dog{} ", graph.toString());
	  
	  try {
		  TestData.exampleGraph1().removeLarryGraphVertex(null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingChangeEdge() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  
	  assertTrue(graph.changeLarryGraphEdge("dog", "cat", 12));
	  
	  assertFalse(graph.changeLarryGraphEdge("dog", "cat", -1));
	  
	  assertFalse(graph.changeLarryGraphEdge("dog", "cat", -20));
	  
	  assertFalse(graph.changeLarryGraphEdge("cat", "dog", 20));
	  
	  try {
		  TestData.exampleGraph1().changeLarryGraphEdge(null, "cat", 20);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingGetNeighbors() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  graph.newLarryGraphVertex("monkey");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  graph.newLarryGraphEdge("dog", "monkey", 30);
	  
	  
	  
	  assertTrue(TestData.compareCollections(
              Arrays.asList("cat", "monkey"), graph.getNeighbors("dog")));
	  
	  assertTrue(TestData.compareCollections(
              Arrays.asList(), graph.getNeighbors("monkey")));
	  
	  try {
		  TestData.exampleGraph1().getNeighbors(null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingPredecessors() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  graph.newLarryGraphVertex("monkey");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  graph.newLarryGraphEdge("dog", "monkey", 30);
	  
	  assertTrue(TestData.compareCollections(
              Arrays.asList("dog"), graph.predecessorsOfVertex("cat")));
	  
	  assertTrue(TestData.compareCollections(
              Arrays.asList("dog"), graph.predecessorsOfVertex("monkey")));
	  
	  assertEquals(null, graph.predecessorsOfVertex("dog"));
	  
	  try {
		  TestData.exampleGraph1().predecessorsOfVertex(null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
  
  @Test public void testingCostOfPath() {
	  LarryGraph<String> graph= new LarryGraph<>();
	  graph.newLarryGraphVertex("dog");
	  graph.newLarryGraphVertex("cat");
	  graph.newLarryGraphVertex("monkey");
	  
	  graph.newLarryGraphEdge("dog", "cat", 20);
	  graph.newLarryGraphEdge("cat", "monkey", 20);
	  graph.newLarryGraphEdge("monkey", "dog", 30);
	  
	  int sum = graph.costOfPath(Arrays.asList("dog", "cat", "monkey", "dog"));
	  
	  assertEquals(70, sum);
	  
	  sum = graph.costOfPath(Arrays.asList("dog", "cat", "monkey"));
	  
	  assertEquals(40, sum);
	  
	  sum = graph.costOfPath(Arrays.asList("dog", "monkey"));
	  
	  assertEquals(-1, sum);
	  
	  try {
		  TestData.exampleGraph1().costOfPath(null);
	  } catch (IllegalArgumentException e) {
		  System.out.println(e.getMessage());
	  }
  }
}
