
package com.example.databaseencriptionexample.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.databaseencriptionexample.constants.PreferenceConstants;

/**
 * This class handles the saving and retrieving the persistence storage
 * data using the Androids SharedPresence apis.
 */
public class ApplicationPreference {

    /**
     * Singleton instance of the ApplicationPreference
     */
    private static ApplicationPreference mApplicationPreference = null;


    /**
     * Instance of SharedPreferences to access the apis.
     */
    private SharedPreferences mSharedPreferences;

    /**
     * Private constructor to follow Singleton pattern.
     *
     * @param context instance of Context
     */
    private ApplicationPreference(Context context) {
        initialize(context);
    }

    /**
     * Metood to return ApplicationPreference instance.
     *
     * @param context instance of Context
     * @return Singleton instance of ApplicationPreference
     */
    public static ApplicationPreference getInstance(Context context) {
        if (mApplicationPreference == null) {
            mApplicationPreference = new ApplicationPreference(context);
        }
        return mApplicationPreference;
    }

    /**
     * This function initializes the SharedPresence
     *
     * @param context
     */
    private void initialize(Context context) {
        mSharedPreferences = context.getSharedPreferences(PreferenceConstants
                .PREFERENCE_FILE_NAME, Context
                .MODE_PRIVATE);
    }

    /**
     * This function sets the String value of the supplied key.
     *
     * @param key   preference key
     * @param value value of the supplied key to be set.
     */
    public void setString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * This function returns the String value of the supplied key.
     *
     * @param key preference key
     * @return value of the supplied key if present, otherwise empty string
     */
    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

}
