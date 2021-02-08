# Threading - Beleske

Svaka promena na UI komponentama moze biti samo iz main (UI) niti!

UI API nije thread safe!

Ovo se moze resiti koriscenjem runOnUiThread() metode.

### Kako pokrenuti novu nit

	new Thread(new Runnable() {
	    @Override
	    public void run() {
	        // posao koji treva da se odradi
	    }
	}).start();

Ili koriscenjem lambda funkcija:
	new Thread( () -> {
        //posao koji treba da se odradi
    }).start();


### Kako na bezbedan nacin promeniti neku UI komponentu

* Koriscenjem runOnUiThread() metode iz aktivnosti

		mainActivity.runOnUiThread( () -> binding.addNewUser.setText("THE PROPER WAY") );

* Koriscenjem post() metode iz viewa

		binding.addNewUser.post( () -> binding.addNewUser.setText("THE PROPER WAY 2") );
