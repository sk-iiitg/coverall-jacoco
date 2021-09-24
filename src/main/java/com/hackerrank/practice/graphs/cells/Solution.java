package com.hackerrank.practice.graphs.cells;


import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class Solution. This class implements the solution to the
 * Hacker Rank challenge: "Connected Cells in a Grid".
 * 
 * https://www.hackerrank.com/challenges/ctci-connected-cell-in-a-grid
 * 
 * The solution builds a graph where the nodes represents those
 * positions in the cell that have 1 and arcs adjacency relationships
 * betwen such cells.
 * 
 * When converted to this structure finding the largest region
 * filled with ones is equivalent to finding the largest disjoint
 * set of nodes in the graph.
 */
public class Solution extends DefaultChallengeSolution<Input,Integer> {


    public static class Node {

        public int id;
        public int val;
        public boolean visited = false;
        public Set<Node> neighbours;

        public Node(int id, int val) {
            this.id = id;
            this.val = val;
            this.neighbours = new HashSet<>();
        }
    }


    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {

        int maxRegionSize = this.maxRegion(input.getGrid());
        return new DefaultChallengeResult<>(maxRegionSize);
    }

    protected int maxRegion(List<List<Integer>> grid) {


        // given the constraints we always have 
        // one entry in the list 0 < n,m < 10.
        int n = grid.size();
        int m = grid.get(0).size();

        // we buffer all the nodes.
        Map<Integer,Node> nodes = new HashMap<>();

        int j = 0;
        for(List<Integer> row: grid) {
            int k = 0;
            for(Integer val : row) {

                int id = j*m + k;
                if (val == 1) {
                    Node one = new Node(id,val);
                    nodes.put(id, one);
                    
                    System.out.println("[" + j + "," + k + "]: <Node> (id: " + id + ")");

                    // we also generate the links to the neighbours
                    // as we scan the matrix forward first by colums
                    // in a line and then by line. The only nodes
                    // already created are:
                    // id-m-1, id-m, id-m+1
                    // id-1,    id

                    Node ng = null;
                    int idg = -1;

                    if (j> 0) {
                        if (k>0) {
                            idg = id - m - 1;
                            ng = nodes.get(idg);
                            if (ng != null) {
                                one.neighbours.add(ng);
                                ng.neighbours.add(one);
                                
                                System.out.println("[" + j + "," + k + "]: <Arc> -> [" + idg % m + "," + idg / m + "] (id: " + idg + ")");
                            }
                        }

                        idg = id - m;
                        ng = nodes.get(idg);
                        if (ng != null) {
                            one.neighbours.add(ng);
                            ng.neighbours.add(one);
                            
                            System.out.println("[" + j + "," + k + "]: <Arc> -> [" + idg % m + "," + idg / m + "] (id: " + idg + ")");
                        } 

                        if (k<m-1) {

                            idg = id - m+1;
                            ng = nodes.get(idg);
                            if (ng != null) {
                                one.neighbours.add(ng);
                                ng.neighbours.add(one);
                                
                                System.out.println("[" + j + "," + k + "]: <Arc> -> [" + idg % m + "," + idg / m + "] (id: " + idg + ")");
                            } 
    
                        }
                    }
                    if (k > 0) { 
                        idg = id - 1;
                        ng = nodes.get(idg);
                        if (ng != null) {
                            one.neighbours.add(ng);
                            ng.neighbours.add(one);
                            
                            System.out.println("[" + j + "," + k + "]: <Arc> -> [" + idg % m + "," + idg / m + "] (id: " + idg + ")");
                        }
                    }

                }
                k++;
            }
            j++;
        }
    
        int max = 0;
        Queue<Node> left = new LinkedList<>();

        // at this point we got all nodes that have
        // 1 as not null in the map, what we need to
        // do is to count the largest set of visited
        // nodes (largest disjoint set)
        //
        for(Node one : nodes.values()) {

            // this ensures that if for any reasons
            // all the nodes we have found have already
            // been visited, then we don't do this again.

            // a isolated region will have n.visited that
            // is set to false, because we have not reached
            // it yet.
            
            System.out.print("N: (id: " + one.id + ", v: " + one.visited + ") - ");
            if (!one.visited) {
                
                System.out.println("[Traverse]");
                
                int progress = 0;
                Node next = one;
                next.visited = true;
                
                do {
                    progress++;
                    System.out.println("Next: (id: " + next.id + ", v: " + next.visited + ")");
                    for(Node ng : next.neighbours) {
                        
                        
                        System.out.print("   --> NG: (id: " + ng.id + ", v: " + ng.visited + ") - ");
                        // we don't need to check
                        // whether the node is the
                        // same as n, because for
                        // that visited is true.
                        if (!ng.visited) {
                            ng.visited = true;
                            left.add(ng);
                            System.out.println("[Traverse]");
                        } else {
                            System.out.println("[Skipped]");
                        }
                    }
                    next = left.poll();
                } while (next != null);

                if (progress > max) {
                    max = progress;
                }
            } else {
                
                System.out.println("[Skipped]");
                
            }
        }
            
        return max;
    }


}