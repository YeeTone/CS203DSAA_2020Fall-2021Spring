package JCoderAC;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class L103 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int k= fastReader.nextInt();
            long[]numbers=new long[n];
            long[]groups=new long[k];
            for (int j = 0; j < n; j++) {
                numbers[j]=Long.parseLong(fastReader.next()) ;
            }
            for (int j = 0; j < k; j++) {
                groups[j]= Long.parseLong(fastReader.next());
            }
            Arrays.sort(numbers);
            Arrays.sort(groups);
            //System.out.println(Arrays.toString(groups));

            //System.out.println(Arrays.toString(groups));
            int left=0;
            int right=numbers.length-1;

            long sum=0;
            for (long group : groups) {
                if (group == 1) {
                    sum += numbers[right] * 2;
                    right -= 1;
                } else {
                    break;
                }
            }
            for (int j = groups.length-1; j >= 0; j--) {
                if(groups[j]==1){
                    break;
                }else {
                    sum+=numbers[right];
                    right-=1;
                    sum+=numbers[left];
                    left+=groups[j]-1;
                }
            }
            fastWriter.println(sum);
        }

        fastReader.close();
        fastWriter.close();
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
