package com.netlight.sec.finstergram;

import android.app.Application;
import android.util.Base64;
import com.netlight.sec.finstergram.data.UserSettings;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* compiled from: FintergramApplication.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lcom/netlight/sec/finstergram/FinstergramApplication;", "Landroid/app/Application;", "()V", "encryption", "", "nonSecureRandomNumbers", "onCreate", "", "randomNumbers", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes4.dex */
public final class FinstergramApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        UserSettings.INSTANCE.load(this);
        encryption();
        randomNumbers();
        nonSecureRandomNumbers();
    }

    public final String randomNumbers() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomNumbers = new byte[16];
        secureRandom.nextBytes(randomNumbers);
        String encodeToString = Base64.encodeToString(randomNumbers, 0);
        Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(randomNumbers, Base64.DEFAULT)");
        return encodeToString;
    }

    public final String nonSecureRandomNumbers() {
        int random1 = (int) new Date().getTime();
        Calendar c = Calendar.getInstance();
        int random2 = c.get(14);
        byte[] randomNumbers = new byte[16];
        Iterable $this$forEach$iv = RangesKt.until(0, 16);
        Iterator<Integer> it = $this$forEach$iv.iterator();
        while (it.hasNext()) {
            int element$iv = ((IntIterator) it).nextInt();
            randomNumbers[element$iv] = (byte) RangesKt.random(new IntRange(0, 255), Random.INSTANCE);
        }
        String random3 = Base64.encodeToString(randomNumbers, 0);
        return "Generated random numbers:\n" + random1 + " \n" + random2 + " \n" + random3;
    }

    public final String encryption() {
        byte[] keyBytes = {108, 97, 107, 100, 115, 108, 106, 107, 97, 108, 107, 106, 108, 107, 108, 115};
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(1, secretKey);
        byte[] bytes = "my secret here".getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        SecretKeySpec badSecretKeySpec = new SecretKeySpec(bytes, "AES");
        return "SUCCESS!!\n\nThe keys were generated and used successfully with the following details:\n\nHardcoded AES Encryption Key: " + Base64.encodeToString(keyBytes, 0) + "\nHardcoded Key from string: " + Base64.encodeToString(badSecretKeySpec.getEncoded(), 0) + '\n';
    }
}
