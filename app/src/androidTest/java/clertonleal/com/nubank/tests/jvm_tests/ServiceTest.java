package clertonleal.com.nubank.tests.jvm_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import javax.inject.Inject;

import clertonleal.com.nubank.application.TestApplication;
import clertonleal.com.nubank.dagger.TestComponent;
import clertonleal.com.nubank.entity.Bill;
import clertonleal.com.nubank.entity.BillStatus;
import clertonleal.com.nubank.entity.LineItem;
import clertonleal.com.nubank.entity.Summary;
import clertonleal.com.nubank.service.Service;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

@RunWith(JUnit4.class)
public class ServiceTest {

    @Inject
    Service service;

    public ServiceTest() {
        ((TestComponent) TestApplication.getComponent()).inject(this);
    }

    @Test
    public void serviceShouldReturnFourBills() {
        service.retrieveBills().subscribe(bills -> assertEquals(bills.size(), 4),
                                          throwable -> fail("Error to retrieve bills"));
    }

    @Test
    public void serviceShouldReturnOneOverdueBill() {
        service.retrieveBills().subscribe(bills -> assertEquals(getNumberOfBills(bills, BillStatus.overdue), 1),
                                          throwable -> fail("Error to retrieve bills"));
    }

    @Test
    public void serviceShouldReturnOneClosedBill() {
        service.retrieveBills().subscribe(bills -> assertEquals(getNumberOfBills(bills, BillStatus.closed), 1),
                                          throwable -> fail("Error to retrieve bills"));
    }

    @Test
    public void serviceShouldReturnOneOpenBill() {
        service.retrieveBills().subscribe(bills -> assertEquals(getNumberOfBills(bills, BillStatus.open), 1),
                                          throwable -> fail("Error to retrieve bills"));
    }

    @Test
    public void serviceShouldReturnOneFutureBill() {
        service.retrieveBills().subscribe(bills -> assertEquals(getNumberOfBills(bills, BillStatus.future), 1),
                                          throwable -> fail("Error to retrieve bills"));
    }

    @Test
    public void serviceShouldSerializeABill() {
        service.retrieveBills().subscribe(bills -> {
            Bill bill = bills.get(0);
            assertNotNull(bill.getBarcode());
            assertNotNull(bill.getId());
            assertNotNull(bill.getLineItems());
            assertNotNull(bill.getLinhaDigitavel());
            assertNotNull(bill.getState());
            assertNotNull(bill.getSummary());
            Summary summary = bill.getSummary();

            assertNotNull(summary.getCloseDate());
            assertNotNull(summary.getDueDate());
            assertNotNull(summary.getInterest());
            assertNotNull(summary.getMinimumPayment());
            assertNotNull(summary.getOpenDate());
            assertNotNull(summary.getPaid());
            assertNotNull(summary.getPastBalance());
            assertNotNull(summary.getTotalBalance());
            assertNotNull(summary.getTotalCumulative());

            LineItem lineItem = bill.getLineItems().get(0);

            assertNotNull(lineItem.getAmount());
            assertNotNull(lineItem.getCharges());
            assertNotNull(lineItem.getHref());
            assertNotNull(lineItem.getIndex());
            assertNotNull(lineItem.getPostDate());
            assertNotNull(lineItem.getTitle());
        }, throwable -> fail("Error to retrieve bills"));
    }

    private int getNumberOfBills(List<Bill> bills, BillStatus billStatus) {
        int count = 0;
        for (Bill bill: bills) {
            if (bill.getState() == billStatus) {
                count++;
            }
        }

        return count;
    }

}
