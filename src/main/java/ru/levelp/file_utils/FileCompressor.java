package ru.levelp.file_utils;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FileCompressor {

    public void zip(String sourcePath, String targetPath) throws IOException {
        Path path = FileSystems.getDefault().getPath(sourcePath);
        ArrayList<File> filesToCompress = new ArrayList<>();
        doFileList(path.toFile(), filesToCompress);
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetPath));
        for (File f : filesToCompress) {
            zos.putNextEntry(new ZipEntry(f.getPath()));
            FileInputStream fis = new FileInputStream(f);
            write(fis, zos);
            fis.close();
        }
        zos.close();
    }

    private void write(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[2048];
        int len;
        while ((len = is.read(buffer)) >= 0)
            os.write(buffer, 0, len);
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


    public void unzip(String sourceZip) throws IOException {
        String dstDirectory = destinationDirectory(sourceZip);
        File dstDir = new File(dstDirectory);

        if (!dstDir.exists()) {
            dstDir.mkdir();
        }
        ZipInputStream zis = new ZipInputStream(new FileInputStream(sourceZip));
        ZipEntry ze = zis.getNextEntry();
        String nextFileName;
        while (ze != null) {
            nextFileName = ze.getName();
            File nextFile = new File(dstDirectory + File.separator + nextFileName);
            if (ze.isDirectory()) {
                nextFile.mkdir();
            } else {
                new File(nextFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(nextFile);
                write(zis, fos);
                fos.close();
            }
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

    }

    private String destinationDirectory(final String srcZip) {
        return srcZip.substring(0, srcZip.lastIndexOf("."));
    }


}
