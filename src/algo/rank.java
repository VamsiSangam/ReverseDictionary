package algo;

import java.util.ArrayList;
import java.util.PriorityQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rupesh
 */

class Pair implements Comparable<Pair>
{
    int first, last;
 
    public Pair(int first, int last) {
        this.first = first;
        this.last = last;
    }
 
    @Override
    public int compareTo(Pair o) {
        // This is where your mfing comparision takes place
        // Do whateva u want here
 
        //        returns
        //
        // a negative int if this < that
        // 0 if this == that
        // a positive int if this > that
        return this.first - o.first;
    } 
}

public class rank {
    public static int func(ArrayList<Integer> words, ArrayList<ArrayList<Integer> > adjacencyList) {
        int[] rank = new int[adjacencyList.size()];
        int[] dist = new int[adjacencyList.size()];
        
        // initialize rank to 0
        for (int i = 0; i < adjacencyList.size(); i++) {
            rank[i] = 0;
        }
        
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < adjacencyList.size(); j++) {
                dist[j] = adjacencyList.size() + 1;
            }
            
            dist[words.get(i)] = 1;
            
            PriorityQueue<Pair> queue = new PriorityQueue<Pair>();
            queue.add(new Pair(1, words.get(i)));
            
            while(!queue.isEmpty()) {
                Pair p = queue.peek();
                queue.remove();
                
                int idx = p.last;
                int cost = p.first;
                
                if (dist[idx] < cost) {
                    continue;
                }
                
                cost += 1;
                
                ArrayList arr = adjacencyList.get(idx);
                
                for (int k = 0; k < arr.size(); k++) {
                    if (dist[(int)arr.get(k)] > cost) {
                        dist[(int)arr.get(k)] = cost;
                        queue.add(new Pair(cost, (int)arr.get(k)));
                    }
                }
            }
            
            for (int j = 0; j < adjacencyList.size(); j++) {
                rank[j] += dist[j];
            }
        }
        
        int idx = -1;
        
        for (int i = 1; i < adjacencyList.size(); i++) {
            if (rank[i] != 0) {
                if (idx == -1) {
                    idx = i;
                } else if (rank[i] < rank[idx]) {
                    idx = i;
                }
            }
        }
        
        return idx;
    }
}


class TestClass
{
    int x, y, z;

    public TestClass(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
    
    
}
