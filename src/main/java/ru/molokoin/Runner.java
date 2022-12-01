package ru.molokoin;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Runner implements UserFace{
    public static void main(String[] args) {
        System.out.println("Запущен поток приложения ...");
        
        //получаем данные из файла, для анализа
        String source = "storage\\j120-lab2_пример файла для обработки.txt";
        File fSource = new File(source);
        String text = Storage.get(fSource);
        
        //создаем новый словарь
        Dictionary abc = new Dictionary(text);

        //инициируем заполнение нового словаря
        abc.build();

        //пишем словарь "как есть" в файл
        String contentAsIs = Dictionary.toString(abc.getWords());
        File fReportAsIs = new File("storage\\fReportAsIs.txt");
        Storage.put(contentAsIs, fReportAsIs);

        /**
         * Пишем словарь в алфавитном порядке
         * HashMap сам по себе не сортируется, соответственно есть несколько вариантов изменения последовательности списка,
         * все варианты связаны с преобразованием HashMap в другой тип:
         * для упорядочивания по возрастанию проце всего TreeMap
         * для остальных типов сортировки это будут другие варианты.
         * 
         * если общий вариант,
         * то получаем список Set элементов, по которым надо отсортировать перечень,
         * сортируем его,
         * и затем по ключу или по значению подставляем в выводе второй компонент
         */
        File fReportAlphabet = new File("storage\\fReportAlphabet.txt");
        String contentAlphabet = "";
        TreeMap<String, Integer> tm = Dictionary.getAlphabet(abc.getWords());
        for (Entry<String, Integer> entry : tm.entrySet()) {
            contentAlphabet = contentAlphabet + entry.getKey() + " : " + entry.getValue() + "\n";
        }
        Storage.put(contentAlphabet, fReportAlphabet);

        // Пишем словарь в обратном порядке (сортировка по последней букве)
        File fReportAlphabetReverse = new File("storage\\fReportAlphabetReverse.txt");
        String contentAlphabetReverse = "";
        tm = Dictionary.getAlphabetReverse(abc.getWords());
        for (Entry<String, Integer> entry : tm.entrySet()) {
            contentAlphabetReverse = contentAlphabetReverse + entry.getKey() + " : " + entry.getValue() + "\n";
        }
        Storage.put(contentAlphabetReverse, fReportAlphabetReverse);

        //пишем слова отсортированные в соответствии с частотой появления по тексту
        /**
         * отсортировали по частоте появления, но нет сортировки по алфавиту среди слов с одинаковой частотой появления!
         */
        File fReportAlphabetFrequency = new File("storage\\fReportAlphabetFrequency.txt");
        String contentAlphabetFrequency = "";
        Map<String, Integer> m = Dictionary.getAlphabetFrequency(abc.getWords());
        for (Entry<String, Integer> entry : m.entrySet()) {
            contentAlphabetFrequency = contentAlphabetFrequency + entry.getKey() + " : " + entry.getValue() + "\n";
        }
        Storage.put(contentAlphabetFrequency, fReportAlphabetFrequency);
    }
    
}
