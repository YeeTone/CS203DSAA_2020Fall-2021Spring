package SUSTechACM;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1352 {

    public static void main(String[] args) {
        FastReader fastReader=new FastReader(System.in);
        FastWriter fastWriter=new FastWriter(System.out);
        int t= fastReader.nextInt();
        for (int i = 0; i < t; i++) {

            int n= fastReader.nextInt();
            PolyItemNode polyHead1=new PolyItemNode();
            PolyItemNode poly1=polyHead1;
            for (int j = 0; j < n; j++) {
                int coef= fastReader.nextInt();
                int expo= fastReader.nextInt();

                if(coef==0){
                    continue;
                }

                poly1.coef=coef;
                poly1.expo=expo;
                if(j!=n-1){
                    poly1.next=new PolyItemNode();
                    poly1=poly1.next;
                }
            }
            //System.out.println(poly1.getLinkedListString());

            int m= fastReader.nextInt();
            PolyItemNode polyHead2=new PolyItemNode();
            PolyItemNode poly2=polyHead2;
            for (int j = 0; j < m; j++) {
                int coef= fastReader.nextInt();
                int expo= fastReader.nextInt();
                if(coef==0){
                    continue;
                }

                poly2.coef=coef;
                poly2.expo=expo;
                if(j!=m-1){
                    poly2.next=new PolyItemNode();
                    poly2=poly2.next;
                }
            }
            //System.out.println(poly2.getLinkedListString());

            PolyItemNode result=getPolySum(polyHead1,polyHead2);
            fastWriter.println(result.getLinkedListString());
        }

        fastReader.close();
        fastWriter.close();
    }
    private static PolyItemNode getPolySum(PolyItemNode polyHead1,PolyItemNode polyHead2){
        PolyItemNode resultHead=new PolyItemNode();
        PolyItemNode result=resultHead;

        boolean poly1Used=false,poly2Used=false;
        while (!(poly1Used&&poly2Used)){
            poly1Used=(polyHead1==null);
            poly2Used=(polyHead2==null);

            if(!poly1Used){
                if(!poly2Used){
                    int expo1=polyHead1.expo;
                    int expo2=polyHead2.expo;
                    int coef1=polyHead1.coef;
                    int coef2=polyHead2.coef;

                    if(expo1>expo2){
                        if(coef2==0){
                            polyHead2=polyHead2.next;
                            continue;
                        }

                        result.expo= expo2;
                        result.coef=coef2;
                        result.next=new PolyItemNode();
                        result=result.next;

                        polyHead2=polyHead2.next;
                    }else if(expo1==expo2){
                        if(coef1+coef2==0){
                            polyHead1=polyHead1.next;
                            polyHead2=polyHead2.next;
                            continue;
                        }

                        result.coef=coef1+coef2;
                        result.expo=expo1;
                        result.next=new PolyItemNode();
                        result=result.next;

                        polyHead1=polyHead1.next;
                        polyHead2=polyHead2.next;
                    }else {
                        if(coef1==0){
                            polyHead1=polyHead1.next;
                            continue;
                        }
                        result.expo=expo1;
                        result.coef=coef1;
                        result.next=new PolyItemNode();
                        result=result.next;

                        polyHead1=polyHead1.next;
                    }
                }
                else {
                    int coef1=polyHead1.coef;
                    int expo1=polyHead1.expo;

                    if(coef1==0){
                        polyHead1=polyHead1.next;
                        continue;
                    }

                    result.expo=expo1;
                    result.coef=coef1;
                    result.next=new PolyItemNode();
                    result=result.next;

                    polyHead1=polyHead1.next;
                }
            }else {
                if(!poly2Used){

                    int expo2=polyHead2.expo;
                    int coef2=polyHead2.coef;
                    if(coef2==0){
                        polyHead2=polyHead2.next;
                        continue;
                    }
                    result.expo= expo2;
                    result.coef=coef2;
                    result.next=new PolyItemNode();
                    result=result.next;

                    polyHead2=polyHead2.next;
                }
            }
            poly1Used=(polyHead1==null);
            poly2Used=(polyHead2==null);
        }
        return resultHead;
    }

    private static class PolyItemNode{
        int expo;
        int coef;
        PolyItemNode next;

        String getLinkedListString(){
            int pre_expo=0;
            PolyItemNode temp=this;
            StringBuilder builder=new StringBuilder();
            boolean appended=false;
            boolean firstNegative=false;
            int count=0;
            while (temp!=null){
                if(temp.expo<pre_expo){
                    break;
                }
                count+=1;
                if(count==1){
                    //System.out.println("temp.coef = " + temp.coef);
                    firstNegative=(temp.coef<0);
                }
                pre_expo= temp.expo;
                if(temp.expo==0){
                    if(temp.coef>0){
                        appended=true;
                        builder.append('+').append(temp.coef);
                    }else if(temp.coef<0){
                        appended=true;
                        builder.append(temp.coef);
                    }
                }else {
                    if(temp.coef>0){
                        appended=true;
                        builder.append('+');
                        if(temp.coef!=1){
                            builder.append(temp.coef);
                        }
                        builder.append('x');
                        if(temp.expo!=1){
                            builder.append('^').append(temp.expo);
                        }
                    }else if(temp.coef<0){
                        appended=true;
                        builder.append('-');
                        if(temp.coef!=-1){
                            builder.append(-temp.coef);
                        }
                        builder.append('x');
                        if(temp.expo!=1){
                            builder.append('^').append(temp.expo);
                        }
                    }
                }
                temp=temp.next;
            }

            if(!appended){
                return "0";
            }else {
                if(firstNegative){
                    return builder.toString();
                }else {
                    return builder.substring(1);
                }
            }
        }
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
