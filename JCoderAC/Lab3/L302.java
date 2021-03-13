package JCoderAC.Lab3;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class L302 {
    public static void main(String[] args) throws IOException {
        /*FastReader slowReader=new FastReader(System.in);*/
        StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        FastWriter fastWriter=new FastWriter(System.out);

        in.nextToken();
        int n= (int)in.nval;
        in.nextToken();
        int k= (int)in.nval;

        int[]array=new int[n];
        for (int i = 0; i < n; i++) {
            in.nextToken();
            int value= (int)in.nval;
            array[i]=value;
        }

        int answer=getKth(array,k);
        fastWriter.println(answer);

        fastWriter.close();
    }
    /*private static int partition(int[]array,int s,int e){
        int tp=array[s];
        while (s<e){
            while (s<e&&array[e]>=tp){
                e--;
            }
            array[s]=array[e];
            while (s<e&&array[s]<=tp){
                s++;
            }
            array[e]=array[s];
        }
        array[s]=tp;
        return s;
    }*/
    private static int partition(int[]array,int s,int e) {
        int pivot = array[e];
        int left = s;
        for (int i = s; i < e; i++) {
            if (array[i] <= pivot) {
                if (i != left) {
                    int temp = array[i];
                    array[i] = array[left];
                    array[left] = temp;
                }
                left++;
            }
        }
        int temp = array[left];
        array[left] = array[e];
        array[e] = temp;
        return left;
    }

    private static int getKth(int[]array,int k){
        int s=0,e=array.length-1;
        int index=partition(array,s,e);
        while (index!=k-1){
            if(index>k-1){
                e=index-1;
            }else {
                s=index+1;
            }
            index=partition(array,s,e);
        }
        return array[index];
    }

    private static class FastReader implements Closeable {
        private final BufferedReader br;
        private StringTokenizer st;

        public FastReader(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
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
