package ru.levelp.file_utils;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileCompressor {
    public void zip(String sourcePath, String targetPath) throws IOException {
        Path path = FileSystems.getDefault().getPath(sourcePath);
        ArrayList<File> filesToCompress = new ArrayList<>();
        doFileList(path.toFile(), filesToCompress);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetPath));

        for (File f : filesToCompress) {
            out.putNextEntry(new ZipEntry(f.getPath()));


            FileInputStream fis = new FileInputStream(f);
            byte[] buffer = new byte[2048];
            int len;
            while ((len = fis.read(buffer)) >= 0) {
                out.write(buffer, 0, len);

            }
            fis.close();

        }
        out.close();
    }

    private ArrayList<File> doFileList(File file, ArrayList<File> filesToCompress) {
        if (file.isFile()) {
            filesToCompress.add(file);
        } else {
            for (File f : file.listFiles()) {
                doFileList(f, filesToCompress);
            }
        }
        return filesToCompress;
    }


    public void unzip(String sourceZip, String targetFolder) throws IOException {

    }
}
