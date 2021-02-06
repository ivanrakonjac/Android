# Multiple backstack app Beleske

Da bi imasli vise backstackova neko treba da ih cuva.

To moze da radi aktivnost ili fragment (nav host fragment).

NavHostFragment ima svoje child fragmente i on vodi racuna o njima kroz svoj child fragmentManager.
Ne baratamo mi eksplicitno fragmentManagerom.

Ideja:
- programskim putem napraviti vise nav host fragmenata
- svaki ce imati svoj backstack
- svaki ce imati nav host controller
- menjati ih (nav host fragmente) klikom na ikonicu u meniju

Koraci:

1)

U main activity staviti 1 nav host container

	<androidx.fragment.app.FragmentContainerView
    android:id="@+id/nav_host_container"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    app:layout_constraintBottom_toTopOf="@+id/bottom_navigation_view"
    app:layout_constraintTop_toTopOf="parent" />

2)

Napraviti nav_graphova koliko imamo komponenti u meniju   
Podesiti putanje unutar grafova   
Setovati home fragmente   

3)

Podesiti idjeve itema u botoom navigaciji i nav_grafova da budu isti radi lakseg baratanja

