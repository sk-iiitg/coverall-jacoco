package com.hackerrank.practice.graphs.distances;

import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;



import com.hackerrank.core.ChallengeSolution;

/**
 * Class Solution. This class implements the solution
 * to the Hacker Rank challenge: "BFS Shortest Reach in
 * a Graph".
 * 
 * https://www.hackerrank.com/challenges/ctci-bfs-shortest-reach
 */
public class Solution implements ChallengeSolution<Input,Result> {

    public static class Node {
        
        public int id;
        public long distance = -1;
        public Set<Node> neighbours;
        
        public Node(int id) {
            this.id = id;
            this.neighbours = new HashSet<>();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(id: ")
              .append(this.id)
              .append(", d: ")
              .append(this.distance)
              .append(", n: [");
            int i=0;
            for(Node n : this.neighbours) {
                if (i++>0) {
                    sb.append(", ");
                }
                sb.append(n.id);
            }
            sb.append("])");
            
            return sb.toString();
        }    
        
        public boolean equals(Object o) {
            
            if (o instanceof Node) {
                Node other = (Node) o;
                return this.id == other.id;
            }
            
            return false;
        }    
        
        public int hashCode() {
            return Integer.hashCode(this.id);
        }
    }


    @Override
    public boolean accept(String testCaseId) {
        return true;
    }

    @Override
    public Result execute(Input input) {

        long[] distances = this.computeDistances(input.getN(), input.getGraphFrom(), input.getGraphTo(), input.getStartNode());
        return new Result(distances);
    }

    protected long[] computeDistances(int n, int[] graphFrom, int[] graphTo, int startNode) {
     
        Node[] nodes = new Node[n];
        for(int i = 0; i<n; i++) {
            nodes[i] = new Node(i+1);
        }
        
        
        for(int j=0; j<graphFrom.length; j++) {
            int from = graphFrom[j];
            int to = graphTo[j];
            
            Node nodeFrom = nodes[from-1];
            Node nodeTo = nodes[to-1];
            
            
            if (from == startNode) {
                nodeTo.distance = 6;
            } 
            
            if (to == startNode) {
                nodeFrom.distance = 6;
            } 
            
            nodeFrom.neighbours.add(nodeTo);
            nodeTo.neighbours.add(nodeFrom);
            
        }
        
        Node head = nodes[startNode - 1];
        // we use a special marker for the
        // distance of the head node so that
        // we don't consider it by mistake.
        head.distance = - 2;
        /*
        for(int i=0; i<n; i++) {
            System.out.println("" + nodes[i]);
        }
        */
        // we add all the children of the
        // current node, which is the head.
        Queue<Node> left = new LinkedList<>();
        for(Node ng : head.neighbours) {
            left.add(ng);   
        }
        while(!left.isEmpty()) {
            
            // every node that is added to the queue
            // has a distance, we only re-add the node
            // to if we can reach it through a shorter
            // distance, which causes the neighbouring
            // nodes to be recomputed again.
            Node father = left.poll();
            for(Node ng : father.neighbours) {
                
                if (ng != head) {
                
                    long d = ng.distance;
                    if (d == -1 || d > father.distance + 6) {
                        ng.distance = father.distance + 6;
                        left.add(ng);
                    } 
                }
            } 
        }
        
        // now that we have computed the distances of all the
        // nodes, we can simply copy all the distances to the
        // final array by skipping the start node.
        long[] distances = new long[n-1]; 
        int l = 0;
        for(int k=1; k<=n; k++) {
            if (k != startNode) {
                distances[l++] = nodes[k-1].distance;
            }
        }
        return distances;   
    }
}