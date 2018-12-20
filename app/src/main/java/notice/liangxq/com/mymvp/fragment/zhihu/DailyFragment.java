package notice.liangxq.com.mymvp.fragment.zhihu;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import notice.liangxq.com.mymvp.R;
import notice.liangxq.com.mymvp.activitys.CalendarActivity;
import notice.liangxq.com.mymvp.adapter.DailyAdapter;
import notice.liangxq.com.mymvp.base.BaseFragment;
import notice.liangxq.com.mymvp.bean.zhihu.DailyBeforeListBean;
import notice.liangxq.com.mymvp.bean.zhihu.DailyListBean;
import notice.liangxq.com.mymvp.presenter.DailyPresenter;
import notice.liangxq.com.mymvp.utils.CircularAnimUtil;
import notice.liangxq.com.mymvp.utils.DateUtil;
import notice.liangxq.com.mymvp.view.zhihu.DailyView;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * 日报Fragment
 */
public class DailyFragment extends BaseFragment<DailyView, DailyPresenter<DailyView>> implements DailyView, XRecyclerView.LoadingListener {


    @BindView(R.id.view_main)
    XRecyclerView viewMain;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    List<DailyListBean.StoriesBean> mList = new ArrayList<>();
    Unbinder unbinder;
    private DailyAdapter dailyAdapter;
    //用于刷新的时候判断日期
    String currentDate;

    public DailyFragment() {
        // Required empty public constructor
    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_daily;
    }


    @Override
    protected void initEventAndData() {
        //获取当前的时间
        currentDate = DateUtil.getTomorrowDate();
        viewMain.setLayoutManager(new LinearLayoutManager(activity));
        dailyAdapter = new DailyAdapter(mList, activity);
        viewMain.setAdapter(dailyAdapter);
        viewMain.setLoadingListener(this);
        presenter.getDailyData();
    }

    @Override
    public DailyPresenter<DailyView> createPresenter() {
        return new DailyPresenter<>();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent(DailyListBean info) {
        dailyAdapter.addDailyDate(info);
    }

    @Override
    public void showMoreContent(DailyBeforeListBean dailyBeforeListBean) {
        //将返回的日期赋值给全局的日期，用于刷新数据用
        currentDate = String.valueOf(Integer.valueOf(dailyBeforeListBean.getDate()));
        dailyAdapter.addDailyBeforeDate(dailyBeforeListBean);
    }

    @Override
    public void onRefresh() {
        if (currentDate.equals(DateUtil.getTomorrowDate())) {
            presenter.getDailyData();
        } else {
            int year = Integer.valueOf(currentDate.substring(0, 4));
            int month = Integer.valueOf(currentDate.substring(4, 6));
            int day = Integer.valueOf(currentDate.substring(6, 8));
            CalendarDay date = CalendarDay.from(year, month - 1, day);
            EventBus.getDefault().post(date);
        }
        viewMain.refreshComplete();

    }

    @Override
    public void onLoadMore() {
        viewMain.loadMoreComplete();
    }

    /**
     * 接收日期重新发起请求
     * @param date
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(CalendarDay date) {
        if (presenter != null) {
            presenter.getBeforeDailyData(date);
        }
    }


    @OnClick(R.id.fab_calender)
    public void onViewClicked() {
        Intent it = new Intent();
        it.setClass(activity, CalendarActivity.class);
        CircularAnimUtil.startActivity(activity, it, fabCalender, R.color.fab_bg);
    }
}
