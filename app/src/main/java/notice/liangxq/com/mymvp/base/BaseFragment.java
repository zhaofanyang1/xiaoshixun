package notice.liangxq.com.mymvp.base;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.xmlpull.v1.XmlPullParser;

import notice.liangxq.com.mymvp.view.BaseView;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.base
 * 文件名：BaseFragment
 * 创建者：liangxq
 * 创建时间：2018/12/11  20:25
 * 描述：TODO
 */
public abstract class BaseFragment<V, P extends BasePresenter<V>> extends SimpleFragment {

    public P presenter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView((V) this);
        }
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
        EventBus.getDefault().unregister(this);
    }

    //创建指定的P层对象
    public abstract P createPresenter();
}
