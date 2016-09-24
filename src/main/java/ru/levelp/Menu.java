package ru.levelp;

import ru.levelp.file_utils.FileCompressor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {
    private static final Menu instance = new Menu();

    public static Menu getInstance() {
        return instance;
    }

    private Menu() {
    }

    public void start() throws IOException {
        int select;
        String str;
        FileCompressor compressor = new FileCompressor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("1 - создать бэкап файла");
            System.out.println("2 - получить бэкап по id");
            System.out.println("3 - список бэкапов");
            System.out.println("4 - выход");

            str = reader.readLine();
            select = Integer.parseInt(str);

            if (select == 1) {
                System.out.println("ВВедите полное имя файла(включая путь и расширение) или папки(включая путь)");
                String sourcePath = reader.readLine();

                System.out.println("Введите имя сохраняемого бэкапа с расширением .zip");
                String targetPath = reader.readLine();

                compressor.zip(sourcePath, targetPath);
            } else if (select == 2) {

            } else if (select == 3) {

            } else if (select == 4) {
                reader.close();
                break;
            }


        }
    }
}
