package notice.liangxq.com.mymvp.module.zhihu;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import notice.liangxq.com.mymvp.app.MyApp;
import notice.liangxq.com.mymvp.base.BaseObserver;
import notice.liangxq.com.mymvp.base.HttpFinishCallBack;
import notice.liangxq.com.mymvp.bean.zhihu.DailyBeforeListBean;
import notice.liangxq.com.mymvp.bean.zhihu.DailyListBean;
import notice.liangxq.com.mymvp.http.HttpManager;
import notice.liangxq.com.mymvp.http.server.ZhihuApis;
import notice.liangxq.com.mymvp.http.zhihu.ZhiHuManager;
import notice.liangxq.com.mymvp.utils.RxUtils;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.module.zhihu
 * 文件名：DailyModule
 * 创建者：liangxq
 * 创建时间：2018/12/12  19:41
 * 描述：TODO
 */
public class DailyModule  {

    public DailyModule() {

    }


    public interface DailyListFinish extends HttpFinishCallBack {

        void setContent(DailyListBean info);

        void setMoreContent(DailyBeforeListBean dailyBeforeListBean);
    }

   public void getDailyData(final DailyListFinish dailyListFinish) {
        dailyListFinish.setShowProgressBar();
       MyApp.getZhihuServer().getDailyList().compose(RxUtils.<DailyListBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyListBean>(dailyListFinish) {
                    @Override
                    public void onNext(DailyListBean value) {
                        dailyListFinish.setContent(value);
                    }
                });

    }


   public void getBeforeData(String date, final DailyListFinish dailyListFinish) {
        dailyListFinish.setShowProgressBar();
       MyApp.getZhihuServer().getDailyBeforeList(date).compose(RxUtils.<DailyBeforeListBean>rxObserableSchedulerHelper()).subscribe(new BaseObserver<DailyBeforeListBean>(dailyListFinish) {
            @Override
            public void onNext(DailyBeforeListBean value) {
                dailyListFinish.setMoreContent(value);
            }
        });
    }


    void startInterval() {

    }


    void stopInterval() {

    }


    void insertReadToDB(int id) {

    }

}
