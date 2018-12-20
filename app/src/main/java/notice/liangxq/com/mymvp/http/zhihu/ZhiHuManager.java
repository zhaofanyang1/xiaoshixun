package notice.liangxq.com.mymvp.http.zhihu;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import notice.liangxq.com.mymvp.bean.zhihu.CommentBean;
import notice.liangxq.com.mymvp.bean.zhihu.DailyBeforeListBean;
import notice.liangxq.com.mymvp.bean.zhihu.DailyListBean;
import notice.liangxq.com.mymvp.bean.zhihu.DetailExtraBean;
import notice.liangxq.com.mymvp.bean.zhihu.HotListBean;
import notice.liangxq.com.mymvp.bean.zhihu.SectionChildListBean;
import notice.liangxq.com.mymvp.bean.zhihu.SectionListBean;
import notice.liangxq.com.mymvp.bean.zhihu.ThemeChildListBean;
import notice.liangxq.com.mymvp.bean.zhihu.ThemeListBean;
import notice.liangxq.com.mymvp.bean.zhihu.WelcomeBean;
import notice.liangxq.com.mymvp.bean.zhihu.ZhihuDetailBean;
import notice.liangxq.com.mymvp.http.server.ZhihuApis;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.http.zhihu
 * 文件名：ZhiHuManager
 * 创建者：liangxq
 * 创建时间：2018/12/12  15:54
 * 描述：TODO
 *
 * 这个类已经没有用了！这是作为Api的查看
 */
public class ZhiHuManager {

    private ZhihuApis zhihuApis;

    public ZhiHuManager(ZhihuApis zhihuApis) {
        this.zhihuApis = zhihuApis;
    }

    /**
     * 启动界面图片
     */
    public Observable<WelcomeBean> getWelcomeInfo(String res) {
        return zhihuApis.getWelcomeInfo(res);
    }

    ;

    /**
     * 最新日报
     */
    public Observable<DailyListBean> getDailyList() {
        return zhihuApis.getDailyList();
    }

    ;

    /**
     * 往期日报
     */
    public Observable<DailyBeforeListBean> getDailyBeforeList(String date) {
        return getDailyBeforeList(date);
    }

    ;

    /**
     * 主题日报
     */
    public Observable<ThemeListBean> getThemeList() {
        return zhihuApis.getThemeList();
    }

    ;

    /**
     * 主题日报详情
     */
    public Observable<ThemeChildListBean> getThemeChildList(int id) {
        return getThemeChildList(id);
    }

    ;

    /**
     * 专栏日报
     */
    public Observable<SectionListBean> getSectionList() {
        return zhihuApis.getSectionList();
    }

    ;

    /**
     * 专栏日报详情
     */
    public Observable<SectionChildListBean> getSectionChildList(int id) {

        return getSectionChildList(id);
    }

    ;

    /**
     * 热门日报
     */
    public Observable<HotListBean> getHotList() {
       return zhihuApis.getHotList();
    }

    ;

    /**
     * 日报详情
     */
    public  Observable<ZhihuDetailBean> getDetailInfo(int id) {
        return zhihuApis.getDetailInfo(id);
    }

    ;

    /**
     * 日报的额外信息
     */
    public Observable<DetailExtraBean> getDetailExtraInfo(int id) {
        return zhihuApis.getDetailExtraInfo(id);
    }

    ;

    /**
     * 日报的长评论
     */
    public  Observable<CommentBean> getLongCommentInfo(int id) {
        return zhihuApis.getLongCommentInfo(id);
    }

    ;

    /**
     * 日报的短评论
     */
    public Observable<CommentBean> getShortCommentInfo(int id) {
        return zhihuApis.getShortCommentInfo(id);
    };

}
