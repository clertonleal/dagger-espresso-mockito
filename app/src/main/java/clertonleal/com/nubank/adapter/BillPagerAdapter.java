package clertonleal.com.nubank.adapter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import clertonleal.com.nubank.R;
import clertonleal.com.nubank.application.Application;
import clertonleal.com.nubank.entity.Bill;
import clertonleal.com.nubank.entity.BillStatus;
import clertonleal.com.nubank.fragment.BillFragment;

public class BillPagerAdapter extends FragmentPagerAdapter {

    @Inject
    Resources resources;

    @Inject
    LayoutInflater layoutInflater;

    private List<BillFragment> listPhotosFragments = new ArrayList<>();

    public BillPagerAdapter(FragmentManager fragmentManager, List<Bill> bills) {
        super(fragmentManager);
        Application.getComponent().inject(this);
        for (int x = 0; x < bills.size(); x++) {
            BillFragment billFragment = BillFragment.newInstance(bills.get(x), x);
            listPhotosFragments.add(billFragment);
        }
    }

    public View getTabView(int position) {
        View tab =  layoutInflater.inflate(R.layout.tab_name, null);
        TextView tabName = (TextView) tab.findViewById(R.id.tab_name);
        tabName.setText(getPageTitle(position));
        tabName.setContentDescription(String.valueOf(position));
        BillStatus billStatus = listPhotosFragments.get(position).getBill().getState();
        LinearLayout tabArrow = (LinearLayout) tab.findViewById(R.id.tab_arrow);
        tabArrow.setBackgroundResource(billStatus.getArrow());
        tabName.setTextColor(resources.getColor(billStatus.getColor()));
        return tab;
    }

    @Override
    public Fragment getItem(int position) {
        return listPhotosFragments.get(position);
    }

    @Override
    public int getCount() {
        return listPhotosFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return listPhotosFragments.get(position).getPageTitle();
    }
}
