package infrastructure.base;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.util.Log;

import java.io.File;


/**
 * Created by Anthony on 2016/6/3.
 * Class Note:
 * Base Application for Application
 * use in AndroidManifest.xml
 */
public class BaseApplication extends Application {

//    DataManager mDataManager;
    private static Handler mHandler;
    private static Context mContext;



    @Override
    public void onCreate() {

        super.onCreate();

        mHandler = new Handler();

        mContext = getApplicationContext();

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//multi dex support
    }

    public static Context getContext() {
        return mContext;
    }

    public static Handler getHanlder() {
        return mHandler;
    }



    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }

    /**
     * get cache dir
     *
     * @return cache directory
     */
    @Override
    public File getCacheDir() {
        Log.i("getCacheDir", "cache sdcard state: " + Environment.getExternalStorageState());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File cacheDir = getExternalCacheDir();
            if (cacheDir != null && (cacheDir.exists() || cacheDir.mkdirs())) {
                Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());
                return cacheDir;
            }
        }

        File cacheDir = super.getCacheDir();
        Log.i("getCacheDir", "cache dir: " + cacheDir.getAbsolutePath());

        return cacheDir;
    }


}
