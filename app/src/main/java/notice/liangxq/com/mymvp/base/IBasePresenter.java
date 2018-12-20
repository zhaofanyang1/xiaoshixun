package notice.liangxq.com.mymvp.base;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import notice.liangxq.com.mymvp.view.BaseView;

/**
 * 项目名：xiaoshixun
 * 包名：  notice.liangxq.com.mymvp.base
 * 文件名：IBasePresenter
 * 创建者：liangxq
 * 创建时间：2018/12/11  20:36
 * 描述：TODO
 */
public class IBasePresenter<V extends BaseView> implements BasePresenter<V> {

    private WeakReference<V> weakReference;

    public V mView;


    @Override
    public void attachView(V v) {
        weakReference = new WeakReference<V>(v);
        mView = weakReference.get();
    }

    @Override
    public void detachView() {
        if (weakReference != null) {
            weakReference.clear();
            mView = null;
        }
    }


}
