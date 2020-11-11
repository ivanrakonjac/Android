package ika.narucivanje;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    /*
    * MutableLiveData je samo potklasa apstraktne klase LiveData
    * Koristimo je da ne bismo morali da definisemo sve metode LiveData klase
     */
    private final MutableLiveData<Integer> quantity = new MutableLiveData<>();

    public MutableLiveData<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(int value) {
        quantity.setValue(value);
    }


}
