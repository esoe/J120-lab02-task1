package ru.molokoin;

import java.io.File;

public class Runner implements UserFace{
    public static void main(String[] args) {
        System.out.println("Запущен поток приложения ...");
        
        //получаем данные из файла, для анализа
        String source = "storage\\j120-lab2_пример файла для обработки.txt";
        File f = new File(source);
        String text = Storage.get(f);
        System.out.println(text);

        //создаем новый словарь
        Dictionary abc = new Dictionary(source);
        abc.build();
        
    }
    
}
