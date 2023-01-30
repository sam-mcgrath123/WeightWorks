package fragments.profile;

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
    Fragment goalFragment;

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
        getChildFragmentManager().beginTransaction()
                .replace(R.id.user_fragment_container, new MetricSectionFragment())
                .commit();
    }

    private View.OnClickListener userSettingsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Navigation.findNavController(v).navigate(R.id.action_profile_settings);
        }
    };
}