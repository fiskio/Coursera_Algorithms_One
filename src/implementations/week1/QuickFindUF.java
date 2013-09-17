package week1;

public class QuickFindUF implements UnionFinder {

    private int[] arr;
    private int count;

    public QuickFindUF() {}

    public QuickFindUF(int N) {
        arr = new int[N];
        for (int i=0; i<N; i++) {
            arr[i] = i;
        }
        count = N;
    }

    public UnionFinder newUnionFinder(int N) {
        return new QuickFindUF(N);
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean isConnected(int a, int b) {
        return (arr[a] == arr[b]);
    }

    @Override
    public void connect(int a, int b) {
        if (isConnected(a, b))
            return;
        int oldId = arr[a];
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == oldId) {
                arr[i] = arr[b];
            }
        }
        count--;
    }

    @Override
    public int find(int a) {
        return arr[a];
    }
}

