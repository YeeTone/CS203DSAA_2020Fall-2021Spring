package CS203;

import java.io.*;
import java.util.*;

public class L206 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);

        int n= reader.nextInt();
        long m= reader.nextLong();
        long k= reader.nextLong();
        long p= reader.nextLong();


        long[]wis=new long[n];
        long[]ais=new long[n];

        long max=-1,aisMax=-1;

        for (int i = 0; i < n; i++) {
            wis[i]= reader.nextLong();
            ais[i]= reader.nextLong();
            long result = wis[i] + ais[i] * m;
            if(max< result){
                max=result;
            }

            if(aisMax<ais[i]){
                aisMax=ais[i];
            }
        }

        long low=0,high=Long.MAX_VALUE;
        long ans=0;
        while (low<=high) {
            long mid=low+(high-low)/2;
            if(check(n,m,k,p,wis,ais,mid)){
                ans=mid;
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        if(ans==0){
            System.out.println(aisMax);
        }else {
            System.out.println(ans);
        }
    }
    private static boolean check(int n,long m,long k,long p,long[]wis,long[]ais,long mid){
        long can_eat=k*m;
        long need;
        for (int i = 0; i < n; i++) {
            if(wis[i]+ais[i]*(m)<= mid){
                continue;
            }
            need=(long)Math.ceil((wis[i]+ais[i]*(m)- mid)*1.0/p);
            can_eat-=need;
        }
        return can_eat>=0;
    }

    private static class FastScanner {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        public void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public void hasNext() {
            while (!st.hasMoreTokens()) {
                String s = nextLine();
                if (s == null) return;
                eat(s);
            }
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }
}
