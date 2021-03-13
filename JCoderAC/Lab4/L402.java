package JCoderAC.Lab4;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L402 {
    private static final int INSERT_LEFT=0;
    private static final int INSERT_RIGHT=1;
    public static void main(String[] args) throws IOException {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);

        int n= fastReader.nextInt();
        PersonNode[]people=new PersonNode[n];

        for (int i = 0; i < n; i++) {
            people[i]=new PersonNode(i);
        }

        PersonNode first=people[0];
        for (int i = 1; i < n; i++) {
            int index= fastReader.nextInt()-1;
            PersonNode inserted=people[i];
            PersonNode curPersonK=people[index];
            int op= fastReader.nextInt();
            if(op==0){
                if(curPersonK.left==null){
                    inserted.right=curPersonK;
                    curPersonK.left=inserted;
                    first=inserted;
                }else {
                    PersonNode left=curPersonK.left;
                    left.right=inserted;
                    inserted.left=left;
                    curPersonK.left=inserted;
                    inserted.right=curPersonK;
                }
            }else {
                if(curPersonK.right==null){
                    inserted.left=curPersonK;
                    curPersonK.right=inserted;
                }else {
                    PersonNode right=curPersonK.right;
                    curPersonK.right=inserted;
                    inserted.left=curPersonK;
                    right.left=inserted;
                    inserted.right=right;
                }
            }
        }

        int m= fastReader.nextInt();
        for (int i = 0; i < m; i++) {
            int index= fastReader.nextInt()-1;
            people[index].removed=true;
        }

        PersonNode temp=first;
        while (temp!=null){
            if(temp.removed){
                temp=temp.right;
                continue;
            }
            fastWriter.print(temp.index+1);
            if(temp.right!=null){
                fastWriter.print(' ');
            }
            temp=temp.right;
        }

        fastReader.close();
        fastWriter.close();
    }


    private static class PersonNode {
        int index;
        PersonNode left=null;
        PersonNode right=null;
        boolean removed=false;

        public PersonNode(int number){
            this.index=number;
        }
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
