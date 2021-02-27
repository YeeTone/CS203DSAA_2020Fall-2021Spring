import java.util.*;

public class RandomPassword {
    private static final ArrayList<Character>allChars=new ArrayList<>();
    private static final Character[]specialChars={'#','!','@','$','%'};
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int length=sc.nextInt();

        allChars.addAll(Arrays.asList(specialChars));
        for (int i = 0; i < 10; i++) {
            allChars.add((char)('0'+i));
        }
        for (int i = 0; i < 26; i++) {
            allChars.add((char)('a'+i));
            allChars.add((char)('A'+i));
        }
        Collections.shuffle(allChars);

        for (int i = 0; i < length; i++) {
            System.out.print(allChars.get(i));
        }

    }
}