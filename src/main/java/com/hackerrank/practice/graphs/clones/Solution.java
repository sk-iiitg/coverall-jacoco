package com.hackerrank.practice.graphs.clones;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;

/**
 * Class Solution. This class implements a solution to the
 * "Nearest Clones" challenge published in Hacker Rank:
 * 
 * https://www.hackerrank.com/challenges/find-the-nearest-clone
 * 
 */
public class Solution extends DefaultChallengeSolution<Input, Integer> {

    public static class Node {
        public long color;
        public int id;
        public int depth;
        public List<Node> intermediate;
        public List<Node> terminals;
        public boolean visited = false;
        
        public Node(int id, long color) {
            this.id = id;
            this.color = color;
            this.depth = 0;
            this.intermediate = new ArrayList<>();
            this.terminals = new ArrayList<>();
        }
        
        public boolean equals(Object o) {
            if (o == null) return false;
            return (o instanceof Node) &&  this.id == ((Node) o).id;
        }
        
        public int hashCode() {
            return Integer.hashCode(this.id);
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(id: ")
              .append(this.id)
              .append(", c: ")
              .append(this.color)
              .append(", t: [");
            int i = 0;
            for(Node n : this.terminals) {
                if (i>0) {
                    sb.append(", ");
                }
                sb.append(n.id);
            }
            sb.append("], i: [");
            i=0;
            for(Node n : this.intermediate) {
                if (i>0) {
                    sb.append(", ");
                }
                sb.append(n.id);
            }
            sb.append("], d: ")
              .append(this.depth)
              .append(")");
              
            return sb.toString();
        }
    }


    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {

        int shortestPathToClone = this.findShortest(input.getGraphNodes(), input.getGraphFrom(), input.getGraphTo(), input.getIds(), input.getValue());
    
        return new DefaultChallengeResult<>(shortestPathToClone);
    }

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    protected int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {

        List<Node> heads = new ArrayList<>();
        Node[] nodes = new Node[graphNodes];
        for(int i=0; i<graphNodes; i++) {
            Node n = new Node(i+1, ids[i]); 
            nodes[i] = n;
            if (n.color == val) {
                heads.add(n);
            }
        }
        int arcs = graphFrom.length;
        for(int i=0; i<arcs; i++) {
            int from = graphFrom[i] - 1;
            int to = graphTo[i] - 1;
            
            Node nf = nodes[from];
            Node nt = nodes[to];
            
            // while we are building the arcs we can check
            // whether we have immediate neighbours of the
            // specified color.
            if (nf.color == nt.color && nf.color == val) {
                return 1;
            }
            
            if (nf.color == val) {
                nt.terminals.add(nf);
            } else {
                nt.intermediate.add(nf);
            }
            
            if (nt.color == val) {
                nf.terminals.add(nt);
            } else {
                nf.intermediate.add(nt);
            }
        }
        
        // if we are here this means that the two nodes
        // with the same color are at least distant by
        // one node. This will be used as out condition
        // to stop the search if we find a matching value.
        
        int minFound = Integer.MAX_VALUE;
        long wanted = val;
        
        boolean found = false;
        while (!heads.isEmpty() && minFound > 2) {
            // we pull another head from
            // the list of colors.
            Node head = heads.remove(0);
            head.visited = true;
            
            // System.out.println("(min: " + minFound +") -->[] <H>: " + head);
            
            Stack<Node> children = new Stack<>();
            // the first level of a head does not have
            // any color matching, so we don't need to
            // check for anything (i.e. we have a node
            // of the color desired)
            for(Node next : head.intermediate) {
                next.depth = 1;
                children.push(next);
            }
            
            // ok we are sure that this node
            // is connected.
            while (!children.isEmpty()) {
                
                boolean hasFoundShorter = false;
                Node next = children.pop();
                
                // System.out.println("(min: " + minFound + ")[]--> - " + next);
                
                if (next.depth < minFound) {
                    
                    int nextDepth = next.depth + 1;
                   
                    // we check first if there is a terminal node
                    // that we have note visited already in the
                    // neighbours,
                    if (!next.terminals.isEmpty()) {
                        for(Node ngt : next.terminals) {
                            
                            // if this is not where we started or something
                            // we checked in a previous cycle.
                            if (ngt.visited == false && nextDepth < minFound) {
                                
                                // System.out.println("<< New Min: " + nextDepth + ">>");
                                minFound = nextDepth;
                                hasFoundShorter = true;
                                break;
                            }
                        }
                    } 
                    if (!hasFoundShorter && !next.intermediate.isEmpty()) {
                        for(Node ngi : next.intermediate) {
                            // here we remove the link back to
                            // us because it does not help and
                            // we also remove those nodes that
                            // we have visited at a lower depth
                            // because revisiting them at this
                            // depth will not help finding a
                            // smaller path.
                            if (ngi != next && 
                                (ngi.depth == 0 || ngi.depth > nextDepth)) {
                                ngi.depth = nextDepth;
                                children.push(ngi);
                                
                                // System.out.println("(min: " + minFound +") -->[] - " + head);
   
                            }
                        }
                    }
                }
            }
        }
        
        return minFound == Integer.MAX_VALUE ? -1 : minFound;
    }

}