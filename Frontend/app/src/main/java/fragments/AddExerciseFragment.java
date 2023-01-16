package fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.example.weightworks.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class AddExerciseFragment extends DialogFragment {

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
                Toast.makeText(getContext(), "Worked", Toast.LENGTH_LONG).show();
            }
        });
        builder.setView(view);
        return builder;
    }
}