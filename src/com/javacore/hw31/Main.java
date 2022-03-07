package com.javacore.hw31;

import java.io.*;
import java.util.Date;

public class Main {
    public static String createDirs(String parentDir, String[] arraySubDir) {
        StringBuilder log = new StringBuilder();
        Date date = new Date();
        for (String dirName:arraySubDir) {
            File newDir = new File(parentDir + "//" + dirName);
            if (newDir.mkdir()) log.append ("Директорий <" + dirName +  "> успешно создан. ["
                                            + date.toString() + "]\n");
            else log.append ("В процессе создания директория <" + dirName +  "> произошла ошибка. ["
                            + date.toString() + "]\n");
        }

        return log.toString();
    }

    public static String createFiles(String parentDir, String[] arrayFiles){
        StringBuilder log = new StringBuilder();
        Date date = new Date();
        for (String fileName:arrayFiles) {
            File newFile = new File(parentDir + "//" + fileName);
            try {
                if (newFile.createNewFile()) log.append ("Файл <" + fileName +  "> успешно создан в директории: <"
                                                        + parentDir + "> [" + date.toString() + "]\n");
            } catch (IOException ex) {
                log.append ("В процессе создания файла <" + fileName +  "> в директории: <" + parentDir
                        + "> произошла ошибка: " + ex.getMessage() + " [" + date.toString() + "]\n");
            }
        }

        return log.toString();
    }

    public static void writeToTextFile(String path, String text){
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write(text);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args){
        StringBuilder log = new StringBuilder();

        log.append(createDirs("Games", new String[] {"src", "res", "savegames", "temp"}));
        log.append(createDirs("Games//src", new String[] {"main", "test"}));
        log.append(createDirs("Games//res", new String[] {"drawables", "vectors", "icons"}));
        log.append(createFiles("Games//src//main", new String[] {"Main.java", "Utils.java"}));

        writeToTextFile("Games//temp//temp.txt", log.toString());
    }
}
