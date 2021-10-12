package com.hackerrank.practice.graphs.machines;

import com.hackerrank.core.DefaultChallengeSolution;
import com.hackerrank.core.DefaultChallengeResult;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

/**
 * Class <b>Solution</b>. This class implements the solution to the
 * Hacker Rank Matrix problem described here:
 * 
 * https://www.hackerrank.com/challenges/matrix
 * 
 * The implementation uses disjoint sets to keep track of the network
 * nodes that are connected as edges are added. The criteria for adding
 * an edge is ensuring that with the inclusion of the edge there is always
 * only one node that contains "machines". If the list of edges is ordered
 * in descending order we will be removing the edges that have the least
 * cost. 
 * 
 * The assumption that the original graph is a tree and there aren't
 * any cycles makes the algorithm work.
 */
public class Solution extends DefaultChallengeSolution<Input,Integer> {

    /**
     * Class <b>Node</b>. This class defines the building block
     * of the disjoint set. The node contains a value and a 
     * pointer to the parent node. The standard structure of the
     * node for a disjoint set is augmented by adding the flag
     * thay keeps track whether the node has a machine, which
     * is essential to the solution to the problem.
     */
    public static class Node {
        protected boolean hasMachine;
        protected Node parent;
        protected int value;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * Class <b>DisjointSets</b>. This class is a wrapper that
     * keeps track of the disjoint sets as nodes are added and
     * connected in the graph. The implementation features three
     * main methods:
     * - {@link DisjointSets#add(int)} to add a node.
     * - {@link DisjointSets#union(int, int)} to merge two sets.
     * - {@link DisjointSets#findRoot(Node)} to find the root of a set
     */
    public static class DisjointSets {

        /**
         * This is a map that keeps track of all the nodes
         * added to the graph.
         */
        protected Map<Integer, Node> nodes = new HashMap<>();

        /**
         * Adds a node to the graph. The method returns the
         * root node of the disjoint set that the node is 
         * associated with. If the node is not present the
         * method adds the node and returns it.
         * 
         * @param value     a {@literal int} value representing
         *                  the value of the node to add. This  
         *                  is used as a key to check whether
         *                  the node already exists.
         * 
         * @return  a {@link Node} implementation representing
         *          the disjoint set of that contains the node.
         *          For nodes not already present in the graph
         *          this node contains the value directly.
         */
        public Node add(int value) {

            Node n = this.nodes.get(value);
            if (n == null) {
                n = new Node(value);
                this.nodes.put(value, n);
            } else {
                n = this.findRoot(n);
            }
            return n;
        }

        /**
         * Merges the two disjoint sets that are associated to the
         * two nodes passed as argument, and returns the resulting
         * disjoint set. If the two nodes belong to the same set 
         * nothing is done, if the two nodes belong to different sets
         * the merging is performed by taking as root the one that
         * contains machine if any, otherwise it the first.
         * 
         * @param value1    identifier of the first node
         * @param value2    identifier of the second node
         * 
         * @return  a {@link Node} representing the root
         *          of the disjoint set created by merging
         *          the two disjoint sets associated to the
         *          values passed as arguments.
         */
        public Node union(int value1, int value2) {

            // we need to check that both disjoint sets
            // are contained in the list of sets otherwise
            // we cannot add them.

            Node n1 = this.nodes.get(value1);
            Node n2 = this.nodes.get(value2);
            if (n1 == null || n2 == null) {
                throw new IllegalArgumentException(String.format("Either %1$d or %2$d is not in the set (value: %1$d, included: %3$s), (value: %2$d, included: %4$s).", 
                                                                value1, value2, n1 != null, n2 != null));
            }

            Node r1 = this.findRoot(n1);
            Node r2 = this.findRoot(n2);
            if (r1 != r2) {
                
                if (r1.hasMachine) {
                    r2.parent = r1;
                } else {
                    r1.parent = r2;
                    r1 = r2;
                }
            }
            return r1;
        }
        /** 
         * 
         * This method returns the root node that matches
         * the set that contains the given value. If the
         * the value is not found it returns {@literal null}.
         */
        protected Node findRoot(Node n) {
            if (n != null) {
                while(n.parent != null) {
                    n = n.parent;
                }
            }
            return n;
        }
    }

    /**
     * @inheritDoc
     * 
     * The current implementation unwraps the list of roads and machines
     * contained in the input and invokes {@link #minTime(List, List)} to
     * determine the minimum cost required to prevent connections between
     * cities with machines. The value is wrapped in a {@link DefaultChallengeResult}
     * instance and returned to the caller.
     */
    @Override
    public DefaultChallengeResult<Integer> execute(Input input) {

        int value = this.minTime(input.getRoads(), input.getMachines());
        return new DefaultChallengeResult<>(value);
    }

    /**
     * Implementation of the solution to the matrix problem. The method first
     * orders the list of machines in descending order according to cost of
     * disrupting a road. It then loops over the ordered list to create the nodes 
     * for the cities and adding the edges representing the roads. An edge is added
     * if and only if it does not connect two cities that contain machines, in 
     * that case the cost of the road is added to the running total that is then
     * returned by the method at the end of the loop. Ordering the roads in 
     * discending order ensures that we remove always the edge that cost the 
     * least to achieve what we want.
     * 
     * @param roads     a {@link List} of triplets of integer representing the
     *                  roads that connect two cities. The first two values are
     *                  the identifiers of the city. The third value is the cost
     *                  of the road.
     * 
     * @param machines  a {@link List} of cities that contain machines.
     * 
     * @return  an integer value representing the minimum cost required to keep
     *          the cities with machines apart. This value is obtained by summing
     *          the cost of all the roads removed from the graph.
     */
    protected int minTime(List<List<Integer>> roads, List<Integer> machines) {

        // we sort the roads by adding those with bigger cost earlier. As need to remove
        // (i.e. destroy) roads with the minimum cost and we have the assumption that there
        // aren't loops, then as we proceed adding the roads to the graph, we will encounter
        // smaller and smaller roads at the first detection of the road to remove, we are sure
        // that we pick the smaller one we have.
        //
        Collections.sort(roads, (List<Integer> r1, List<Integer> r2) ->  {
                int w1 = r1.get(2);
                int w2 = r2.get(2);

                return (w1 == w2 ? 0 : (w1 > w2 ? -1 : 1));
            }
        );

        // we also need to prepare the list of machines in a much more consumable way, since
        // we need to be able to quickly check whether a given city contains a machine or not
        // for this reason we can use a set.

        Set<Integer> citiesWithMachines = new HashSet<>(machines);


        int total = 0;
        int i = 0;

        DisjointSets sets = new DisjointSets();

        // the algorithm is the following:
        // - we keep adding roads by creating disjoint sets
        // - for each pair we add we check whether the roads
        //   connects a city with a city with machines, if so
        //   we keep track of it and remove.
        // - whenever we remove we add the cost of destroying 
        //   the road to the running total.

        for(List<Integer> road : roads) {

            int c1 = road.get(0);
            int c2 = road.get(1);
            int w = road.get(2);

            System.out.print(String.format("%1$-7d: [t: %2$d] - [%3$d, %4$d, %5$d] ==> ", i, total, c1, c2, w));
            
            boolean c1HasMachine = citiesWithMachines.contains(c1);
            boolean c2HasMachine = citiesWithMachines.contains(c2);    

            System.out.print(String.format("(%1$d:%2$b, %3$d:%4$b) ==> ", c1, c1HasMachine, c2, c2HasMachine));


            // this operation retrieves or creates a set that
            // contains the c1. The first time it creates the
            // set and the second time it does retrieve it. 
            // The value of hasMachine is set the previous value
            // or the value of the current city. Being a logical
            // or, it is true if it was true before or it is true
            // with the last insertion.
            Node n1 = sets.add(c1);
            n1.hasMachine = n1.hasMachine || c1HasMachine;

            // same as above.
            //
            Node n2 = sets.add(c2);
            n2.hasMachine = n2.hasMachine || c2HasMachine;


            System.out.print(String.format("(%1$d: %2$d:%3$b, %4$d: %5$d:%6$b) ==> ", c1, n1.value, n1.hasMachine, c2, n2.value, n2.hasMachine));

            // because we don't have cycles, we should not
            // have djc1 and djc2 as the same set, otherwise
            // we have cycle even before joining.

            // if both sets have a machine we cannot join them
            // this means that we need to blow up the road that
            // connects them, adding to the total running cost.
            //
            if (n1.hasMachine && n2.hasMachine) {

                total += w;
                System.out.println(String.format("REMOVE: t=%d", total));

            } else {
                // otherwise it is fine to connect them.
               Node n = sets.union(c1, c2);

               System.out.println(String.format("ADD: %1$d + %2$d = %3$d:%4$b", c1, c2, n.value, n.hasMachine));
            }

            i++;

        }
        return total;
    }
}

