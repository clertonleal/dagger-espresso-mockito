package clertonleal.com.nubank.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Summary implements Serializable {

    @SerializedName("due_date")
    private Date dueDate;

    @SerializedName("close_date")
    private Date closeDate;

    @SerializedName("past_balance")
    private Long pastBalance;

    @SerializedName("total_balance")
    private Long totalBalance;

    private Long interest;

    @SerializedName("total_cumulative")
    private Long totalCumulative;

    private Long paid;

    @SerializedName("minimum_payment")
    private Long minimumPayment;

    @SerializedName("open_date")
    private Date openDate;

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Long getPastBalance() {
        return pastBalance;
    }

    public void setPastBalance(Long pastBalance) {
        this.pastBalance = pastBalance;
    }

    public Long getInterest() {
        return interest;
    }

    public void setInterest(Long interest) {
        this.interest = interest;
    }

    public Long getTotalCumulative() {
        return totalCumulative;
    }

    public void setTotalCumulative(Long totalCumulative) {
        this.totalCumulative = totalCumulative;
    }

    public Long getPaid() {
        return paid;
    }

    public void setPaid(Long paid) {
        this.paid = paid;
    }

    public Long getMinimumPayment() {
        return minimumPayment;
    }

    public void setMinimumPayment(Long minimumPayment) {
        this.minimumPayment = minimumPayment;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public Long getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Long totalBalance) {
        this.totalBalance = totalBalance;
    }
}
