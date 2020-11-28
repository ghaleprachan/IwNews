package com.example.iwnews.services;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;

public class BulletListUtil {
    public static CharSequence makeBulletList(int leadingMargin, CharSequence... lines) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        for (int i = 0; i < lines.length; i++) {
            CharSequence line = lines[i] + (i < lines.length - 1 ? "\n" : "");
            Spannable spannable = new SpannableString(line);
            spannable.setSpan(new BulletSpan(leadingMargin), 0, spannable.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            sb.append(spannable);
        }
        return sb;
    }
}
