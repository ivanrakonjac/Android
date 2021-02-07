# Local database app - Beleske

Android ima SQLite bazu podataka.

Serverless je (ne podize se zaseban proces (server), vec je citava baza 1 fajl).

Tim fajlom barata bibliotecki kod.

https://developer.android.com/reference/android/database/package-summary

https://developer.android.com/reference/android/database/sqlite/package-summary

Preporuceno je da se koriste Room ORM, iako SQLite model nije deprecated.

Dokumentacija: 

https://developer.android.com/training/data-storage/room

https://developer.android.com/reference/androidx/room/package-summary



### Kako koristiti:

1)

* definisati zavisnosti
* Dependencies za Room: https://developer.android.com/jetpack/androidx/releases/room

2)

* Definisati 3 najbitnije stavke (Entitete (Tabele), DataAccessObjekte (DAO), Bazu )

3)

#### Definisanje Baze:

* @Database(entities = {User.class}, version = 1, exportSchema = false)
	- entities - koji sve entiteti ulaze u bazu
	- version - zbog migracija
	- exportSchema - za eksportovanje seme baze

* Definisanje public abstract metode za svaki DAO 
	- public abstract UserDao userDao();  

4)

* Ako u entitetima koristimo tipove podataka koje Room ne podrzava (npr. Data iz java.util paketa), Room ce se buniti
* To se resava tako sto se napise konverter tog tipa podatka u neki tip koji Room podrzava
* A da bi baza znala koje sve konvertere ima potrebno je setovati

		@TypeConverters(value = {DateConverter.class})

5)

Napraviti bazu:

	Room.databaseBuilder((MainActivity)requireActivity(), UserDatabase.class, "user-app.db").build();

* (MainActivity)requireActivity() - context
* UserDatabase.class - koju bazu pravimo
* user-app.db - ime baze

6)

Kako proveriti da li je baza napravljen:

* DeviceFileExplorer (desni donji ugao)
* data/data/dirSaPaketomNaseApp/databases
* Baza ce biti napravljena tek kada se nesto u nju doda


### Dobre fore pri koriscenju

#### Dohvatanje svih elemenata iz neke tabele

U UserDao interfejsu:

	@Query("SELECT * FROM User")
    List<User> getAll ();

A dohvatanje bi ovako izgledalo:

	List<User> userList = userDatabase.userDao().getAll();


Ali mnogo je bolje definisati nesto ovako u UserDao interfejsu:

	@Query("SELECT * FROM User")
    LiveData<List<User>> getAllLiveData ();

Ovde kazemo: Vrati mi listu Usera, tj. vrati mi LiveData sa listom Usera i menjaj tu listu ako se rezultat SELECT * FROM User promeni.

Room je sam svestan LiveData tipa i sam ce pozvati setList, kada se vrednost promeni.

Mnogo pametnije!

Plus mozemo da observujemo taj live data, pa na promenu vrednosti da menjamo user interface.




### Kako napraviti Dynamic lists sa RecyclerView-om

https://developer.android.com/jetpack/androidx/releases/recyclerview

https://developer.android.com/guide/topics/ui/layout/recyclerview

https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example

Postupak:

1)

* Dodati dependecije

2)

* Dodaj RecyclerView u xml layout (aktivnost ili fragment).

		<androidx.recyclerview.widget.RecyclerView
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content" />

3)

* Napraviti Adapter klasu koja u sebi sadrzi ViewHolder klasu.

* ViewHolder je klasa koja predstavlja 1 red u listi.

* Adapter upravlja listom pomocu overridovanih metoda.

4)

* U fragmentu/aktivnosti gde je RecyclerView treba narpaviti Adapter.

* Setovati mu listu sa podacima.

* Dodati adapter na RecyclerView i setovati setLayoutManager.

#### Primer sa dohvatanjem liste iz baze:

	UserDatabase userDatabase = UserDatabase.getInstance((MainActivity) getActivity());
	List<User> userList = userDatabase.userDao().getAll();
      																							
	UserAdapter userAdapter = new UserAdapter();
	userAdapter.setUserList(userList);
																								
	binding.recyclerView.setAdapter(userAdapter);
	binding.recyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));