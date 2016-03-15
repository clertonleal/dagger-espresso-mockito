package clertonleal.com.nubank.fragment;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import clertonleal.com.nubank.R;
import clertonleal.com.nubank.entity.Bill;
import clertonleal.com.nubank.entity.Summary;
import clertonleal.com.nubank.util.FormatUtil;

public class BillViewController {

    @Inject
    Resources resources;

    @InjectView(R.id.list)
    RecyclerView recyclerView;

    @InjectView(R.id.header)
    LinearLayout header;

    @InjectView(R.id.bill_value)
    TextView headerBillValue;

    @InjectView(R.id.bill_due_date)
    TextView headerBillDueDate;

    @InjectView(R.id.bill_message)
    TextView headerBillMessage;

    @InjectView(R.id.value_spent)
    TextView paidValueSpent;

    @InjectView(R.id.paid_block)
    LinearLayout paidBlock;

    @InjectView(R.id.paid_value)
    TextView paidValue;

    @InjectView(R.id.closed_block)
    LinearLayout closedBlock;

    @InjectView(R.id.btn_generate_billet)
    Button generateBillet;

    @InjectView(R.id.from_to_date)
    TextView fromToDate;

    @Inject
    public BillViewController() {}

    public void showBill(Bill bill, View view) {
        ButterKnife.inject(this, view);
        Summary summary = bill.getSummary();

        header.setBackgroundColor(resources.getColor(bill.getState().getColor()));
        headerBillValue.setText(FormatUtil.getMonetaryValue(summary.getTotalCumulative()));
        headerBillDueDate.setText(resources.getString(R.string.expiration, FormatUtil.getShortDate(summary.getDueDate())));
        fromToDate.setText(resources.getString(R.string.from_to_bill_date, FormatUtil.getShortDate(summary.getOpenDate()), FormatUtil.getShortDate(summary.getCloseDate())));

        switch (bill.getState()) {
            case overdue:
                headerBillMessage.setVisibility(View.GONE);
                paidValue.setText(FormatUtil.getMonetaryValue(summary.getPaid()));
                closedBlock.setVisibility(View.GONE);
                generateBillet.setVisibility(View.GONE);
                break;

            case closed:
                headerBillMessage.setVisibility(View.GONE);
                paidBlock.setVisibility(View.GONE);
                fillPaidBlock(summary);
                break;

            case open:
                headerBillMessage.setText(resources.getString(R.string.close_date, FormatUtil.getDate(summary.getCloseDate())));
                paidBlock.setVisibility(View.GONE);
                closedBlock.setVisibility(View.GONE);
                generateBillet.setBackgroundResource(R.drawable.btn_blue);
                generateBillet.setTextColor(resources.getColor(bill.getState().getColor()));
                break;

            case future:
                headerBillMessage.setText(R.string.partial_bill);
                paidBlock.setVisibility(View.GONE);
                closedBlock.setVisibility(View.GONE);
                generateBillet.setVisibility(View.GONE);
                break;
        }
    }

    private void fillPaidBlock(Summary summary) {
        paidValueSpent.setText(FormatUtil.getMonetaryValue(summary.getTotalBalance()));
        if (summary.getPastBalance() == 0) {
            closedBlock.findViewById(R.id.past_balance).setVisibility(View.GONE);
        } else {
            ((TextView)closedBlock.findViewById(R.id.value_no_paid)).setText(FormatUtil.getMonetaryValue(summary.getPastBalance()));
        }

        if (summary.getInterest() == 0) {
            closedBlock.findViewById(R.id.interest).setVisibility(View.GONE);
        } else {
            ((TextView)closedBlock.findViewById(R.id.value_interest)).setText(FormatUtil.getMonetaryValue(summary.getInterest()));
        }
    }

    public void setContentDescription(int position) {
        recyclerView.setContentDescription(String.valueOf(position));
        header.setContentDescription(String.valueOf(position));
        headerBillValue.setContentDescription(String.valueOf(position));
        headerBillDueDate.setContentDescription(String.valueOf(position));
        headerBillMessage.setContentDescription(String.valueOf(position));
        paidValueSpent.setContentDescription(String.valueOf(position));
        paidBlock.setContentDescription(String.valueOf(position));
        paidValue.setContentDescription(String.valueOf(position));
        closedBlock.setContentDescription(String.valueOf(position));
        generateBillet.setContentDescription(String.valueOf(position));
        fromToDate.setContentDescription(String.valueOf(position));
        closedBlock.findViewById(R.id.past_balance).setContentDescription(String.valueOf(position));
        closedBlock.findViewById(R.id.interest).setContentDescription(String.valueOf(position));
    }

}
