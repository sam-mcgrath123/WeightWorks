package fragments.workout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import java.util.HashMap;
import java.util.Objects;

public class AddExerciseFragment extends DialogFragment {

    //TODO add constants for secondDialogOptions

    private String selectedOption;
    private HashMap<Integer, String[]> secondDialogOptions = new HashMap<>();
    View mainDialog;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        secondDialogOptions.put(0, new String[]{"Chest", "Arms", "Legs"});
        secondDialogOptions.put(1, new String[]{"Barbell", "Dumbbell", "Machine/Other"});
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder = initializeAlertBuilder(builder, inflater);


        return builder.create();
    }

    private AlertDialog.Builder initializeAlertBuilder(AlertDialog.Builder builder, LayoutInflater inflater) {
        builder.setView(inflater.inflate(R.layout.fragment_add_exercise, (ViewGroup) getView(), false))
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO update exercises
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Objects.requireNonNull(AddExerciseFragment.this.getDialog()).cancel();
                    }
                });
        mainDialog = inflater.inflate(R.layout.fragment_add_exercise, null, false);

        CardView bodyPartCard = mainDialog.findViewById(R.id.add_exercise_popup_body_part);
        bodyPartCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondDialog(bodyPartCard, 0);
            }
        });
        CardView categoryCard = mainDialog.findViewById(R.id.add_exercise_popup_category);
        categoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSecondDialog(categoryCard, 1);
            }
        });
        builder.setView(mainDialog);
        return builder;
    }

    private void showSecondDialog(CardView secondaryDialogView, int optionsKey) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        String title = optionsKey == 0 ? "Body Part" : "Category";
        builder.setTitle(title);
        builder.setCancelable(true);
        selectedOption = "";

        builder.setSingleChoiceItems(secondDialogOptions.get(optionsKey), -1, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedOption = secondDialogOptions.get(optionsKey)[which].toString();
            }
        });

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView value = secondaryDialogView.findViewById(R.id.add_exercise_input);
                value.setText(selectedOption);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}