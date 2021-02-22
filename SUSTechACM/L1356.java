package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1356 {
    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int n= fastReader.nextInt();

        String[]strings=new String[n];
        for (int i = 0; i < n; i++) {
            strings[i]= fastReader.next();
        }

        int q= fastReader.nextInt();
        int correct=0;

        for (int k = 0; k < q; k++) {
            int i= fastReader.nextInt();
            int j= fastReader.nextInt();
            char result=fastReader.next().charAt(0);

            long fij=get_f_i_j(i,j,strings);
            long fji=get_f_i_j(j,i,strings);

            //System.out.println("fij = " + fij);
            //System.out.println("fji = " + fji);

            if(fij==fji&&result=='='){
                correct++;
            }else if(fij<fji&&result=='<'){
                correct++;
            }else if(fij>fji&&result=='>'){
                correct++;
            }
        }

        fastWriter.println(correct);

        fastReader.close();
        fastWriter.close();
    }
    private static long get_f_i_j(int i,int j,String[]strings){
        String si=strings[i-1];
        String sj=strings[j-1];

        char[]all= (si + '#' + sj).toCharArray();

        int m= all.length;
        int[]next=new int[m];
        next[0]=0;
        int k=0,j1=1;
        while (j1<m){
            if(all[j1]==all[k]){
                k++;
                next[j1]=k;
                j1++;
            }else if(k==0){
                next[j1]=0;
                j1++;
            }else {
                k=next[k-1];
            }
        }
        return next[m-1];
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
