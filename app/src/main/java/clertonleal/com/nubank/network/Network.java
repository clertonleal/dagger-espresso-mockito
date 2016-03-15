package clertonleal.com.nubank.network;


import java.util.List;

import clertonleal.com.nubank.entity.BillWrapper;
import retrofit.http.GET;
import rx.Observable;

public interface Network {

    @GET("/mobile-challenge/bill/bill_new.json")
    Observable<List<BillWrapper>> retrieveBills();

}
