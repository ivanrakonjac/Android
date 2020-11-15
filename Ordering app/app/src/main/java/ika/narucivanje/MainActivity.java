package ika.narucivanje;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import ika.narucivanje.databinding.ActivityMainBinding;
import ika.narucivanje.proizvodi.ProizvodiActivity;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "activity-lifecycle";

    private ActivityMainBinding activityMainBinding;
    private  MyViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLifecycle().addObserver(new MyLifeCycleAwareComponent());

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        /*
        * Dohvatamo LiveData objekat quantity koji je svestan Life-Cyclea
        * Kacimo observer na njega, i prosledjujemo zivotni ciklus objekta koji ga posmatra (observuje)
        * Nakon toga definisemo sta se desava onChanged
         */
        myViewModel.getLastChange().observe(this, new Observer<Date>() {
            @Override
            public void onChanged(Date datum) {
                activityMainBinding.textView.setText("Naruceno: \n" +
                        "Ime:\t" + myViewModel.getIme().getValue() + "\n" +
                        "Prezime:\t" + myViewModel.getPrezime().getValue() + "\n" +
                        "Adresa:\t" + myViewModel.getAdresa().getValue() + "\n" +
                        "Grad:\t" + myViewModel.getGrad().getValue() + "\n" +
                        "Postanski broj:\t" + myViewModel.getPostanskiBroj().getValue() + "\n" +
                        "Kolicina:\t" + myViewModel.getKolicina().getValue() + "\n" +
                        "Vrsta:\t" + myViewModel.getVrsta().getValue() + "\n" +
                        "Telefon:\t" + myViewModel.getTelefon().getValue() + "\n" +
                        "Vreme:\t" + myViewModel.getLastChange().getValue());
            }
        });

        activityMainBinding.predjiDalje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), ProizvodiActivity.class);
                startActivity(intent);
            }
        });

        activityMainBinding.button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                String ime = "";
                try{
                    ime = activityMainBinding.imeEditText.getText().toString();
                    if(ime.equals("")) throw new NullPointerException();
                }catch (NullPointerException npe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.imeEditText.requestFocus();
                    return;
                }

                String prezime = "";
                try{
                    prezime = activityMainBinding.prezimeEditText.getText().toString();
                    if(prezime.equals("")) throw new NullPointerException();
                }catch (NullPointerException npe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.prezimeEditText.requestFocus();
                    return;
                }

                String adresa = "";
                try{
                    adresa = activityMainBinding.adresaEditText.getText().toString();
                    if(adresa.equals("")) throw new NullPointerException();
                }catch (NullPointerException npe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.adresaEditText.requestFocus();
                    return;
                }

                String grad = "";
                try{
                    grad = activityMainBinding.gradEditText.getText().toString();
                    if(grad.equals("")) throw new NullPointerException();
                }catch (NullPointerException npe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.gradEditText.requestFocus();
                    return;
                }

                int postanskiBroj = 0;
                try{
                    postanskiBroj = Integer.parseInt(activityMainBinding.postanskiBrojEditText.getText().toString());
                }catch (NumberFormatException nfe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.postanskiBrojEditText.requestFocus();
                    return;
                }

                int kolicina = 0;
                try{
                    kolicina = Integer.parseInt(activityMainBinding.kolicinaEditText.getText().toString());
                }catch (NumberFormatException nfe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.kolicinaEditText.requestFocus();
                    return;
                }

                String vrstaProizvoda = "";
                try{
                    vrstaProizvoda = activityMainBinding.vrsta.getText().toString();
                    if(vrstaProizvoda.equals("")) throw new NullPointerException();
                }catch (NullPointerException npe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.vrsta.requestFocus();
                    return;
                }

                String telefon = "";
                try{
                    telefon = activityMainBinding.telefonEditText.getText().toString();
                    if(telefon.equals("")) throw new NullPointerException();
                }catch (NullPointerException npe){
                    Toast.makeText(view.getContext(),R.string.error_message, Toast.LENGTH_SHORT).show();
                    activityMainBinding.telefonEditText.requestFocus();
                    return;
                }

                /**
                 * Ako su sva polja popunjena mogu se updejtovati vrednosti
                 */
                myViewModel.updateValues(ime,prezime,adresa,grad,postanskiBroj,kolicina,vrstaProizvoda,telefon);

            }
        });

        /**
         * Dohvatam niz iz resursa
         * Pravim od njega adapter sa layoutom iz Resursa
         * Na meni u activity_main.xml setujem napravljeni adapter
         */
        String[] vrsteProizvoda = getResources().getStringArray(R.array.vrsta_proizvoda);
        ArrayAdapter<String> vrsteProizvodaAdapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, vrsteProizvoda);
        activityMainBinding.vrsta.setAdapter(vrsteProizvodaAdapter);

    }

    /*
    * Poziva se pre nego sto onDestory() moze biti pozvan
    * Ovu metodu treba iskoristiti za cuvanje svih neophodnih podataka koje ne bismo smeli izgubiti ubijanjem nase Aktivnosti (i Modela)
    * Bundle objekat ce preziveti to ubijanje pa podatke cuvamo u njemu
    * */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if(myViewModel.isModelHasData() == true){
            outState.putString("IME_KEY",myViewModel.getIme().getValue());
            outState.putString("PREZIME_KEY",myViewModel.getPrezime().getValue());
            outState.putString("ADRESA_KEY",myViewModel.getAdresa().getValue());
            outState.putString("GRAD_KEY",myViewModel.getGrad().getValue());
            outState.putInt("POSTANSKI_BR_KEY",myViewModel.getPostanskiBroj().getValue());
            outState.putInt("KOLICINA_KEY",myViewModel.getKolicina().getValue());
            outState.putString("VRSTA_KEY",myViewModel.getVrsta().getValue());
            outState.putString("TELEFON_KEY",myViewModel.getTelefon().getValue());
        }

        Log.d(LOG_TAG, "onSaveInstanceState() called");
    }
}