package ru.molokoin;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

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
        System.out.println("-----------------------------------------");
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
     * — словаря, в котором слова отсортированы не по начальным, а по конечным буквам.<p>
     * В обратном словаре сначала перечисляются слова, заканчивающиеся на «а», потом на «б» и так далее
     * @param words
     * @return
     */
    public static HashMap<String, Integer> getAlphabetReverse(HashMap<String, Integer> words){
        return null;
    }

    /**
     * Статический метод, возвращает словарь, отсортированный по убыванию частоты появления в тексте;
     * при совпадении частот слова упорядочиваются по алфавиту
     * @param words
     * @return
     */
    public static HashMap<String, Integer> getAlphabetFrequency(HashMap<String, Integer> words){
        return null;
    }

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
