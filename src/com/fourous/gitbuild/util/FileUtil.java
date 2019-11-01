package com.fourous.gitbuild.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: 文件访问类
 */
public class FileUtil {
    private final static Charset DEFAULT_FILE_ENCODING = StandardCharsets.UTF_8;

    /**
     * 读取文件内容，此方法以默认方式读取文件内容
     *
     * @param filePath
     * @return
     */
    public static String getFileContent(String filePath) {
        return getFileContent(filePath, DEFAULT_FILE_ENCODING);
    }

    /**
     * 读取文件内容
     * 此方法已指定字符集读取文件内容
     *
     * @param filePath 文件路径
     * @param charset  文件字符集编码
     * @return
     */
    public static String getFileContent(String filePath, Charset charset) {
        StringBuilder fileContent = new StringBuilder("");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filePath);
            byte[] buffer = new byte[1024];
            int num = 0;
            while ((num = fileInputStream.read(buffer)) > 0) {
                fileContent.append(new String(buffer, 0, num, charset));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileContent.toString();
    }

    /**
     * 读取文件内容
     * 此方法采用指定字符集读取文件内容
     *
     * @param inputStream 输入流
     * @return
     */
    public static String getFileContent(InputStream inputStream) {
        return getFileContent(inputStream, DEFAULT_FILE_ENCODING);
    }

    /**
     * 读取文件内容
     * 此方法以指定字符集读取文件内容
     *
     * @param inputStream
     * @param charset
     * @return
     */
    public static String getFileContent(InputStream inputStream, Charset charset) {
        StringBuilder fileContent = new StringBuilder("");
        try {
            byte[] buffer = new byte[1024];
            int num = 0;
            while ((num = inputStream.read(buffer)) > 0) {
                fileContent.append(new String(buffer, 0, num, charset));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent.toString();
    }

    /**
     * 创建文件并给文件设置内容，设置文字编码格式为UTF-8格式
     *
     * @param filePathAndName
     * @param fileContent
     */
    public static void newFile(String filePathAndName, String fileContent) {
        PrintWriter writer = null;
        try {
            File newFile = new File(filePathAndName);
            if (newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            writer = new PrintWriter(newFile, DEFAULT_FILE_ENCODING.name());
            writer.println(fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将内容写入文件
     *
     * @param filePathAndName 文件路径
     * @param fileContent     文件内容
     */
    public static void writeFile(String filePathAndName, String fileContent) {
        OutputStreamWriter writer = null;
        BufferedWriter writer1 = null;
        try {
            File file = new File(filePathAndName);
            if (!file.exists()) {
                newFile(filePathAndName, fileContent);
            } else {
                writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
                writer1 = new BufferedWriter(writer);
                writer1.write(fileContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer1 != null) {
                    writer1.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给文件添加内容
     *
     * @param filePathAndName
     * @param fileContent
     */
    public static void appendContent(String filePathAndName, String fileContent) {
        OutputStreamWriter writer = null;
        BufferedWriter writer1 = null;
        try {
            File file = new File(filePathAndName);
            if (!file.exists()) {
                newFile(filePathAndName, fileContent);
            } else {
                writer = new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8);
                writer1 = new BufferedWriter(writer);
                writer1.write(fileContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer1 != null) {
                    writer1.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 扫描文件夹，返回文件夹下所以文件名称
     *
     * @param filePath
     * @return
     */
    public static List<String> scanFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.isFile()) {
            return new ArrayList<>();
        }
        String[] files = file.list();
        return Arrays.asList(files);
    }

    /**
     * 判断文件是否存在
     *
     * @param file
     * @return
     */
    public static boolean exists(String file) {
        return new File(file).exists();
    }

    /**
     * 删除文件或者文件夹，注意在文件夹有内容情况下，会递归删除子文件或者子文件夹
     *
     * @param file
     * @return
     */
    public static boolean delete(File file) {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                return file.delete();
            } else {
                File[] subFiles = file.listFiles();
                for (File subFile : subFiles) {
                    delete(subFile);
                }
                return file.delete();
            }
        } else {
            return true;
        }
    }
}
