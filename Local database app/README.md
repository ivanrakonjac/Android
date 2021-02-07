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

Definisanje Baze:

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