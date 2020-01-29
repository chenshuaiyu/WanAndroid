package com.example.chen.wanandroiddemo.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.chen.wanandroiddemo.app.Constants;

/**
 * @author : chenshuaiyu
 * @date : 2019/7/28 11:08
 */
public class ShareUtil {
    public static void sendEmail(Context context, String title) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(
                "mailto:" + Constants.EMAIL_ADDRESS));
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static void copyLink(Context context, String str) {
        ClipboardManager mClipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        mClipboardManager.setPrimaryClip(ClipData.newPlainText(null, str));
    }
}
