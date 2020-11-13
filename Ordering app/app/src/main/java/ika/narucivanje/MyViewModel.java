package ika.narucivanje;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Date;


public class MyViewModel extends ViewModel {

    /**
     * Sluzi nam da nam pokaze da li je model prazan ili ne
     * Da znamo da li da vrsimo cuvanje u Bundle ili ne
     */
    private boolean modelHasData = false;

    public void setModelHasData(boolean modelHasData) {
        this.modelHasData = modelHasData;
    }

    public boolean isModelHasData() {
        return modelHasData;
    }

    /*
    * MutableLiveData je samo potklasa apstraktne klase LiveData
    * Koristimo je da ne bismo morali da definisemo sve metode LiveData klase
     */
    private final MutableLiveData<String> ime = new MutableLiveData<>();
    private final MutableLiveData<String> prezime = new MutableLiveData<>();
    private final MutableLiveData<String> adresa = new MutableLiveData<>();
    private final MutableLiveData<String> grad = new MutableLiveData<>();
    private final MutableLiveData<Integer> postanskiBroj = new MutableLiveData<>();
    private final MutableLiveData<Integer> kolicina = new MutableLiveData<>();
    private final MutableLiveData<String> vrsta = new MutableLiveData<>();
    private final MutableLiveData<String> telefon = new MutableLiveData<>();
    private final MutableLiveData<Date> lastChange = new MutableLiveData<>();

    public LiveData<Date> getLastChange() {
        return lastChange;
    }

    public LiveData<String> getIme() {
        return ime;
    }

    public LiveData<String> getPrezime() {
        return prezime;
    }

    public LiveData<String> getAdresa() {
        return adresa;
    }

    public LiveData<String> getGrad() {
        return grad;
    }

    public LiveData<Integer> getPostanskiBroj() {
        return postanskiBroj;
    }

    public LiveData<Integer> getKolicina() {
        return kolicina;
    }

    public LiveData<String> getVrsta() {
        return vrsta;
    }

    public LiveData<String> getTelefon() {
        return telefon;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateValues(String ime, String prezime, String adresa, String grad, int postanskiBroj, int kolicina, String vrsta, String telefon){

        this.modelHasData = true;

        this.ime.setValue(ime);
        this.prezime.setValue(prezime);
        this.adresa.setValue(adresa);
        this.grad.setValue(grad);
        this.postanskiBroj.setValue(postanskiBroj);
        this.kolicina.setValue(kolicina);
        this.vrsta.setValue(vrsta);
        this.telefon.setValue(telefon);


        /**
         * Kada su updejtovane sve vrednosti upisujem trenutno vreme kao vrednost
         * Da bi trigerovao observer za promenu texta u controlleru (MainActivity)
         */
        this.lastChange.setValue(new Date());
    }


    /**
     * provera !modelHasData gleda ima li smisla traziti podatke u Bundle objektu
     * jer ako nije bilo podataka u modelu, nema smisla traziti ih u bundle objektu
     */
    public void initByBundle(Bundle bundle){
        if(bundle != null){
           if(!modelHasData){
               if(bundle.containsKey("IME_KEY")){
                   ime.setValue(bundle.getString("IME_KEY"));
               }
               if(bundle.containsKey("PREZIME_KEY")){
                   prezime.setValue(bundle.getString("PREZIME_KEY"));
               }
               if(bundle.containsKey("ADRESA_KEY")){
                   adresa.setValue(bundle.getString("ADRESA_KEY"));
               }
               if(bundle.containsKey("GRAD_KEY")){
                   grad.setValue(bundle.getString("GRAD_KEY"));
               }
               if(bundle.containsKey("POSTANSKI_BR_KEY")){
                   postanskiBroj.setValue(bundle.getInt("POSTANSKI_BR_KEY"));
               }
               if(bundle.containsKey("KOLICINA_KEY")){
                   kolicina.setValue(bundle.getInt("KOLICINA_KEY"));
               }
               if(bundle.containsKey("VRSTA_KEY")){
                   vrsta.setValue(bundle.getString("VRSTA_KEY"));
               }
               if(bundle.containsKey("TELEFON_KEY")){
                   telefon.setValue(bundle.getString("TELEFON_KEY"));
               }
           }
        }
    }


}
