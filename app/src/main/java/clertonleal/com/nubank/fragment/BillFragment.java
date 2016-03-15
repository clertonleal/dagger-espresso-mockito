package clertonleal.com.nubank.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.InjectView;
import clertonleal.com.nubank.R;
import clertonleal.com.nubank.adapter.LineItemAdapter;
import clertonleal.com.nubank.application.Application;
import clertonleal.com.nubank.entity.Bill;
import clertonleal.com.nubank.service.Service;
import clertonleal.com.nubank.util.FormatUtil;

public class BillFragment extends BaseFragment {

    @Inject
    Service service;

    @Inject
    LineItemAdapter lineItemAdapter;

    @Inject
    Resources resources;

    @Inject
    BillViewController billViewController;

    @InjectView(R.id.list)
    RecyclerView recyclerView;

    LinearLayoutManager linearLayoutManager;

    private Bill bill;

    private int position;

    public static BillFragment newInstance(Bill bill, int position) {
        BillFragment fragment = new BillFragment();
        fragment.setBill(bill);
        fragment.setPosition(position);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bill_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureRecycleView();
        billViewController.showBill(bill, view);
        billViewController.setContentDescription(position);
    }

    private void configureRecycleView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(lineItemAdapter);
        lineItemAdapter.setLineItems(bill.getLineItems());
    }

    public String getPageTitle() {
        return FormatUtil.getMonth(bill.getSummary().getDueDate());
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    protected void injectMembers() {
        Application.getComponent().inject(this);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
