package clertonleal.com.nubank.entity;

import clertonleal.com.nubank.R;

public enum BillStatus {

    overdue(R.color.green, R.drawable.triangle_green),
    closed(R.color.red, R.drawable.triangle_red),
    open(R.color.blue, R.drawable.triangle_blue),
    future(R.color.orange, R.drawable.triangle_orange);

    private int color;
    private int arrow;

    BillStatus(int color, int arrow) {
        this.color = color;
        this.arrow = arrow;
    }

    public int getColor() {
        return color;
    }

    public int getArrow() {
        return arrow;
    }
}
