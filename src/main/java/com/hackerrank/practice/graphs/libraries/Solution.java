package com.hackerrank.practice.graphs.libraries;


import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
// import java.lang.StringBuilder;

import com.hackerrank.core.DefaultChallengeResult;
import com.hackerrank.core.DefaultChallengeSolution;

public class Solution extends DefaultChallengeSolution<Input,Long> {


    /**
     * Class Node. Stores the information about a city and
     * the possible roads that can be built to connect to
     * other cities.
     */
    public static class Node {

        // unique identifier of the node (city).
        public int id;
        // neighbouring cities
        public Set<Node> nodes;
        
        // these two are provided for fast access to
        // the next and previous records when the
        // city is added in a list that requires
        // scanning with removals.
        public Node next;
        public Node previous;

        /**
         * Initialises an instance of the Node for the city
         * with the specified identifier.
         */
        public Node(int id) {

            this.id = id;
            this.nodes = new HashSet<>();
        }

        /**
         * Adds a neighbouring city.
         */
        public void add(Node n) {
            this.nodes.add(n);
        }

        /**
         * Gets the hashcode for this instance of the node.
         * This is the unique identifier of the city that
         * the node maps.
         */
        public int hashCode() {
            return Integer.hashCode(this.id);
        }
        
        /**
         * Prints out the information about the node in the
         * form of a pair containing the identifier of the
         * city and the array of neighbouring cities.
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("(id: ")
              .append(this.id)
              .append(", children: [");
            int i=0;
            for (Node n : this.nodes) {
                if (i>0) {
                  sb.append(", ");
                }
                sb.append(n.id);
                i++;
            }  
            sb.append("])");
            
            return sb.toString();
        }
    }
    
    @Override 
    public DefaultChallengeResult<Long> execute(Input input) {

        long cost = this.roadsAndLibraries(input.getCitiesCount(), input.getLibraryCost(), input.getRoadCoast(), input.getCities());
        return new DefaultChallengeResult<>(cost);
    }

    /*
     * Complete the 'roadsAndLibraries' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER c_lib
     *  3. INTEGER c_road
     *  4. 2D_INTEGER_ARRAY cities
     */

    protected long roadsAndLibraries(int n, int c_lib, int c_road, List<List<Integer>> cities) {
        // Write your code here
           
        // if the cost of the libraries is less than the cost
        // of the roads it is easier to build a library in each
        // city because we will be spending less.

        // we need to find how many cities we have in total
        // and build a data structure that enables us to
        // traverse it.
        
        // System.out.println("n: " + n  + ", c_lib: " + c_lib + ", c_road: " + c_road);


        if (n == 1) {
            return c_lib;
        }
       
        long cost = 0;
        long lc_lib = (long) c_lib;
        long lc_road = (long) c_road;
        
        if (c_lib <= c_road) {
             
            // System.out.println("All libraries");
            cost = lc_lib * n;

        } else {

            // the data may not be a connected graph. Hence we need
            // to pre-create all node to be sure that we don't get
            // mad later trying to figure out those that are missing.
            Node previous = null;
            Node[] nodes = new Node[n];
            for(int i=1; i<=n; i++) {
                Node current = new Node(i);

                // while we create the nodes we also link them
                // one to another (see later)
                current.previous = previous;
                if (previous != null) {
                    previous.next = current;
                } 
                nodes[i-1] = current;
                previous = current;
            }
            
            // this loop populates the roads into our data structure
            // by adding the possible links to each of the cities.
            //
            for (List<Integer> roads : cities) {

                int v = roads.get(0);
                int u = roads.get(1);

                Node nv = nodes[v-1];
                Node nu = nodes[u-1];
                nv.add(nu);
                nu.add(nv);
            }

            // given a group of fully connected nodes
            // we only need one libraries because the
            // adding one additional libray will only
            // help us remove one link, and given that
            // the link is less than the library there
            // is no gain in reducing the cost.

            // therefore we simply need to find the 
            // the number of disjoint sets. To compute
            // the disjoint sets we start from a node
            // and travel through all the links
            Queue<Node> left = new LinkedList<>();

            // we start from the first city, and then
            // we move forward until there are no more
            // cities to visit.
            //
            Node head = nodes[0];
            
            while(head != null) {

                // this set contains the collection
                // of nodes that we have encountered
                // along the way.
                Set<Node> seen = new HashSet<>();

                // the size of the map is the same
                // as the size of the list because
                //
                seen.add(head);
                left.add(head);   
                
                // System.out.println("New node: (id: " + head.id + ")");
                
                                   
                // since the list is not empty we can
                // always pull the first element, which
                // we put in the queue and in the list
                // of seen nodes.
                
                do {
                    Node next = left.poll();
                    for(Node connected : next.nodes) {
                        boolean notSeenBefore = seen.add(connected);
                        if (notSeenBefore) {
                            left.add(connected);
                            if (connected.next != null) {
                                connected.next.previous = connected.previous;
                            }
                            if (connected.previous != null) {
                                connected.previous.next = connected.next;
                            }
                            //System.out.println("Removing (id: " + connected.id +")");
                            
                        }
                    }
                    
                } while (!left.isEmpty());
                
                cost +=  lc_lib + (seen.size()-1) * lc_road;
                
                // unlink the current head now 
                // that has been moved properly.

                // NOTE: if we unlink the head before we have
                //       processed all the nodes in the queue
                //       we may end up having the head to point
                //       to a deleted item.
                head = head.next;
                if (head != null) {
                    head.previous = null;
                }
            }

        }

        return cost;

   }

}