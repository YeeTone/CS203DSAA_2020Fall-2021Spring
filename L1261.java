package SUSTechACM;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class L1261 {
    public static void main(String[] args) {
        FastScanner fastScanner = new FastScanner(System.in);
        int t = fastScanner.nextInt();

        for (int i = 0; i < t; i++) {

            int n = fastScanner.nextInt();
            PolyItemNode polyHead1 = new PolyItemNode();
            PolyItemNode temp1 = polyHead1;
            for (int j = 0; j < n; j++) {
                long coef=fastScanner.nextLong();
                int expo= fastScanner.nextInt();
                if(coef==0){
                    continue;
                }

                temp1.coef = coef;
                temp1.expo = expo;
                if (j != n - 1) {
                    temp1.next = new PolyItemNode();
                    temp1 = temp1.next;
                }
            }
            //polyHead1.printLinkedList();

            int m = fastScanner.nextInt();
            PolyItemNode polyHead2 = new PolyItemNode();
            PolyItemNode temp2 = polyHead2;
            for (int j = 0; j < m; j++) {
                long coef=fastScanner.nextLong();
                int expo= fastScanner.nextInt();
                if(coef==0){
                    continue;
                }

                temp2.coef = coef;
                temp2.expo = expo;
                if (j != m - 1) {
                    temp2.next = new PolyItemNode();
                    temp2 = temp2.next;
                }
            }

            PolyItemNode result=getSum(polyHead1,polyHead2);
            //result.printLinkedList();

            int q= fastScanner.nextInt();
            for (int j = 0; j < q; j++) {
                int expo= fastScanner.nextInt();
                System.out.print((long) getCoef(result,expo));
                if(j!=q-1){
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    private static double getCoef(PolyItemNode resultPoly,int expo){
        PolyItemNode temp=resultPoly;
        while (temp!=null){
            if(temp.expo==expo){
                return temp.coef;
            }
            temp=temp.next;
        }
        return 0;
    }


    private static PolyItemNode getSum(PolyItemNode polyHead1,PolyItemNode polyHead2){
        PolyItemNode resultHead=new PolyItemNode();
        PolyItemNode result=resultHead;

        PolyItemNode temp1=polyHead1;
        PolyItemNode temp2=polyHead2;

        boolean poly1Used=(temp1==null);
        boolean poly2Used=(temp2==null);

        int expo1=0,expo2=0;
        int resultExpo=0;
        long resultCoef=0;
        while (!(poly1Used&&poly2Used)){
            //resultHead.printLinkedList();

            poly1Used=(temp1==null);
            poly2Used=(temp2==null);
            /*System.out.println("poly1Used = " + poly1Used);
            System.out.println("poly2Used = " + poly2Used);
            System.out.println("----------");*/
            if(!poly1Used){
                if(!poly2Used){
                    expo1= temp1.expo;
                    expo2= temp2.expo;
                    if(expo1<expo2){
                        resultExpo=expo1;
                        resultCoef=temp1.coef;

                        temp1=temp1.next;

                        result.expo=resultExpo;
                        result.coef=resultCoef;
                        result.next=new PolyItemNode();
                        result=result.next;
                    } else if(expo1==expo2){
                        resultExpo=expo1;
                        resultCoef=temp1.coef+ temp2.coef;

                        temp1=temp1.next;
                        temp2=temp2.next;

                        if(resultCoef==0){
                            continue;
                        }

                        result.expo=resultExpo;
                        result.coef=resultCoef;
                        result.next=new PolyItemNode();
                        result=result.next;
                    } else {

                        resultExpo=expo2;
                        resultCoef=temp2.coef;

                        temp2=temp2.next;

                        result.expo=resultExpo;
                        result.coef=resultCoef;
                        result.next=new PolyItemNode();
                        result=result.next;
                    }
                }
                else {
                    expo1=temp1.expo;
                    /*System.out.println("This!");*/
                    resultExpo=expo1;
                    resultCoef=temp1.coef;

                    temp1=temp1.next;

                    result.expo=resultExpo;
                    result.coef=resultCoef;
                    result.next=new PolyItemNode();
                    result=result.next;
                }
            }else {
                if(!poly2Used){
                    expo2= temp2.expo;

                    resultExpo=expo2;
                    resultCoef=temp2.coef;

                    temp2=temp2.next;

                    result.expo=resultExpo;
                    result.coef=resultCoef;
                    result.next=new PolyItemNode();
                    result=result.next;
                }
            }
        }

        return resultHead;
    }

    private static class PolyItemNode{
        long coef=0;
        int expo=0;
        PolyItemNode next=null;

        public void printLinkedList(){
            PolyItemNode temp=this;
            int pre_expo=this.expo;
            while(temp!=null){
                if(pre_expo> temp.expo){
                    break;
                }
                pre_expo= temp.expo;
                System.out.println(temp);
                temp=temp.next;
            }
        }
        @Override
        public String toString() {
            return "PolyItemNode{" +
                    "coef=" + coef +
                    ", expo=" + expo +
                    '}';
        }
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
            }

        }
    }
}
