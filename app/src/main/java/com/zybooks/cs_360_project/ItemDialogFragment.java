package com.zybooks.cs_360_project;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ItemDialogFragment extends DialogFragment {
    public interface OnItemEnteredListener {
        void onItemEntered(String itemName, int quantity);
    }

    private OnItemEnteredListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final EditText itemEditText = new EditText(requireActivity());
        itemEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        itemEditText.setMaxLines(1);

        return new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.item)
                .setView(itemEditText)
                .setPositiveButton(R.string.create, (dialog, whichButton) -> {
                    String itemName = itemEditText.getText().toString();
                    mListener.onItemEntered(itemName.trim(), 1);
                })
                .setNegativeButton(R.string.cancel, null)
                .create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnItemEnteredListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
