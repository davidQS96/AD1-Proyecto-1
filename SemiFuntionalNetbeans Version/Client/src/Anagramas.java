
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Anagramas {
    Map<String, Integer> dictionary = new HashMap<>();
    String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    int[] values = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};
    
    private void fillValues(){
        for(int i = 0; i < letters.length;i++){
            dictionary.put(letters[i], values[i]);
        }
    }
    
    private int valueInNumber(String word){
        int result = 1;
        for(int i = 0; i<word.length(); i++){
            for(int j = 0; j<letters.length; j++){
                String key = letters[j];
                String letter = String.valueOf(word.charAt(i));
                if(key.equals(letter)){
                    result = result * dictionary.get(key);
                }
        }
    }
        return result;
    
}
    public static void main(String[] args){
        while(true){
        Anagramas lol = new Anagramas();
        lol.fillValues();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite un palabra a analizar: ");
        String word1 = sc.next();
        System.out.println("Contra que palabra?: ");
        String word2 = sc.next();
        int resultado_1 = lol.valueInNumber(word1);
        int resultado_2 = lol.valueInNumber(word2);
        if(resultado_1 == resultado_2){
            System.out.println("Lo son");
        }else{
            System.out.println("No lo son");
        }
        }
    }
}
