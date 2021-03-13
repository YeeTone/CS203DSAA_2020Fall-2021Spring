package JCoderAC.Lab4;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L403 {
    private static final FastReader fastReader;
    private static final FastWriter fastWriter;
    static {
        fastReader=new FastReader(System.in);
        fastWriter=new FastWriter(System.out);
    }

    public static void main(String[] args) {
        int n= fastReader.nextInt();
        int m= fastReader.nextInt();
        int k= fastReader.nextInt()-1;

        char[]bracketStr=fastReader.next().toCharArray();
        char[]instructions=fastReader.next().toCharArray();

        BracketNode[]brackets=new BracketNode[n];
        for (int i = 0; i < n; i++) {
            brackets[i]=new BracketNode(i,bracketStr[i]);
        }

        for (int i = 0; i < brackets.length; i++) {
            if(i==0){
                brackets[i].nextAvailable=brackets[i+1];
            }else if(i== brackets.length-1){
                brackets[i].preAvailable=brackets[i-1];
            }else {
                brackets[i].preAvailable=brackets[i-1];
                brackets[i].nextAvailable=brackets[i+1];
            }
        }

        int availableStart=0,availableEnd=n-1;
        int firstAvailableIndex=0;

        boolean isEmpty=false;
        for (int i = 0; i < m; i++) {
            if(isEmpty){
                return;
            }
            switch (instructions[i]){
                case 'L':{
                    if(k-1>=availableStart&&brackets[k].preAvailable!=null){
                        k=brackets[k].preAvailable.index;
                    }
                    break;
                }
                case 'R':{
                    if(k+1<=availableEnd&&brackets[k].nextAvailable!=null){
                        k=brackets[k].nextAvailable.index;
                    }
                    break;
                }
                case 'D': {
                    int leftCount = 0;
                    int rightCount = 0;
                    BracketNode node = brackets[k];

                    do {
                        if (node.value == '(' && !node.removed) {
                            leftCount++;
                        } else if (node.value == ')' && !node.removed) {
                            rightCount++;
                        }
                        if (leftCount == rightCount) {
                            break;
                        } else {
                            if(brackets[k].value=='('){
                                node = node.nextAvailable;
                            }else {
                                node=node.preAvailable;
                            }

                        }

                    } while (true);

                    BracketNode startNode;
                    BracketNode endNode;
                    if(brackets[k].value=='('){
                        startNode=brackets[k];
                        endNode=node;
                    }else {
                        startNode=node;
                        endNode=brackets[k];
                    }

                    if (startNode.preAvailable == null) {
                        if (endNode.nextAvailable == null) {
                            isEmpty = true;
                            k = 0;
                        } else {
                            k = endNode.nextAvailable.index;
                            firstAvailableIndex=k;
                            endNode.nextAvailable.preAvailable = null;
                            endNode.nextAvailable = null;
                        }
                    } else {
                        if (endNode.nextAvailable == null) {
                            k = startNode.preAvailable.index;
                            startNode.preAvailable.nextAvailable = null;
                            startNode.preAvailable = null;
                        } else {
                            BracketNode startPre = startNode.preAvailable;
                            BracketNode endNext = endNode.nextAvailable;

                            startPre.nextAvailable = endNext;
                            endNext.preAvailable = startPre;
                            k = endNext.index;
                        }
                    }
                    break;
                }
            }
        }

        BracketNode temp=brackets[firstAvailableIndex];
        while (temp!=null){
            fastWriter.print(temp.value);
            temp=temp.nextAvailable;
        }

        fastReader.close();
        fastWriter.close();

    }
    private static class BracketNode{
        int index;
        char value;
        boolean removed=false;
        BracketNode preAvailable=null;
        BracketNode nextAvailable=null;

        public BracketNode(int index,char value){
            this.index=index;
            this.value=value;
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
