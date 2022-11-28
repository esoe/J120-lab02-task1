package ru.molokoin;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Storage {
    /**
     * Метод возвращает в виде строки текст файла
     * @param sourceFile
     * @return
     */
    public static String get(File sourceFile){
        String line = "";
        try(FileReader reader = new FileReader(sourceFile)){
            char[]buf = new char[5];
            int c =0;
            while((c=reader.read(buf))!=-1){
                line += new String(buf, 0, c);
            }
        }catch (IOException e) {
            System.out.println("Файл по указанному пути отсутствует.");
            System.out.println(e.getMessage());
        }
        return line;
    }
    /**
     * Метод пишет в файл содержимое строки
     */
    public static void put(String content, File targetFile){
        //
    }
}
