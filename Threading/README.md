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


Stvaranje nove niti je skupa operacija!

Ideja je da se negde gde moze biti potrebno pozadinsko procesiranje, inicijalno kreira nit.

A onda joj se naknadno dostavljaju zadaci (Runnables) koje ona izvrsava.

Mozemo je sami napraviti a mozemo koristiti i Looper.

https://developer.android.com/reference/android/os/Looper 

Ili nesto skroz gotovo kao HandlerThread.

https://developer.android.com/reference/android/os/HandlerThread

UI thread je u stvari HandlerThread, i na svaku nasu akciju (npr pritisak dugmeta) dodaje se Runnable u UI thread, koji to posle izvrsava.

Sve android komponente se izvrsavaju na UI threadu.

#### Kako napraviti uiThreadHandler

	 Handler uiThreadHandler = new Handler(Looper.getMainLooper());

Pa mu onda mozemo proslediti neki posao:

	uiThreadHandler.post( () -> binding.addNewUser.setBackgroundColor(Color.RED) );
