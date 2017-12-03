package chencheng.bwie.com.chencheng_autobanner;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by dell on 2017/12/2.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration imageLoaderConfiguration=ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }
}
