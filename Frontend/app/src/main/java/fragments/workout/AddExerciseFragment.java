package fragments.workout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.Objects;

public class AddExerciseFragment extends DialogFragment {

    private String selectedPart;
    private ArrayList<Integer> partList = new ArrayList<>();
    private ArrayList<String> partArrayList = new ArrayList<>();
    private String[] bodyParts = new String[]{"Chest", "Arms", "Legs"};
    ArrayList<String> selectedBodyParts = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
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
        View view = inflater.inflate(R.layout.fragment_add_exercise, null, false);
        EditText editText = view.findViewById(R.id.add_exercise_name_text);
        editText.setHint("WOW");

        CardView cardView = view.findViewById(R.id.add_exercise_popup_body_part);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Body Part");
                builder.setCancelable(true);
                selectedPart = "";

                builder.setSingleChoiceItems(bodyParts, 1, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedPart = bodyParts[which].toString();
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0; j < partList.size(); j++) {
                            stringBuilder.append(bodyParts[partList.get(j)]);
                            selectedBodyParts.add(bodyParts[partList.get(j)]);
                            if (j != partList.size() - 1) {
                                stringBuilder.append(", ");
                            }
                        }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
//                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        for (int j = 0; j < selectedParts.length; j++) {
//                            selectedParts[j] = false;
//                            partList.clear();
//                        }
//                    }
//                });
                builder.show();
            }
        });
        builder.setView(view);
        return builder;
    }
}