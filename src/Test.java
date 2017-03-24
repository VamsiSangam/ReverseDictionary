
import java.util.PriorityQueue;

public class Test {

    public static void main(String[] args) {
        PriorityQueue<Pair> q = new PriorityQueue<>();
        
        q.add(new Pair(10, 20));
        q.add(new Pair(20, 30));
        q.add(new Pair(30, 10));
        q.add(new Pair(5, 20));
        q.add(new Pair(4, 20));
        q.add(new Pair(3, 20));
        
        while (!q.isEmpty()) {
            System.out.println(q.poll().getFirst());
        }
    }
}

class Pair implements Comparable<Pair>
{
    private int first, last;

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

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
    
    
}
