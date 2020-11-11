package ika.narucivanje;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {

    private boolean quantityValid = false;

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
        quantityValid = true;
    }

    public void initByBundle(Bundle bundle){
        if(bundle != null){
           if(!quantityValid){
               if(bundle.containsKey("QUANTITY_KEY")){
                   quantity.setValue(bundle.getInt("QUANTITY_KEY"));
               }
           }
        }
    }


}
