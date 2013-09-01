package week1;

public class WeightedQuickUnionUF implements UnionFinder {

    private int[] root;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int N) {
        root = new int[N];
        size = new int[N];
        for (int i=0; i<N; i++) {
            root[i] = i;
            size[i] = 1;
        }
        count = N;
    }

    @Override
    public boolean isConnected(int a, int b) {
        return (find(a) == find(b));
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public void connect(int a, int b) {
        int r1 = find(a);
        int r2 = find(b);
        if (r1 == r2) { return; }
        if (size[r1] < size[r2]) { root[r1] = r2; size[r2] += size[r1]; }
        else                     { root[r2] = r1; size[r1] += size[r1]; }
        count--;
    }

    @Override
    public int find(int a) {
        while (a != root[a]) {
            a = root[a];
        }
        return a;
    }
}