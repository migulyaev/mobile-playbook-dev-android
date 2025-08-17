package org.owasp.mstg.antifrida;

import android.os.Bundle;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.stealthcopter.networktools.PortScan;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFridaServerTextView();
        setFridaMemoryTextView();
        setCheckSigTextView();
        ((Button) findViewById(R.id.ButtonFridaServer)).setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstg.antifrida.MainActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.setFridaServerTextView();
            }
        });
        ((Button) findViewById(R.id.ButtonFridaMemory)).setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstg.antifrida.MainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.setFridaMemoryTextView();
            }
        });
        ((Button) findViewById(R.id.ButtonCheckSignature)).setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstg.antifrida.MainActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainActivity.this.setCheckSigTextView();
            }
        });
    }

    public void setCheckSigTextView() {
        String appSignature = getSignature();
        TextView tv = (TextView) findViewById(R.id.signature);
        if (appSignature.isEmpty()) {
            Toast.makeText(this, "App Signature is empty! You were tampering the App!", 1).show();
            Log.e("Sign Base64 empty", appSignature);
            tv.setText("Signature is empty!");
            tv.setBackgroundResource(17170454);
        } else if (appSignature.contains("99sL2NrjIHWOtn7nBqgQ3Qwvlyc=")) {
            Toast.makeText(this, "App Signature is verified and ok! No tampering detected!", 1).show();
            Log.e("Sign Base64", "App Signature is verified and ok");
            Log.e("Sign Base64 verified", appSignature);
            tv.setText("Signature is valid!");
            tv.setBackgroundResource(17170452);
        } else {
            Toast.makeText(this, "App Signature changed! You were tampering the App!", 1).show();
            Log.e("Sign Base64 changed", appSignature);
            tv.setText("Signature is tampered!");
            tv.setBackgroundResource(17170454);
        }
    }

    public void setFridaMemoryTextView() {
        TextView tv = (TextView) findViewById(R.id.fridaMemory);
        if (checkMemory()) {
            Toast.makeText(this, "Frida detected in Memory!", 1).show();
            tv.setText("Frida detected in Memory!");
            tv.setBackgroundResource(17170454);
            return;
        }
        Toast.makeText(this, "Frida couldn't be found in memory!", 1).show();
        tv.setText("Frida not detected in Memory!");
        tv.setBackgroundResource(17170452);
    }

    public void setFridaServerTextView() {
        TextView tv = (TextView) findViewById(R.id.fridaServer);
        try {
            if (PortScanFrida("127.0.0.1")) {
                Toast.makeText(this, "Frida Server detected!", 1).show();
                tv.setText("Frida server detected!");
                tv.setBackgroundResource(17170454);
                return;
            }
            Toast.makeText(this, "No Frida Server detected!", 1).show();
            tv.setText("Frida server not detected!");
            tv.setBackgroundResource(17170452);
        } catch (UnknownHostException e) {
            while (true) {
                e.printStackTrace();
                return;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Exception] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump */
    public java.lang.String getSignature() {
        /*
            r10 = this;
            java.lang.String r0 = ""
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            r2 = 28
            r3 = 0
            if (r1 < r2) goto L_0x0050
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            java.lang.String r2 = r10.getPackageName()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            r4 = 134217728(0x8000000, float:3.85186E-34)
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r4)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            android.content.pm.SigningInfo r2 = r1.signingInfo     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            android.content.pm.Signature[] r2 = r2.getSigningCertificateHistory()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            java.lang.String r4 = "SHA"
            java.security.MessageDigest r4 = java.security.MessageDigest.getInstance(r4)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            int r5 = r2.length     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            r6 = r0
            r0 = 0
        L_0x0026:
            if (r0 >= r5) goto L_0x004d
            r7 = r2[r0]     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            byte[] r8 = r7.toByteArray()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            r4.update(r8)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            java.lang.String r8 = new java.lang.String     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            byte[] r9 = r4.digest()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            byte[] r9 = android.util.Base64.encode(r9, r3)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            r8.<init>(r9)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            r6 = r8
            java.lang.String r8 = "Sign Base64 API 28 "
            android.util.Log.d(r8, r6)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x004a, Exception -> 0x0047
            int r0 = r0 + 1
            goto L_0x0026
        L_0x0047:
            r0 = move-exception
            r5 = r6
            goto L_0x008f
        L_0x004a:
            r0 = move-exception
            r5 = r6
            goto L_0x009c
        L_0x004d:
            r5 = r6
            goto L_0x009f
        L_0x0050:
            android.content.pm.PackageManager r1 = r10.getPackageManager()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            java.lang.String r2 = "org.owasp.mstg.antifrida"
            r4 = 64
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r2, r4)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            android.content.pm.Signature[] r2 = r1.signatures     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            int r4 = r2.length     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x0099, NameNotFoundException | NoSuchAlgorithmException -> 0x0099, Exception -> 0x008c
            r5 = r0
            r0 = 0
        L_0x0061:
            if (r0 >= r4) goto L_0x009f
            r6 = r2[r0]     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            java.lang.String r7 = "SHA"
            java.security.MessageDigest r7 = java.security.MessageDigest.getInstance(r7)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            byte[] r8 = r6.toByteArray()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            r7.update(r8)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            java.lang.String r8 = new java.lang.String     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            byte[] r9 = r7.digest()     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            byte[] r9 = android.util.Base64.encode(r9, r3)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            r8.<init>(r9)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            r5 = r8
            java.lang.String r8 = "Sign Base64 API < 28 "
            android.util.Log.e(r8, r5)     // Catch: NameNotFoundException | NoSuchAlgorithmException -> 0x008a, Exception -> 0x0088
            int r0 = r0 + 1
            goto L_0x0061
        L_0x0088:
            r0 = move-exception
            goto L_0x008f
        L_0x008a:
            r0 = move-exception
            goto L_0x009c
        L_0x008c:
            r1 = move-exception
            r5 = r0
            r0 = r1
        L_0x008f:
            java.lang.String r1 = "exception"
            java.lang.String r2 = r0.toString()
            android.util.Log.e(r1, r2)
            goto L_0x00a0
        L_0x0099:
            r1 = move-exception
            r5 = r0
            r0 = r1
        L_0x009c:
            r0.printStackTrace()
        L_0x009f:
        L_0x00a0:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.owasp.mstg.antifrida.MainActivity.getSignature():java.lang.String");
    }

    public boolean checkMemory() {
        try {
            int pid = Process.myPid();
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec("cat /proc/" + pid + "/maps");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            char[] buffer = new char[1024];
            StringBuffer output = new StringBuffer();
            while (true) {
                int read = reader.read(buffer);
                if (read <= 0) {
                    break;
                }
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();
            Log.d("fridamemory gadget PID ", Integer.toString(pid));
            Log.d("fridamemory gadget: ", output.toString());
            if (!output.toString().contains("frida-gadget") && !output.toString().contains("frida-agent")) {
                return false;
            }
            Log.d("fridamemory gadget: ", "Frida agent or gadget found!");
            return true;
        } catch (IOException e) {
            return false;
        } catch (InterruptedException e2) {
            return false;
        }
    }

    public boolean PortScanFrida(String ipAddress) throws UnknownHostException {
        Log.d("portscan", "PortScan started");
        Log.d("portscan", "PortScanning IP: " + ipAddress);
        long startTimeMillis = System.currentTimeMillis();
        ArrayList<Integer> openPorts = PortScan.onAddress(ipAddress).setPort(27042).setMethodTCP().doScan();
        Log.d("portscan", "Open Ports: " + openPorts.size());
        Log.d("portscan", "Time taken: " + (((float) (System.currentTimeMillis() - startTimeMillis)) / 1000.0f));
        if (openPorts.size() > 0) {
            return true;
        }
        return false;
    }
}
