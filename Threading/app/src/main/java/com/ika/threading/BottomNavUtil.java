package com.ika.threading;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class BottomNavUtil {

    /**
     * Nama cilj da svaki menu item ima svoj backstack
     * Ova funkcija se poziva kako bi to obezbedila
     * @param navResourceIds
     */
    public static void  setup(BottomNavigationView bottomNavigationView, FragmentManager fragmentManager, int[] navResourceIds, int containerId) {

        Map<Integer, String> navGraphIdToTagMap = new HashMap<>();
        int homeNavGraphId = 0;

        for (int i = 0; i < navResourceIds.length ; i++) {

            String tag = "navHostFragment#" + i;

            NavHostFragment navHostFragment = obtainNavHostFragment(fragmentManager, tag, navResourceIds[i], containerId);

            // Dohvatamo id nav_grapha, da bi mogli da attachujemo/detachujemo navHostFragment
            int navGraphId = navHostFragment.getNavController().getGraph().getId();

            navGraphIdToTagMap.put(navGraphId, tag);

            // ovo nam sluzi da uhvatimo id homeNavGrapha, da bi posle izvukli homeTag
            if (i == 0) {
                homeNavGraphId = navGraphId;
            }

            if (bottomNavigationView.getSelectedItemId() == navGraphId){
                // za primarni se uzima prvi koji je prosledjen u nizu navResourceIds
                attachNavHostFragment(fragmentManager, navHostFragment, i==0);
            }else {
                detachNavHostFragment(fragmentManager,navHostFragment);
            }
        };

        // treba nam zbog fragmentManager.popBackStack(homeTag)
        String homeTag = navGraphIdToTagMap.get(homeNavGraphId);

        AtomicReference<Boolean> isOnHomeWrapper = new AtomicReference<>(
                homeNavGraphId == bottomNavigationView.getSelectedItemId()
        );

        // Tag backStacka na kome se trenutno nalazimo
        AtomicReference<String> currentTagWrapper =  new AtomicReference<>(navGraphIdToTagMap.get(bottomNavigationView.getSelectedItemId()));

        bottomNavigationView.setOnNavigationItemSelectedListener( menuItem -> {
            // provera da ne bi izvrsili nesto u momentu kada to nije pametno
            if (!fragmentManager.isStateSaved()){
                String dstTag = navGraphIdToTagMap.get(menuItem.getItemId());

                // ako smo na istom tagu nemamo sta da menjamo
                if(!dstTag.equals(currentTagWrapper.get())) {

                    // skini sve sa steka do ovog taga i ovaj tag
                    fragmentManager.popBackStack(homeTag, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    NavHostFragment homeNavHostFragment = (NavHostFragment) fragmentManager.findFragmentByTag(homeTag);
                    NavHostFragment destNavHostFragment = (NavHostFragment) fragmentManager.findFragmentByTag(dstTag);

                    if(!dstTag.equals(homeTag)){
                        fragmentManager.beginTransaction()
                                .detach(homeNavHostFragment)
                                .attach(destNavHostFragment)
                                .setPrimaryNavigationFragment(destNavHostFragment)
                                .addToBackStack(homeTag)
                                .setReorderingAllowed(true)
                                .commit();
                    }

                    currentTagWrapper.set(dstTag);
                    isOnHomeWrapper.set(dstTag.equals(homeTag));

                    return true;
                }

            }
            return false;
        });

        int finalHomeNavGraphId = homeNavGraphId;
        fragmentManager.addOnBackStackChangedListener( () -> {
            if (!isOnHomeWrapper.get() && !isOnBackStack(fragmentManager, homeTag)) {
                bottomNavigationView.setSelectedItemId(finalHomeNavGraphId);
            }
        });
    }

    /**
     * Metoda koja nam vraca navHostFragment ako postoji
     * Ili ga pravi, ako ne postoji i dodaje u fragmentManager
     *
     * @param fragmentManager  fragmentManager iz koga se dohvata ili u koga se postavlja navHostFragment
     * @param tag  tag sa kojim se navHostFragment smesta u fragmentManager
     * @param navGraphResId  nav_graph sa kojim se pravi newNavHostFragment
     * @param containerId  id u koji je dodaje newNavHostFragment
     * @return  NavHostFragment postojeci ili nov sa tagom tag
     */
    private static NavHostFragment obtainNavHostFragment(FragmentManager fragmentManager, String tag, int navGraphResId, int containerId) {

        NavHostFragment existingNavHostFragment = (NavHostFragment) fragmentManager.findFragmentByTag(tag);

        if(existingNavHostFragment != null) {
            return existingNavHostFragment;
        }

        NavHostFragment newNavHostFragment = NavHostFragment.create(navGraphResId);
        fragmentManager.beginTransaction().add(containerId, newNavHostFragment, tag).commitNow();

        return  newNavHostFragment;
    }

    /**
     * Metoda koja radi attach navHostFragmenta prosledjenog kroz parametar
     * Ovo radimo zato sto kada se fragmenti dodaju u fragmentManager, svi se vide, pa bi bili iscrtani jedni preko drugih
     * Ovim attachom jedan navHostFragment ostaje "upaljen", a ostale "gasimo"
     *
     * @param fragmentManager fragmentManager nad cijim fragmentima radimo attach
     * @param navHostFragment navHostFragment kog attachujemo
     * @param isPrimary boolean koji nam kaze da li ovaj navHostFragment treba da bude postavljen kao primarni (od njih vise)
     */
    private static void attachNavHostFragment (FragmentManager fragmentManager, NavHostFragment navHostFragment, boolean isPrimary) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.attach(navHostFragment);

        if(isPrimary){
            fragmentTransaction.setPrimaryNavigationFragment(navHostFragment);
        }

        fragmentTransaction.commitNow();
    }

    /**
     * Metoda koja detachuje prosledjeni navHostFragment u prosledjenom fragmentManageru
     *
     * @param fragmentManager
     * @param navHostFragment
     */
    private static void detachNavHostFragment (FragmentManager fragmentManager, NavHostFragment navHostFragment) {
        fragmentManager.beginTransaction().detach(navHostFragment).commitNow();
    }

    /**
     * Funkcija koja proverava da li je fragment backStackEntyName na backstacku fragmentManagera
     *
     * @param fragmentManager
     * @param backStackEntyName
     *
     * @return true - na steku je; false - nije na steku
     */
    private static boolean isOnBackStack (FragmentManager fragmentManager, String backStackEntyName){

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            if(fragmentManager.getBackStackEntryAt(i).getName().equals(backStackEntyName)) return true;
        }

        return false;

    }

}
