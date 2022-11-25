package ru.molokoin;

import java.util.HashMap;

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
    private String[] text;
    /**
     * Список words хранит в качестве ключа слова, а в
     * качестве значения - количество повторений слова в тексте.
     * Слова в списке размещены в соответствии с очередностью появления в тексте
     */
    private HashMap<String, Integer> words;
    public Dictionary(String[] text){
        setText(text);
    }
    
    /**
     * метод осуществляет построение списка слов (words)
     * @return
     */
    public Dictionary build(){
        //обработка текста
        //передача результатов обработки в words
        return this;
    }
    /**
     * Статический метод, сортирует словарь по алфавиту
     * @param words
     * @return
     */
    public static HashMap<String, Integer> alphabet(HashMap<String, Integer> words){
        return null;
    }
    /**
     * Статический метод, сортирует словарь попринципу обратного словаря<p>
     * — словаря, в котором слова отсортированы не по начальным, а по конечным буквам.<p>
     * В обратном словаре сначала перечисляются слова, заканчивающиеся на «а», потом на «б» и так далее
     * @param words
     * @return
     */
    public static HashMap<String, Integer> alphabetReverse(HashMap<String, Integer> words){
        return null;
    }

    /**
     * Статический метод, возвращает словарь, отсортированный по убыванию частоты появления в тексте;
     * при совпадении частот слова упорядочиваются по алфавиту
     * @param words
     * @return
     */
    public static HashMap<String, Integer> alphabetFrequency(HashMap<String, Integer> words){
        return null;
    }

    /**
     * @param text the text to set
     */
    public void setText(String[] text) {
        this.text = text;
    }
    /**
     * @return the text
     */
    public String[] getText() {
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
}