package com.spy.easyframe.util;


import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangyutian on 16/1/6.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class FileUtils {
    public static final String TAG = FileUtils.class.getSimpleName();
    public static final String FILE_EXTENSION_SEPARATOR = ".";

    public FileUtils() {
    }

    public static StringBuilder readFile(String filePath, String charsetName) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file != null && file.isFile()) {
            BufferedReader reader = null;

            try {
                InputStreamReader e = new InputStreamReader(new FileInputStream(file), charsetName);
                reader = new BufferedReader(e);

                for (String line = null; (line = reader.readLine()) != null; fileContent.append(line)) {
                    if (!fileContent.toString().equals("")) {
                        fileContent.append("\r\n");
                    }
                }

                reader.close();
                StringBuilder var8 = fileContent;
                return var8;
            } catch (IOException var15) {
                throw new RuntimeException("IOException occurred. ", var15);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException var14) {
                        throw new RuntimeException("IOException occurred. ", var14);
                    }
                }

            }
        } else {
            return null;
        }
    }

    public static boolean writeFile(String filePath, String content, boolean append) {
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException var12) {
            throw new RuntimeException("IOException occurred. ", var12);
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException var11) {
                    throw new RuntimeException("IOException occurred. ", var11);
                }
            }

        }

        return true;
    }

    public static boolean writeFile(String filePath, InputStream stream) {
        FileOutputStream o = null;

        try {
            o = new FileOutputStream(filePath);
            byte[] e = new byte[1024];
            boolean length = true;

            int length1;
            while ((length1 = stream.read(e)) != -1) {
                o.write(e, 0, length1);
            }

            o.flush();
            return true;
        } catch (FileNotFoundException var13) {
            throw new RuntimeException("FileNotFoundException occurred. ", var13);
        } catch (IOException var14) {
            throw new RuntimeException("IOException occurred. ", var14);
        } finally {
            if (o != null) {
                try {
                    o.close();
                    stream.close();
                } catch (IOException var12) {
                    throw new RuntimeException("IOException occurred. ", var12);
                }
            }

        }
    }

    public static List<String> readFileToList(String filePath, String charsetName) {
        File file = new File(filePath);
        ArrayList fileContent = new ArrayList();
        if (file != null && file.isFile()) {
            BufferedReader reader = null;

            try {
                InputStreamReader e = new InputStreamReader(new FileInputStream(file), charsetName);
                reader = new BufferedReader(e);
                String line = null;

                while ((line = reader.readLine()) != null) {
                    fileContent.add(line);
                }

                reader.close();
                ArrayList var8 = fileContent;
                return var8;
            } catch (IOException var15) {
                throw new RuntimeException("IOException occurred. ", var15);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException var14) {
                        throw new RuntimeException("IOException occurred. ", var14);
                    }
                }

            }
        } else {
            return null;
        }
    }

    public static String getFileNameWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        } else {
            int extenPosi = filePath.lastIndexOf(".");
            int filePosi = filePath.lastIndexOf(File.separator);
            return filePosi == -1 ? (extenPosi == -1 ? filePath : filePath.substring(0, extenPosi)) : (extenPosi == -1 ? filePath.substring(filePosi + 1) : (filePosi < extenPosi ? filePath.substring(filePosi + 1, extenPosi) : filePath.substring(filePosi + 1)));
        }
    }

    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        } else {
            int filePosi = filePath.lastIndexOf(File.separator);
            return filePosi == -1 ? filePath : filePath.substring(filePosi + 1);
        }
    }

    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        } else {
            int filePosi = filePath.lastIndexOf(File.separator);
            return filePosi == -1 ? "" : filePath.substring(0, filePosi);
        }
    }

    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        } else {
            int extenPosi = filePath.lastIndexOf(".");
            int filePosi = filePath.lastIndexOf(File.separator);
            return extenPosi == -1 ? "" : (filePosi >= extenPosi ? "" : filePath.substring(extenPosi + 1));
        }
    }

    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        } else {
            File folder = new File(folderName);
            return folder.exists() && folder.isDirectory() ? true : folder.mkdirs();
        }
    }

    public static boolean makeFolders(String filePath) {
        return makeDirs(filePath);
    }

    public static boolean isFileExist(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return false;
        } else {
            File file = new File(filePath);
            return file.exists() && file.isFile();
        }
    }

    public static boolean isFolderExist(String directoryPath) {
        if (TextUtils.isEmpty(directoryPath)) {
            return false;
        } else {
            File dire = new File(directoryPath);
            return dire.exists() && dire.isDirectory();
        }
    }

    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        } else {
            try {
                File e = new File(path);
                if (!e.exists()) {
                    return true;
                } else if (e.isFile()) {
                    return e.delete();
                } else if (!e.isDirectory()) {
                    return false;
                } else {
                    if (e != null && e.listFiles() != null && e.listFiles().length > 0) {
                        File[] var5;
                        int var4 = (var5 = e.listFiles()).length;

                        for (int var3 = 0; var3 < var4; ++var3) {
                            File f = var5[var3];
                            if (f.isFile()) {
                                boolean deleted = f.delete();
                                if (!deleted) {
                                    return false;
                                }
                            } else if (f.isDirectory()) {
                                deleteFile(f.getAbsolutePath());
                            }
                        }
                    }

                    return e.delete();
                }
            } catch (Exception var7) {
                LogUtils.e(var7);
                return false;
            }
        }
    }

    public static long getFileSize(String path) {
        if (TextUtils.isEmpty(path)) {
            return -1L;
        } else {
            File file = new File(path);
            return file.exists() && file.isFile() ? file.length() : -1L;
        }
    }

    public static String getParentFolder(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        } else {
            int end = path.lastIndexOf(File.separator);
            if (end != -1) {
                File file = new File(path);
                String parent = file.getParent();
                if (!parent.endsWith(File.separator)) {
                    parent = parent + File.separator;
                }

                return parent;
            } else {
                return null;
            }
        }
    }

    public static boolean deleteAllInFolder(File file) {
        return file == null ? false : deleteAllInFolder(file.getAbsolutePath());
    }

    public static boolean deleteAllInFolder(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.isDirectory()) {
            return flag;
        } else {
            String[] tempList = file.list();
            File temp = null;

            for (int i = 0; i < tempList.length; ++i) {
                if (path.endsWith(File.separator)) {
                    temp = new File(path + tempList[i]);
                } else {
                    temp = new File(path + File.separator + tempList[i]);
                }

                if (temp.isFile() && !temp.delete()) {
                    return false;
                }

                if (temp.isDirectory()) {
                    if (!deleteAllInFolder(path + File.separator + tempList[i])) {
                        return false;
                    }

                    if (!deleteFolder(path + File.separator + tempList[i])) {
                        return false;
                    }

                    flag = true;
                }
            }

            return flag;
        }
    }

    public static boolean deleteFolder(String folder) {
        try {
            deleteAllInFolder(folder);
            String e = folder.toString();
            File file = new File(e);
            return file.delete();
        } catch (Exception var3) {
            LogUtils.e(TAG, "deleteFolder failed!", var3);
            return false;
        }
    }

    public static boolean canReadFile(File file) {
        return file == null ? false : file.isFile() && file.exists() && file.canRead();
    }

    public static long getFileSize(File file) {
        long size = 0L;
        FileInputStream fis = null;

        try {
            if (canReadFile(file)) {
                fis = new FileInputStream(file);
                size = (long) fis.available();
            }
        } catch (IOException var13) {
            ;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException var12) {
                    ;
                }
            }

        }

        return size;
    }

    public static FileInputStream getFileInputStreamNeedClose(File file, int skip) throws IOException {
        if (canReadFile(file)) {
            FileInputStream fis = new FileInputStream(file);
            if (skip > 0) {
                fis.skip((long) skip);
            }

            return fis;
        } else {
            return null;
        }
    }

    public static synchronized File makeDIRAndCreateFile(String filePath) {
        if (!StorageUtils.isSDCardExists()) {
            return null;
        } else {
            File file = new File(filePath);
            String parent = file.getParent();
            File parentFile = new File(parent);
            if (!parentFile.exists()) {
                if (!parentFile.mkdirs()) {
                    return null;
                }

                try {
                    file.createNewFile();
                } catch (IOException var6) {
                    LogUtils.e(TAG, "makeDIRAndCreateFile 1 failed!!!", var6);
                    return null;
                }
            } else if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException var5) {
                    LogUtils.e(TAG, "makeDIRAndCreateFile 2 failed!!!", var5);
                    return null;
                }
            }

            return file;
        }
    }

    public static void saveSerializableObjectToFile(Object object, FileOutputStream fileOut) {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
        } catch (FileNotFoundException var14) {
            LogUtils.e(var14);
        } catch (IOException var15) {
            LogUtils.e(var15);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var13) {
                    LogUtils.e(var13);
                }
            }

        }

    }

    public static Object readSerializableObjectFromFile(FileInputStream fileIn) {
        Object b = null;
        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(fileIn);
            b = in.readObject();
        } catch (Exception var12) {
            LogUtils.e(var12);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var11) {
                    LogUtils.e(var11);
                }
            }

        }

        return b;
    }

    private static File getFile(String dir, String fileName) {
        File file = new File(dir);
        if (!file.exists() && !file.mkdirs()) {
            return null;
        } else {
            file = new File(file, fileName);
            if (file.exists()) {
                return file;
            } else {
                boolean ret = false;

                try {
                    ret = file.createNewFile();
                } catch (IOException var5) {
                    LogUtils.e(var5);
                }

                return ret ? file : null;
            }
        }
    }

    public static boolean writeSingleLineStringToFile(String dir, String fileName, String content) {
        if (!StringUtils.isBlank(dir) && !StringUtils.isBlank(fileName) && !StringUtils.isBlank(content)) {
            File file = getFile(dir, fileName);
            if (file != null && file.exists() && file.isFile()) {
                BufferedWriter out = null;

                try {
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false)));
                    out.write(content);
                    return true;
                } catch (Exception var14) {
                    LogUtils.e(var14);
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException var13) {
                        LogUtils.e(var13);
                    }

                }

                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String readSingleLineStringFromFile(String dir, String fileName) {
        String content = "";
        if (!StringUtils.isBlank(dir) && !StringUtils.isBlank(fileName)) {
            File file = getFile(dir, fileName);
            if (file != null && file.exists() && file.isFile()) {
                BufferedReader in = null;

                try {
                    in = new BufferedReader(new FileReader(file));
                    String e = in.readLine();
                    if (StringUtils.isNotBlank(e)) {
                        content = e;
                    }

                    in.close();
                } catch (IOException var14) {
                    LogUtils.e(var14);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException var13) {
                            LogUtils.e(var13);
                        }
                    }

                }
            }

            return content;
        } else {
            return "";
        }
    }

    public static String getFileDirectoryWithOutSlash(String filePath) {
        int lastIndex = filePath.lastIndexOf(47);
        return lastIndex != -1 ? filePath.substring(0, lastIndex) : "";
    }

    public static String getDataFilesPath(Context context) {
        File file = context.getFilesDir();
        String path = file.getAbsolutePath();
        return path;
    }

    public static String getFilenameForKey(String key, String fileExt) {
        int firstHalfLength = key.length() / 2;
        String localFilename = String.valueOf(key.substring(0, firstHalfLength).hashCode());
        localFilename = localFilename + String.valueOf(key.substring(firstHalfLength).hashCode()) + fileExt;
        return localFilename;
    }

    public static String getFilenameForKey(String key) {
        int firstHalfLength = key.length() / 2;
        String localFilename = String.valueOf(key.substring(0, firstHalfLength).hashCode());
        localFilename = localFilename + String.valueOf(key.substring(firstHalfLength).hashCode());
        return localFilename;
    }

    public static String getApkFileAbsolutePath(Context context, String downloadUrl) {
        String fileName = getFilenameForKey(downloadUrl, ".apk");
        return StorageUtils.getDefaultCacheDir(context.getApplicationContext(), (String) null).getAbsolutePath() + File.separator + fileName;
    }

    public static boolean makesureCreateFile(String filename) throws IOException {
        return TextUtils.isEmpty(filename) ? false : makesureCreateFile(new File(filename));
    }

    public static boolean makesureCreateFile(File file) throws IOException {
        if (file == null) {
            return false;
        } else if (file.exists()) {
            return true;
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            return file.createNewFile();
        }
    }

    public static boolean makesureMkdir(String dirname) {
        return TextUtils.isEmpty(dirname) ? false : makesureMakeDir(new File(dirname));
    }

    public static boolean makesureMakeDir(File dir) {
        return dir == null ? false : (dir.exists() && dir.isDirectory() ? true : dir.mkdirs());
    }

    public static void chmod(String permission, String path) {
        try {
            String e = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(e);
        } catch (IOException var4) {
            LogUtils.e(var4);
        }

    }

    public static String getSeriString(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        String productBase64 = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            productBase64 = new String(Base64.encode(baos.toByteArray()));
        } catch (IOException var15) {
            LogUtils.e(TAG, var15.toString(), var15);
        } catch (OutOfMemoryError var16) {
            LogUtils.e(TAG, var16.toString(), var16);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException var14) {
                    LogUtils.e(TAG, var14.toString(), var14);
                }
            }

        }

        if (productBase64 == null) {
            productBase64 = "";
        }

        return productBase64;
    }

    public static Object getObjectFromBytes(String data) throws Exception {
        byte[] objBytes = Base64.decode(data);
        if (objBytes != null && objBytes.length != 0) {
            ByteArrayInputStream bi = null;
            ObjectInputStream oi = null;
            Object object = null;

            try {
                bi = new ByteArrayInputStream(objBytes);
                oi = new ObjectInputStream(bi);
                object = oi.readObject();
            } finally {
                if (oi != null) {
                    oi.close();
                }

                if (bi != null) {
                    bi.close();
                }

            }

            return object;
        } else {
            return null;
        }
    }
}
