package com.example.mobiledevproject.Shared;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.widget.TextView;

import com.example.mobiledevproject.R;

import java.io.File;
import java.text.DecimalFormat;

import static android.content.Context.ACTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class GetCache {

    Activity activity;

    public GetCache(Activity activity) {
        this.activity = activity;
    }

    public String initializeCache() {
        long size = 0;
        size += getDirSize(activity.getCacheDir());
        size += getDirSize(activity.getExternalCacheDir());
        return readableFileSize(size);
    }

    public long getDirSize(File dir) {
        long size = 0;
        for (File file : dir.listFiles()) {
            if (file != null && file.isDirectory()) {
                size += getDirSize(file);
            } else if (file != null && file.isFile()) {
                size += file.length();
            }
        }
        return size;
    }

    public static String readableFileSize(long size) {
        if (size <= 0) return "0 Bytes";
        final String[] units = new String[]{"Bytes", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];

    }

}

