package fragments;

import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;

public class UserFragment extends Fragment {

    ImageView userSettings;
    Fragment metricFragment;
    Fragment nutritionFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        for (Fragment fragment : getChildFragmentManager().getFragments()) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }
        userSettings = view.findViewById(R.id.user_settings_icon);
        userSettings.setOnClickListener(userSettingsListener);
        TabLayout tabLayout = view.findViewById(R.id.user_tab_layout);
        tabLayout.addOnTabSelectedListener(tabSelectedListener);
        metricFragment = new MetricSectionFragment();
        nutritionFragment = new NutritionSectionFragment();
        TabLayout.Tab metricTab = tabLayout.getTabAt(0);
        tabSelectedListener.onTabSelected(metricTab);
    }


    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition()) {
                case (0):
                    displaySection(metricFragment, nutritionFragment);
                    break;
                case (1):
                    displaySection(nutritionFragment, metricFragment);
                    break;
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
        }
    };

    private void displaySection(Fragment show, Fragment hide) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (show.isAdded())
            fragmentTransaction.show(show);
        else
            fragmentTransaction.add(R.id.user_fragment_container, show, show.getTag());
        if (hide.isAdded())
            fragmentTransaction.hide(hide);
        fragmentTransaction.commit();
    }

    private View.OnClickListener userSettingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_profile_settings);
        }
    };
}