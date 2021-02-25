package JCoderAC;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class L104 {
    public static void main(String[] args) {
        FastReader fastReader =new FastReader(System.in);
        FastWriter fastWriter =new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {
            int n= fastReader.nextInt();
            int k= fastReader.nextInt();
            char[] flowers= fastReader.next().toCharArray();

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
            fastWriter.println(available);
        }
        fastReader.close();
        fastWriter.close();
    }
    private static int gcd(int k,int av){
        while (av!=0){
            int temp=k%av;
            k=av;
            av=temp;
        }
        return k;
    }
    private static class FastReader implements Closeable {
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
