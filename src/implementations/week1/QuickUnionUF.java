package week1;

public class QuickUnionUF implements UnionFinder {

    private int[] arr;
    private int count;

    public QuickUnionUF(int N) {
        arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = i;
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
        arr[r1] = r2;
        count--;
    }

    @Override
    public int find(int a) {
        while (a != arr[a]) {
           a = arr[a];
        }
        return a;
    }
}