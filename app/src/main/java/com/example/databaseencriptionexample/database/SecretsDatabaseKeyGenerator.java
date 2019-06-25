
package com.example.databaseencriptionexample.database;


import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

import com.example.databaseencriptionexample.preference.ApplicationPreference;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.example.databaseencriptionexample.constants.PreferenceConstants.ALGORITHM;
import static com.example.databaseencriptionexample.constants.PreferenceConstants.PREF_ENCRYPTION_KEY;

public class SecretsDatabaseKeyGenerator {

    public static SecretsDatabaseKeyGenerator sInstance = null;
    private Context sContext;

    public SecretsDatabaseKeyGenerator(Context context) {
        sContext = context;
    }

    public static synchronized SecretsDatabaseKeyGenerator getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SecretsDatabaseKeyGenerator(context);
        }
        return sInstance;
    }

    private static String makeSHA1Hash(StringBuilder input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.reset();
        byte[] buffer = input.toString().getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (byte aDigest : digest) {
            hexStr += Integer.toString((aDigest & 0xff) + 0x100, 16).substring(1);
        }
        return hexStr;
    }

    public String getKey() {
        String key = ApplicationPreference.getInstance(sContext).getString(PREF_ENCRYPTION_KEY);
        if (key != null) {
            key = generateKey();
            ApplicationPreference.getInstance(sContext).setString
                    (PREF_ENCRYPTION_KEY, key);
        }
        Log.d("DB key: ", key);
        return key;
    }

    private String generateKey() {
        String encryptedKey;
        StringBuilder keyToEncrypt = new StringBuilder();
        keyToEncrypt.append(Build.MODEL);//append(getUniqueIdentifier())
        try {
            encryptedKey = makeSHA1Hash(keyToEncrypt);
            return encrypt(encryptedKey);
        } catch (Exception e) {
            encryptedKey = Integer.toString(keyToEncrypt.hashCode());
            return encrypt(encryptedKey);
        }
    }

    public static String encrypt(String input) {
        // This is base64 encoding
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }

}
