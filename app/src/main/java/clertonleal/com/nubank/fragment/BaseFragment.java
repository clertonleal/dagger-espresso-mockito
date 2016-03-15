package clertonleal.com.nubank.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import butterknife.ButterKnife;
import clertonleal.com.nubank.application.Application;
import clertonleal.com.nubank.dagger.component.Component;
import rx.subscriptions.CompositeSubscription;

public abstract class BaseFragment extends Fragment {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        injectMembers();
        setOnClickListeners();
    }

    @Override
    public void onDestroyView() {
        unsubscribeSubscriptions();
        super.onDestroyView();
    }

    protected void unsubscribeSubscriptions(){
        compositeSubscription.unsubscribe();
        compositeSubscription = new CompositeSubscription();
    }

    protected Component getComponent() {
        return Application.getComponent();
    }

    protected void log(Throwable e){
        Log.e(getTag(), "", e);
    }

    protected void setOnClickListeners(){}

    protected abstract void injectMembers();

}
