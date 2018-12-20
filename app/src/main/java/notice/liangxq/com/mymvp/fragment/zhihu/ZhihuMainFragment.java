package notice.liangxq.com.mymvp.fragment.zhihu;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import notice.liangxq.com.mymvp.R;
import notice.liangxq.com.mymvp.base.SimpleFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuMainFragment extends SimpleFragment {


    @BindView(R.id.tab_zhihu_main)
    TabLayout tabZhihuMain;
    @BindView(R.id.vp_zhihu_main)
    ViewPager vpZhihuMain;

    String[] tabTitle = new String[]{"日报","专栏","热门"};
    List<Fragment> fragments = new ArrayList<Fragment>();
    public ZhihuMainFragment() {
        // Required empty public constructor
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_zhihu_main;
    }

    @Override
    protected void initEventAndData() {
        fragments.add(new DailyFragment());
        fragments.add(new SectionFragment());
        fragments.add(new HotFragment());
        vpZhihuMain.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        //TabLayout配合ViewPager有时会出现不显示Tab文字的Bug,需要按如下顺序
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[0]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[1]));
        tabZhihuMain.addTab(tabZhihuMain.newTab().setText(tabTitle[2]));
        tabZhihuMain.setupWithViewPager(vpZhihuMain);
        tabZhihuMain.getTabAt(0).setText(tabTitle[0]);
        tabZhihuMain.getTabAt(1).setText(tabTitle[1]);
        tabZhihuMain.getTabAt(2).setText(tabTitle[2]);
    }

}
