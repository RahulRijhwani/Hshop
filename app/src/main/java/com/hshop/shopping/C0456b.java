package com.hshop.shopping;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

/* renamed from: com.pumis.b.b */
public class C0456b extends Application {
    public static String f2907a;
    public static String f2907a1;
    public static String f2907a2;
    public static String f2907a3;
    public static String f2907a4;
    public static String f2907a5;
    public static SharedPreferences f2467p;
    public static SharedPreferences f2467p1;
    public static SharedPreferences f2467p2;
    public static SharedPreferences f2467p3;
    public static SharedPreferences f2467p4;
    public static SharedPreferences f2467p5;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    static {
        f2907a = "MyPrefs";
    }
    static {
        f2907a1= "MyPrefs";
    }
    static {
        f2907a2= "MyPrefs";
    }
    static {
        f2907a3= "MyPrefs";
    }
    static {
        f2907a4= "MyPrefs";
    }
    static {
        f2907a5= "MyPrefs";
    }

}
