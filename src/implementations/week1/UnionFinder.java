package week1;

public interface UnionFinder {

    /** Check if two items belong to the same set **/
    public boolean isConnected(int a, int b);

    /** Returns the number of connected sets **/
    public int count();

    /** Merge two sets, connects A and B **/
    public void connect(int a, int b);

    /** Find the class to which an element belongs **/
    public int find(int a);
}

