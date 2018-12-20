package notice.liangxq.com.mymvp.presenter;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import notice.liangxq.com.mymvp.base.IBasePresenter;
import notice.liangxq.com.mymvp.bean.zhihu.DailyBeforeListBean;
import notice.liangxq.com.mymvp.bean.zhihu.DailyListBean;
import notice.liangxq.com.mymvp.module.zhihu.DailyModule;
import notice.liangxq.com.mymvp.utils.DateUtil;
import notice.liangxq.com.mymvp.view.zhihu.DailyView;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.presenter
 * 文件名：DailyPresenter
 * 创建者：liangxq
 * 创建时间：2018/12/12  22:08
 * 描述：TODO
 */
public class DailyPresenter<V extends DailyView> extends IBasePresenter<V> implements DailyModule.DailyListFinish {

    private DailyModule dailyModule=new DailyModule();
    public void getDailyData(){
        if(mView!=null){
            dailyModule.getDailyData(this);
        }
    }

    public void getBeforeDailyData(CalendarDay calendarDay){
        Observable.just(calendarDay)
                .map(new Function<CalendarDay, String>() {
                    @Override
                    public String apply(CalendarDay calendarDay) throws Exception {                            StringBuilder date = new StringBuilder();
                        String year = String.valueOf(calendarDay.getYear());
                        String month = String.valueOf(calendarDay.getMonth() + 1);
                        String day = String.valueOf(calendarDay.getDay() + 1);
                        if(month.length() < 2) {
                            month = "0" + month;
                        }
                        if(day.length() < 2) {
                            day = "0" + day;
                        }
                        return date.append(year).append(month).append(day).toString();
                    }
                }).filter(new Predicate<String>() {
            @Override
            public boolean test(String s) throws Exception {
                if(s.equals(DateUtil.getTomorrowDate())) {
                    getDailyData();
                    return false;
                }
                return true;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                dailyModule.getBeforeData(s,DailyPresenter.this);
            }
        });
    }
    @Override
    public void setHideProgressBar() {
        if(mView!=null){
            mView.hideProgressBar();
        }
    }

    @Override
    public void setShowProgressBar() {
        if(mView!=null){
            mView.showProgressBar();
        }
    }


    @Override
    public void setContent(DailyListBean info) {
        if(mView!=null){
            mView.showContent(info);
        }
    }

    @Override
    public void setMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        if(mView!=null){
            mView.showMoreContent(dailyBeforeListBean);
        }
    }
}
