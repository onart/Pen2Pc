package onart.pack.pen2pc;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogYN#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogYN extends DialogFragment {

    private int content, y, n;

    public DialogYN() {
        // Required empty public constructor
    }

    public DialogYN(int content,int y, int n){
        this.content=content;
        this.y=y;
        this.n=n;
    }

    public static DialogYN newInstance(String param1, String param2) {
        DialogYN fragment = new DialogYN();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(content)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    if(y!=0) getActivity().findViewById(y).callOnClick();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> {
                    if(n!=0) getActivity().findViewById(n).callOnClick();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}