package CS203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class L104 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        int t=reader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= reader.nextInt();
            int k= reader.nextInt();
            char[] flowers= reader.next().toCharArray();

            ArrayList<Character>flowerKinds=new ArrayList<>(n);
            ArrayList<Integer>flowerCounts=new ArrayList<>(n);
            for(char c:flowers){
                int index=flowerKinds.indexOf(c);
                if(index==-1){
                    flowerKinds.add(c);
                    flowerCounts.add(1);
                }else {
                    flowerCounts.set(index,flowerCounts.get(index)+1);
                }
            }

            Collections.sort(flowerCounts);

            int available=n;
            while (true){
                if(k%available==0){
                    break;
                }else {
                    int groups=gcd(k,available);

                    int perGroup=available/groups;
                    int[]groupsAvailable=new int[flowerCounts.size()];
                    int sum=0;
                    for (int j = 0; j < flowerCounts.size(); j++) {
                        groupsAvailable[j]=flowerCounts.get(j)/perGroup;
                        sum+=groupsAvailable[j];
                    }

                    if(sum>=groups){
                        break;
                    }else {
                        available--;
                    }
                }
            }
            System.out.println(available);
        }
    }
    private static int gcd(int k,int av){
        while (av!=0){
            int temp=k%av;
            k=av;
            av=temp;
        }
        return k;
    }
    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

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
