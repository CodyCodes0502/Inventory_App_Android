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

public class InventoryDialogFragment  extends DialogFragment {

    public interface OnInventoryEnteredListener {
        void onInventoryEntered(String inventoryName);
    }

    private OnInventoryEnteredListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final EditText inventoryEditText = new EditText(requireActivity());
        inventoryEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        inventoryEditText.setMaxLines(1);

        return new AlertDialog.Builder(requireActivity())
                .setTitle(R.string.inventory)
                .setView(inventoryEditText)
                .setPositiveButton(R.string.create, (dialog, whichButton) -> {
                    String inventoryName = inventoryEditText.getText().toString();
                    mListener.onInventoryEntered(inventoryName.trim());
                })
                .setNegativeButton(R.string.cancel, null)
                .create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnInventoryEnteredListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
