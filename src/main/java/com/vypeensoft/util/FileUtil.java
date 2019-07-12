package com.vypeensoft.util;

import java.io.*;
import java.util.*;

public class FileUtil {
    public static Map<String, Long> folderSizeMap = new HashMap<String, Long>();

    //=========================================================================================
    public static List<String> getFolderListRecursive(String sourcePath) {
        File dir = new File(sourcePath);
        if(!dir.exists()) {
            return new ArrayList<String>();
        }
        List<String> fileTree = new ArrayList<String>();
        try {
            for (File entry : dir.listFiles()) {
                if (entry.isFile()) {
                    //Skip files. only incude folders
                } else {
                    fileTree.addAll(getFolderListRecursive(entry.getAbsolutePath()));
                    fileTree.add(entry.getAbsolutePath()); //first add children, then add parent
                }
            }
        } catch(Exception e) {
            //
        }
        return fileTree;
    }
    //=========================================================================================
    public static List<String> getFileListFromFolderRecursive(String sourcePath) {
        return getFileList(sourcePath);
    }
    //-----------------------------------------------------------------------------------------
    public static List<String> getFileListFromFolder(String sourcePath) {
        return getFileList(sourcePath);
    }
    //-----------------------------------------------------------------------------------------
    public static List<String> getFileList(String sourcePath) {
        System.out.println("REM -- "+sourcePath);
        File dir = new File(sourcePath);
        if(!dir.exists()) {
            return new ArrayList<String>();
        }
        List<String> fileTree = new ArrayList<String>();
        for (File entry : dir.listFiles()) {
            if (entry.isFile())
                fileTree.add(entry.getAbsolutePath());
            else
                fileTree.addAll(getFileList(entry.getAbsolutePath()));
        }
        Collections.sort(fileTree);
        return fileTree;
    }

    //=========================================================================================
    public static List<String> getFileListFromCurrentFolder(String sourcePath) {
        File dir = new File(sourcePath);
        List<String> fileTree = new ArrayList<String>();
        for (File entry : dir.listFiles()) {
            if (entry.isFile()) {
                fileTree.add(entry.getAbsolutePath());
            }
        }
        Collections.sort(fileTree);
        return fileTree;
    }

    //=========================================================================================
    public static void getFolderSizeRecursive(String sourcePath) {
        //System.out.println("REM sourcePath == "+sourcePath);
        long fileSizeCombined = 0;
        File dir = new File(sourcePath);
        List<String> fileTree = new ArrayList<String>();
        for (File entry : dir.listFiles()) {
            if (entry.isFile()) {
                long size = entry.length();
                Long existingSize = folderSizeMap.get(sourcePath);
                if(existingSize == null) {
                    existingSize = new Long(0);
                }
                folderSizeMap.put(sourcePath, new Long(existingSize.longValue() + size));
            } else {
                getFolderSizeRecursive(entry.getAbsolutePath());
                Long subfolderSize = folderSizeMap.get(entry.getAbsolutePath());
                Long existingSize = folderSizeMap.get(sourcePath);
                if(subfolderSize == null) {
                    subfolderSize = new Long(0);
                }
                if(existingSize == null) {
                    existingSize = new Long(0);
                }
                folderSizeMap.put(sourcePath, new Long(existingSize.longValue() + subfolderSize.longValue()));
            }
        }
    }
    //=========================================================================================
    public static ArrayList<String> readListFromFile(String fileName) throws Exception {
        ArrayList<String> returnArray = new ArrayList<String>();
        BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
        try {
            String line = null; // not declared within while loop
            while ((line = input.readLine()) != null) {
                if(line.trim().equals("")) {
                  continue;
                }
                returnArray.add(line);
            }
        } finally {
            input.close();
        }
        return returnArray;
    }
    //=========================================================================================
    public static String readFileContentsAsString(String fileName) throws Exception {
        StringBuffer returnStringBuffer = new StringBuffer();
        BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
        try {
            String line = null; // not declared within while loop
            while ((line = input.readLine()) != null) {
                returnStringBuffer.append(line+"\r\n");
            }
        } finally {
            input.close();
        }
        return returnStringBuffer.toString();
    }
    //=========================================================================================
    public static List<String> readFileContentsAsStringList(String fileName) throws Exception {
        List<String> returnArray = new ArrayList<String>();
        BufferedReader input = new BufferedReader(new FileReader(new File(fileName)));
        try {
            String line = null; // not declared within while loop
            while ((line = input.readLine()) != null) {
                returnArray.add(line);
            }
        } finally {
            input.close();
        }
        return returnArray;
    }
    //=========================================================================================
    public static void writeListToFile(String fileListLocation, List<String> fileList) throws Exception {
        FileOutputStream fos1 = new FileOutputStream(new File(fileListLocation));
        for (int i = 0; i < fileList.size(); i++) {
            fos1.write((fileList.get(i) + System.getProperty("line.separator")).getBytes());
        }
        fos1.close();
    }
    //=========================================================================================
    public static void writeStringToNewFile(String fileListLocation, String str) throws Exception {
        FileOutputStream fos1 = new FileOutputStream(new File(fileListLocation));
        fos1.write((str + System.getProperty("line.separator")).getBytes());
        fos1.close();
    }
    //=========================================================================================
    public static void appendStringToFile(String fileListLocation, String str) throws Exception {
        FileOutputStream fos1 = new FileOutputStream(new File(fileListLocation), true);
        fos1.write((str + System.getProperty("line.separator")).getBytes());
        fos1.close();
        
    }
    //=========================================================================================
    public static void deleteFile(String fileListLocation) throws Exception {
        File f = new File(fileListLocation);
        f.delete();
    }
    //=========================================================================================
    public static boolean checkFileExists(String fileListLocation) throws Exception {
        File f = new File(fileListLocation);
        return f.exists();
    }
    //=========================================================================================
    public static String sanitizeFileName(String fileName) {
        fileName = fileName.replaceAll(":","-");
        fileName = fileName.replaceAll("/"," ");
        fileName = fileName.replaceAll("\\*","-");
        fileName = fileName.replaceAll("\\?"," ");

        return fileName;
    }
    //=========================================================================================
    //=========================================================================================
    //=========================================================================================
    //=========================================================================================
}
