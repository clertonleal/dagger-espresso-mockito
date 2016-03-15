package clertonleal.com.nubank.dagger.component;

import clertonleal.com.nubank.activity.MainActivity;
import clertonleal.com.nubank.adapter.BillPagerAdapter;
import clertonleal.com.nubank.fragment.BillFragment;


public interface Component {
    void inject(MainActivity mainActivity);
    void inject(BillPagerAdapter billPagerAdapter);
    void inject(BillFragment billFragment);
}
