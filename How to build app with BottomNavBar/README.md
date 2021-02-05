# Beleske

Steps:

1)
Add dependencies
How to know which? -> https://developer.android.com/jetpack/androidx/releases/navigation

2)
Add icons for menu, create bottom_nav_menu.xlm and add it to main_activity


3)
Add a NavHost to an activity

4)
Dodati nav_graph

Svaki NavHost fragment ima 1 NavController koji regulise navigaciju.
Ako dohvatimo taj NavController, mozemo navigirati medju fragmentima.

	navigationCtrl = navHostFragment.getNavController();

Postoji vise nacina kako preci sa 1 fragmenta na 2:

1) Pomocu destinacije (id fragmenta na koji treba preci) - nije dobra praksa

	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()){
		    case R.id.nav_home:
		        // Dodaje se novi fragment (R.id.homeFragment) na back stack
		        navigationCtrl.navigate(R.id.homeFragment);
		        Toast.makeText(getBaseContext(), "HOME", Toast.LENGTH_SHORT).show();
		        break;
	}

2) Pomocu akcija

Da bi se koristile potrebno ih je dodati u nav_graphu

	Tip akcije:
	-Obicna izmedju fragmenata (sa 1 na 2)
	-Globalna (dostupna sa vise fragmenata, tj. sa vise fragmenata mozemo preci na taj neki destinacioni)
	-Back to destination (vracamo se na fragment odakle smo dosli)
