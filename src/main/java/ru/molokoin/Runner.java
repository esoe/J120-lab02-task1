package ru.molokoin;

import java.io.File;
import java.util.Map;

public class Runner implements UserFace{
    public static void main(String[] args) {
        System.out.println("Запущен поток приложения ...");
        
        //получаем данные из файла, для анализа
        String source = "storage\\j120-lab2_пример файла для обработки.txt";
        File f = new File(source);
        String text = Storage.get(f);
        System.out.println(text);

        //создаем новый словарь
        Dictionary abc = new Dictionary(text);
        //инициируем заполнение нового словаря
        abc.build();
        //пишем словарь  файл - пока консоль
        for (Map.Entry entry : abc.getWords().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue()); //надо в файл писать ..
        }
        System.out.println("Размер словаря: " + abc.getWords().size());

    }
    
}
