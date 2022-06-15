package com.abc.code.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * author: hedianbo.
 * date: 2020-03-09
 * desc:
 */
public class FileUtil {

    public static File fileExists(String downloadPath) {
        return new File(downloadPath);
    }

    public static String getFilePath(Context context, String dir) {
        String directoryPath = "";

        if (MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            directoryPath = context.getExternalFilesDir(dir).getAbsolutePath();
        } else {
            directoryPath = context.getFilesDir() + File.separator + dir;
        }
        File file = new File(directoryPath);
        if (!file.exists()) {//判断文件目录是否存在  
            file.mkdirs();
        }
        return directoryPath;
    }

}
