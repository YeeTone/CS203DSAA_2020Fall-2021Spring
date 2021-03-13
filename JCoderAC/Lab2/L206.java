package JCoderAC.Lab2;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L206 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);

        int n= fastReader.nextInt();
        long m= fastReader.nextLong();
        long k= fastReader.nextLong();
        long p= fastReader.nextLong();


        long[]wis=new long[n];
        long[]ais=new long[n];

        long max=-1,aisMax=-1;

        for (int i = 0; i < n; i++) {
            wis[i]= fastReader.nextLong();
            ais[i]= fastReader.nextLong();
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
            fastWriter.println(aisMax);
        }else {
            fastWriter.println(ans);
        }

        fastReader.close();
        fastWriter.close();
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

    private static class FastReader implements Closeable{
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in), 16384);
            eat("");
        }

        private void eat(String s) {
            st = new StringTokenizer(s);
        }

        public String nextLine() {
            try {
                return br.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while(!st.hasMoreTokens()) {
                String s = nextLine();
                if(s==null) return false;
                eat(s);
            }
            return true;
        }

        public String next() {
            hasNext();
            return st.nextToken();
        }

        public boolean nextBoolean(){
            return Boolean.parseBoolean(next());
        }


        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public float nextFloat(){
            return Float.parseFloat(next());
        }

        public double nextDouble(){
            return Double.parseDouble(next());
        }

        public BigInteger nextBigInteger(){
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecimal(){
            return new BigDecimal(next());
        }

        public void close(){
            try{
                st=null;
                br.close();
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }

        }
    }

    private static class FastWriter implements Closeable{
        private final PrintWriter writer;

        public FastWriter(OutputStream out){
            this.writer=new PrintWriter(out);
        }

        public void print(Object object){
            writer.write(object.toString());
        }

        public void printf(String format,Object... os){
            writer.write(String.format(format,os));
        }

        public void println(){
            writer.write(System.lineSeparator());
        }

        public void println(Object object){
            writer.write(object.toString());
            writer.write(System.lineSeparator());
        }

        @Override
        public void close() {
            writer.flush();
            writer.close();
        }
    }
}
