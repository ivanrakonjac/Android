package ika.narucivanje.proizvodi;

import android.content.res.Resources;

import ika.narucivanje.R;

public class Proizvod {

    private final String ime;

    public Proizvod(String ime) {
        this.ime = ime;
    }

    public static Proizvod createFromResources(Resources resources, int index){
        String[] proizvodi = resources.getStringArray(R.array.route_names);
        return new Proizvod(proizvodi[index]);
    }

    public String getIme() {
        return ime;
    }
}
