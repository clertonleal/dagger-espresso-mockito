package clertonleal.com.nubank.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import clertonleal.com.nubank.R;
import clertonleal.com.nubank.adapter.holder.LineItemHolder;
import clertonleal.com.nubank.entity.LineItem;
import clertonleal.com.nubank.util.FormatUtil;

public class LineItemAdapter extends android.support.v7.widget.RecyclerView.Adapter<LineItemHolder> {

    @Inject
    Resources resources;

    @Inject
    Context context;

    @Inject
    LayoutInflater layoutInflater;

    private List<LineItem> lineItems = new ArrayList<>();

    @Inject
    public LineItemAdapter() {}

    @Override
    public LineItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LineItemHolder(layoutInflater.inflate(R.layout.row_line_item, parent, false));
    }

    @Override
    public void onBindViewHolder(LineItemHolder holder, int position) {
        holder.lineItem = lineItems.get(position);
        holder.lineItemDate.setText(FormatUtil.getShortDate(holder.lineItem.getPostDate()));
        holder.lineItemDescription.setText(holder.lineItem.getTitle());
        holder.lineItemValue.setText(FormatUtil.getShortMonetaryValue(holder.lineItem.getAmount()));
        if (holder.lineItem.getAmount() < 0) {
            holder.lineItemDescription.setTextColor(resources.getColor(R.color.green));
            holder.lineItemValue.setTextColor(resources.getColor(R.color.green));
        } else {
            holder.lineItemDescription.setTextColor(resources.getColor(R.color.main_text_color));
            holder.lineItemValue.setTextColor(resources.getColor(R.color.main_text_color));
        }
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lineItems.size();
    }

    public void clear() {
        this.lineItems.clear();
    }

}
