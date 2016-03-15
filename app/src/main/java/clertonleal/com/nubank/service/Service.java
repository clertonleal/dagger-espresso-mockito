package clertonleal.com.nubank.service;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import clertonleal.com.nubank.entity.Bill;
import clertonleal.com.nubank.entity.BillWrapper;
import clertonleal.com.nubank.network.Network;
import rx.Observable;

public class Service {

    private Network network;

    @Inject
    public Service(Network network) {
        this.network = network;
    }

    public Observable<List<Bill>> retrieveBills() {
        return network.retrieveBills().map(this::unwrapBill);
    }

    private List<Bill> unwrapBill(List<BillWrapper> billWrappers) {
        List<Bill> bills = new ArrayList<>();
        for (BillWrapper billWrapper: billWrappers) {
            bills.add(billWrapper.getBill());
        }

        return bills;
    }
}


