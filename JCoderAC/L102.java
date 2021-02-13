package CS203;

import java.io.*;
import java.util.StringTokenizer;

public class L102 {
    public static void main(String[] args) {
        FastScanner reader=new FastScanner(System.in);
        int T= reader.nextInt();
        for (int i = 0; i < T; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int c = reader.nextInt();
            char[][] map3D = new char[2*b + 2*c + 1][2*a + 2*b + 1];
            print3D(a, b, c, map3D);
        }
    }
    private static void print3D(int a,int b,int c,char[][]map){
        for (int i=0;i<2*b + 2*c + 1;i++){
            for (int j=0;j<2*a + 2*b + 1;j++){
                map[i][j]=' ';
            }
        }
        setDot(a,b,c,map);
        setAdd(a,b,c,map);
        setMinus(a,b,c,map);
        setVertical1(a,b,c,map);
        setUnVertical(a,b,c,map);
        setDot2(a,b,c,map);
        setVertical2(a,b,c,map);
        for (int i=0;i<2*b + 2*c + 1;i++){
            for (int j=0;j<2*a + 2*b + 1;j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
    private static void setDot(int a,int b,int c,char[][]map){
        //left corner:
        for (int i = 0; i < 2*b; i++) {
            for (int j = 0;j < 2*b-i; j++){
                map[i][j]='.';
            }
        }

        //right corner:
        for (int i = 2*b+2*c; i > 2*c; i--) {
            for (int j = 2*a+2*b; j > 2*a+(2*b+2*c-i); j--) {
                map[i][j]='.';
            }
        }
    }
    private static void setAdd(int a,int b,int c,char[][]map){
        for (int column = 0; column < 2*b+2*c+1; column+=2) {
            for (int row=2*b-column;;row+=2){
                if(row<0){
                    row=0;
                }
                try{
                    map[column][row]='+';
                    if(map[column][row+1]!=' '){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }
        }
    }
    private static void setMinus(int a,int b,int c,char[][]map){

        for (int i=0;i<2*b + 2*c + 1;i++){
            int count=0;
            for (int j=0;j<2*a+2*b+1 ;j++){
                try{
                    if(map[i][j]=='+'&&map[i][j+1]==' '&&count<a){
                        map[i][j+1]='-';
                        count++;
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }
        }
    }
    private static void setVertical1(int a,int b,int c,char[][]map){
        for (int i=2*b+1;i<2*b + 2*c + 1;i++){
            for (int j = 0; j <= 2*a+2*b; j+=2) {
                try{
                    if(map[i][j]==' '){
                        map[i][j]='|';
                    } else {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }

            }
        }
    }
    private static void setUnVertical(int a,int b,int c,char[][]map){
        for (int i=0;i<b;i++){
            for (int j=1;j<2*a + 2*b + 1;j+=2){
                try {
                    if(map[2*i+1][j]==' '){
                        map[2*i+1][j]='/';
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }
        }

        for (int i=2*b+2*c-1;i>=0;i-=2){
            for (int j = 2*a+2*b-1; j >= 2*a+1; j-=2) {
                try {
                    if(map[i][j]==' '){
                        map[i][j]='/';
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }
        }
    }
    private static void setDot2(int a,int b,int c,char[][]map){
        for (int i=0;i<2*b+2*c+1;i++){
            int count=0;
            for (int j=0;j<2*a+2*b+1-1;j++){
                try{
                    if(map[i][j-1]=='+'&&map[i][j]==' '&&map[i][j+1]=='+'){
                        map[i][j]='.';
                        continue;
                    }
                }catch (ArrayIndexOutOfBoundsException ignored){

                }
                if(i<2*b){
                    try{
                        if(map[i][j-1]=='/'&&map[i][j+1]=='/'&&map[i][j]==' '&&count<a){
                            count++;
                            map[i][j]='.';
                        }
                    }catch (ArrayIndexOutOfBoundsException ignored){

                    }
                }else {
                    try{
                        if(map[i][j-1]=='|'&&map[i][j+1]=='|'&&map[i][j]==' '&&count<a){
                            count++;
                            map[i][j]='.';
                        }
                    }catch (ArrayIndexOutOfBoundsException ignored){

                    }
                }
            }
        }
    }
    private static void setVertical2(int a,int b,int c,char[][]map){
        for (int i=0;i<2*b + 2*c + 1;i++){
            for (int j=0;j<2*a + 2*b + 1;j++){
                if(map[i][j]==' '){
                    map[i][j]='|';
                }
            }
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
    }
}
