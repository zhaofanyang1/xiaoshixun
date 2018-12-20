package notice.liangxq.com.mymvp.view.zhihu;

import notice.liangxq.com.mymvp.bean.zhihu.DailyBeforeListBean;
import notice.liangxq.com.mymvp.bean.zhihu.DailyListBean;
import notice.liangxq.com.mymvp.view.BaseView;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.view.zhihu
 * 文件名：DailyView
 * 创建者：liangxq
 * 创建时间：2018/12/12  20:24
 * 描述：TODO
 */
public interface DailyView extends BaseView{
    //设置信息
    void showContent(DailyListBean info);

    void showMoreContent(DailyBeforeListBean dailyBeforeListBean);

}
