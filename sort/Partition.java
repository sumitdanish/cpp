import java.util.*;

public class Partition {
    public static void swap (int[] a, int i, int j) {
        if (i==j) return;
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static int partitionBasic(int[] a, int l, int r) {
        int pivot = a[r];
        int i, j;
        for (i=j=l; i<r; i++) {
            if (a[i]<pivot) {
                swap(a, i, j);
                j++;
            }
        }
        swap(a, j, r);
        return j;
    }

    public static int partition(int[] a, int l, int r) {
        if (l>=r) return l;

        int pivot = a[r];
        int i, j;
        i = l;
        j = r-1;
        while (true) {
            while (a[i]<pivot && i<j) i++;
            while (a[j]>pivot && i<j) j--;
            if (i>=j) break;
            swap(a, i, j);
        }
        swap(a, i, r);
        return i;
    }

    /*
     * http://www.sorting-algorithms.com/static/QuicksortIsOptimal.pdf
     */
    public static void partitionThreeWay(int[] a, int l, int r) {
        int p = a[r];
        int i, j, m, n, u, v;
        m = i = 0;
        n = j = r-1;
        while (true) {
            while (a[i]<p && i<j) i++;
            while (a[j]>p && i<j) j--;
            if (i>=j) break;
            if (a[i] == p) {swap(a, i, m); m++;} // move equal to left
            if (a[j] == p) {swap(a, j, n); n--;} // move equal to right
        }
        swap(a, i, r);
        u = l-1;
        v = j+1;
        // now those euqal to p are on left and right side of array
        for (int k=l; k<m; k++,u--) swap(a, k, u);
        for (int k=r; k>n; k--,v++) swap(a, k, v);
    }

    public static void merge(int[] a, int l, int m, int r) {
        int[] aux = new int[r-l+1];
        int i, j, k;
        for (i=l,j=m+1,k=0; i<=m && j<=r; ) {
            aux[k++] = (a[i]<a[j])? a[i++] : a[j++];
        }
        while (i<=m) aux[k++] = a[i++];
        while (j<=r) aux[k++] = a[j++];
        for (i=l, k=0; i<=r; i++, k++) a[i] = aux[k];
    }

    public static void mergeB(int[] a, int l, int m, int r) { 
        int i,j,k;
        int[] aux = new int[100];
        for (i=l, k=0; i<m; i++, k++) aux[k] = a[i];
        for (j=m, k=r; j<=r; j++, k--) aux[k] = a[j];
        for (i=k=l,j=r; i<j; ) {
            a[k++] = (aux[i]<aux[j])? aux[i++] : aux[j--];
        }
    }

    public static void testPartition() {
        int[] a = {1,2,1,3,6,4,5,7,3};
        int len = a.length;
        partitionBasic(a, 0, len-1);
        partition(a, 0, len-1);
        partitionThreeWay(a, 0, len-1);
        System.out.println(Arrays.toString(a));
    }

    public static void main(String[] args) {
        int[] a = {1,2,6,3,4,5};
        int[] b = Arrays.copyOf(a, a.length);
        merge(a, 0, 3, 5);
        mergeB(b, 0, 3, 5);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
}
