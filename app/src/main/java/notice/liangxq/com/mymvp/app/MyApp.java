package notice.liangxq.com.mymvp.app;

import android.app.Application;

import notice.liangxq.com.mymvp.http.HttpManager;
import notice.liangxq.com.mymvp.http.server.ZhihuApis;
import notice.liangxq.com.mymvp.http.zhihu.ZhiHuManager;
import notice.liangxq.com.mymvp.utils.ImplPreferencesHelper;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.app
 * 文件名：MyApp
 * 创建者：liangxq
 * 创建时间：2018/12/11  19:24
 * 描述：TODO
 */
public class MyApp extends Application{
    private static MyApp instance;
    private static ImplPreferencesHelper implPreferencesHelper;
    private static ZhihuApis zhiHuServer;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;

    }

    public static synchronized MyApp getInstance() {
        return instance;
    }

    public static ImplPreferencesHelper getImplPreferencesHelper(){
        if (implPreferencesHelper == null) {
            synchronized (ImplPreferencesHelper.class){
                if (implPreferencesHelper == null) {
                    implPreferencesHelper=new ImplPreferencesHelper();
                }
            }
        }
        return implPreferencesHelper;
    }

    /**
     * 获取知乎网络请求Api
     * @return
     */
    public static ZhihuApis getZhihuServer(){
        if (zhiHuServer == null) {
            synchronized (ZhihuApis.class){
                if (zhiHuServer == null) {
                    zhiHuServer=HttpManager.getInstance().getApiserver(ZhihuApis.HOST,ZhihuApis.class);
                }
            }
        }
        return zhiHuServer;
    }
}
