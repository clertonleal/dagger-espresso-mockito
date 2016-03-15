package clertonleal.com.nubank.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import clertonleal.com.nubank.R;
import clertonleal.com.nubank.entity.LineItem;

public class LineItemHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.line_item_date)
    public TextView lineItemDate;

    @InjectView(R.id.line_item_description)
    public TextView lineItemDescription;

    @InjectView(R.id.line_item_value)
    public TextView lineItemValue;


    public LineItem lineItem;

    public LineItemHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

}
