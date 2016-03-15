package clertonleal.com.nubank.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import clertonleal.com.nubank.R;
import clertonleal.com.nubank.adapter.BillPagerAdapter;
import clertonleal.com.nubank.entity.Bill;
import clertonleal.com.nubank.service.ConnectionService;
import clertonleal.com.nubank.service.Service;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.tabs)
    TabLayout tabLayout;

    @InjectView(R.id.layout_empty_view)
    LinearLayout emptyView;

    @InjectView(R.id.image_refresh)
    ImageView imageRefresh;

    @InjectView(R.id.pager)
    ViewPager viewPager;

    @InjectView(R.id.app_bar)
    AppBarLayout appBarLayout;

    @Inject
    ConnectionService connectionService;

    @Inject
    Service service;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setOnClickListeners() {
        imageRefresh.setOnClickListener(v -> {
            showEmptyView(false);
            checkInternet();
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        checkInternet();
    }

    private void checkInternet() {
        if (connectionService.hasConnection()) {
            initViewPager();
        } else {
            showEmptyView(true);
        }
    }

    private void initViewPager() {
        showProgressDialog();
        compositeSubscription.add(service.retrieveBills().
                observeOn(AndroidSchedulers.mainThread()).subscribe(bills -> {
            setupTabs(bills);
            cancelProgressDialog();
        }, throwable -> {
            log(throwable);
            showEmptyView(true);
        }));

    }

    private void selectTab(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                View view = tab.getCustomView();
                if (view != null) {
                    View arrow = view.findViewById(R.id.tab_arrow);
                    if (i == position) {
                        arrow.setVisibility(View.VISIBLE);
                    } else {
                        arrow.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    private void setupTabs(List<Bill> bills) {
        BillPagerAdapter billPagerAdapter = new BillPagerAdapter(getSupportFragmentManager(), bills);
        viewPager.setAdapter(billPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(billPagerAdapter.getTabView(i));
            }
        }

        selectTab(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    private void showEmptyView(boolean show) {
        cancelProgressDialog();
        if (show) {
            emptyView.setVisibility(View.VISIBLE);
            appBarLayout.setVisibility(View.GONE);
            viewPager.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            appBarLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void injectMembers() {
        component().inject(this);
    }
}
