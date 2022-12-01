package ru.molokoin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

/**
 * Класс формирует словарь из полученного от пользователя текста<p>
 * Использование:
 * Создаем объект Dictionary
 * Передаем в конструктор массив строк.
 * Вызываем метод build() для объекта типа Dictionary
 * Методом getWords() получаем список содержащихся в тексте слов и количество повторений
 * Пример:
 * HashMap<String, Integer> w
 * Dictionary d = new Dictionary(String[] s).build();
 * w = d.getWords();
 */
public class Dictionary {
    /**
     * Массив строк text содержит исходный текст,<p>
     * прочитанный из файла переданного пользователем
     */
    private String text;

    /**
     * Список words хранит в качестве ключа слова, а в
     * качестве значения - количество повторений слова в тексте.
     * Слова в списке размещены в соответствии с очередностью появления в тексте
     */
    private HashMap<String, Integer> words;

    /**
     * Основной конструктор словаря.
     * @param text
     */
    public Dictionary(String text){
        this.words = new HashMap<String, Integer>();
        setText(text);
    }
    
    /**
     * метод осуществляет построение списка слов (words)<p>
     * на основании содержимого text<p>
     * - обработка текста<p>
     * - передача результатов обработки в words<p>
     * 
     * Надо доделать:
     * - разбиение строки на слова по переходам строки
     * - разбиение строки на слова по знакам препинания (на случай, если пробел пропущен)
     * @return
     */
    public Dictionary build(){
        //приводим текст к нижнему регистру
        text = text.toLowerCase();
        /**
         * Удаляем знаки пунктуации.<p>
         * Оставляем только буквы, пробелы и дефисы.<p>
         * и знаки тире остались поидее, попадут как самостоятельные слова в словарь
         */
        text = text.replaceAll("[^а-яa-z -]+", "");
        //разбиваем текст на слова - по пробелам
        String[] words = text.split("[\\s]+");
        System.out.println("Количество слов в тексте: " + words.length);
        //пишем слова в список
        for (String word : words) {
            if (this.words.containsKey(word)){
                Integer repeats = this.words.get(word);
                this.words.put(word, ++repeats);
            }else{
                this.words.put(word, 1);
            }
        }
        return this;
    }
    /**
     * Статический метод, сортирует словарь по алфавиту
     * создаем новый список,
     * пишем в него компоненты в алфавитной последовательности:
     * - находим первое слово, для добавления в список
     * - устанавливаем значение current
     * - пишем current в новый список
     * - удаляем поле из исходного списка (чтобы в следующий раз меноше перебирать)
     * - сравниваем поэлементно current с оставшимися словами исходного списка, находим следующее .. 
     * 
     * - возвращаем сформированный список в качестве результата работы метода. 
     * 
     * @param words
     * @return
     */
    public static TreeMap<String, Integer> getAlphabet(HashMap<String, Integer> words){
        TreeMap<String, Integer> sortedTree = new TreeMap<>();
        sortedTree.putAll(words);
        return sortedTree;
    }
    /**
     * Статический метод, сортирует словарь попринципу обратного словаря<p>
     * — словаря, в котором слова отсортированы не по начальным, а по конечным буквам (перевернуты).<p>
     * В обратном словаре сначала перечисляются слова, заканчивающиеся на «а», потом на «б» и так далее
     * @param words
     * @return
     */
    public static TreeMap<String, Integer> getAlphabetReverse(HashMap<String, Integer> words){
        HashMap<String, Integer> reverceWords = new HashMap<>();
        String s;
        Integer i;
        for (Entry<String, Integer> entry : words.entrySet()) {
            s = (String)entry.getKey();
            i = (Integer)entry.getValue();
            reverceWords.put(Word.reverce(s), i);
        }
        return new TreeMap<String, Integer>(reverceWords);
    }

    /**
     * Статический метод, возвращает словарь, отсортированный по убыванию частоты появления в тексте;
     * при совпадении частот слова упорядочиваются по алфавиту
     * @param words
     * @return
     */
    public static Map<String, Integer> getAlphabetFrequency(HashMap<String, Integer> words){
        LinkedHashMap<String, Integer> sorted = new LinkedHashMap<String, Integer>();
        /**
         * Перечень уникальных значений частоты повторения слова в тексте
         * Причем HashSet их автоматически отсортировал по возрастанию
         */
        HashSet<Integer> values = new HashSet<>(words.values());
        for (Integer value : values) {
            //создаем буфер, который будет содержать только слова с одинаковой частотой повторения
            Map<String, Integer> buf = new HashMap<String, Integer>();
            String currentKey;
            Integer currentValue;
            for (Entry<String, Integer> entry : words.entrySet()) {
                currentKey = (String)entry.getKey();
                currentValue = (Integer)entry.getValue();
                if (currentValue == value){
                    buf.put(currentKey, currentValue);
                }
            }
            //сортируем буфер по ключу
            TreeMap<String, Integer> tree = new TreeMap<>(buf);
            /**
             * Пишем буфер в LinkedHashMap, он никак не перемещает поля в списке,
             * в какой последовательности пишем, так в последствии и отдает
             */
            for (Entry<String, Integer> entry : tree.entrySet()) {
                currentKey = (String)entry.getKey();
                currentValue = (Integer)entry.getValue();
                if (currentValue == value){
                    sorted.put(currentKey, currentValue);
                }
            }
        }
        return sorted;
    }
    // /**
    //  * сортировка списка по значениям
    //  * на самом деле не понятно как это происходит, но работает. (не пригодилось в итоге)
    //  * @param words
    //  * @return
    //  */
    // public static Map<String, Integer> sortMap(HashMap<String, Integer> map){
    //     Map<String, Integer> sorted = new HashMap<String, Integer>(map);
    //     sorted = sorted.entrySet()
    //                 .stream()
    //                 .sorted(Map.Entry.comparingByValue())
    //                 .collect(Collectors.toMap(
    //                                     Map.Entry::getKey,
    //                                     Map.Entry::getValue,
    //                                     (oldValue, newValue) -> oldValue,
    //                                     LinkedHashMap::new
    //                 ));
    //     return sorted;
    // }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
    /**
     * @return the text
     */
    public String getText() {
        return text;
    }
    /**
     * @param words the words to set
     */
    public void setWords(HashMap<String, Integer> words) {
        this.words = words;
    }
    /**
     * @return the words
     */
    public HashMap<String, Integer> getWords() {
        return words;
    }

    /**
     * преобразовываем HashMap в строку для записи в файл
     * @param sourceMap
     * @return
     */
    public static String toString(HashMap<String, Integer> sourceMap){
        String content = "";//результирующая строка для записи в файл
        for (Map.Entry<String, Integer> entry : sourceMap.entrySet()) {
            content = content + entry.getKey() + " : " + entry.getValue() + "\n";
        }
        return content;
    }
}
