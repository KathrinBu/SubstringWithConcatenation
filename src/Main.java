
/*
Вам дана строка s и массив строк words. Все строки words имеют одинаковую длину.
Объединенная подстрока — это s подстрока, содержащая все строки любой перестановки words объединенных.
Например, если words = ["ab","cd","ef"], то "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", и "efcdab"
все являются объединенными строками.
"acdbef" не является объединенной подстрокой, поскольку не является объединением какой-либо
перестановки words.
Выведите начальные индексы всех объединенных подстрок в s. Вы можете вернуть ответ в любом порядке.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        List<Integer> indixes = findIndexes(words, s);

        System.out.println("Начальные индексы полностью объединенных подстрок:");
        for (int index : indixes) {
            System.out.println(index);
        }
        
    }

    public static List<Integer> findIndexes(String[] words, String s){
        List<Integer> indexes = new ArrayList<>();
        int elementLength = words[0].length();
        int massLength = words.length;
        int windowSize = massLength * elementLength;

        Map<String, Integer> wordCountMap = new HashMap<>();
        for (String string:words) {
            wordCountMap.put(string, wordCountMap.getOrDefault(string,0)+1);
        }
        for (int i = 0; i <=(s.length() - windowSize); i++) {
            Map<String, Integer> seenMap = new HashMap<>();
            int count = 0;
            int j=0;
            while (j < massLength){
                String word = s.substring(i + j * elementLength, i + (j + 1) * elementLength);
                if (!wordCountMap.containsKey(word)) {
                    break;
                }
                seenMap.put(word, seenMap.getOrDefault(word, 0) + 1);
                if (seenMap.get(word) > wordCountMap.getOrDefault(word, 0)) {
                    break;
                }
                count++;
                j++;
            }
            if (count == massLength) {
                indexes.add(i);
            }
        }

        return indexes;
    }


}

