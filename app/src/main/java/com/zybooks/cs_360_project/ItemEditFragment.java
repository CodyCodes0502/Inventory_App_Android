package com.zybooks.cs_360_project;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.zybooks.cs_360_project.model.Item;
import com.zybooks.cs_360_project.view.Items_Activity;
import com.zybooks.cs_360_project.viewmodel.ItemListViewModel;


public class ItemEditFragment extends DialogFragment {

    private static final String ARG_ITEM_ID = "item_id";
    private static final String ARG_ITEM_NAME = "item_name";
    private static final String ARG_ITEM_QUANTITY = "item_quantity";
    private static final String ARG_ITEM_CREATED = "item_created";
    private static final String ARG_ITEM_UPDATED = "item_updated";
    private static final String ARG_ITEM_INV_ID = "item_inv_id";

    private EditText mItemNameEditText;
    private EditText mItemQuantityEditText;
    private ItemListViewModel mViewModel;

    private long mItemId;
    private long mInventoryId;
    private long mCreatedTime;

    public interface OnItemEditedListener {
        void onItemEdited();
    }

    public static ItemEditFragment newInstance(long itemId, String itemName, long itemQuantity, long itemInventoryId, long itemCreated, long itemUpdated) {
        ItemEditFragment fragment = new ItemEditFragment();
        Bundle args = new Bundle();

        args.putLong(ARG_ITEM_ID, itemId);
        args.putString(ARG_ITEM_NAME, itemName);
        args.putLong(ARG_ITEM_QUANTITY, itemQuantity);
        args.putLong(ARG_ITEM_INV_ID, itemInventoryId);
        args.putLong(ARG_ITEM_CREATED, itemCreated);
        args.putLong(ARG_ITEM_UPDATED, itemUpdated);

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_item_edit, container, false);

        mItemNameEditText = view.findViewById(R.id.edit_item_name);
        mItemQuantityEditText = view.findViewById(R.id.edit_item_quantity);
        Button saveButton = view.findViewById(R.id.button_save);
        Button cancelButton = view.findViewById(R.id.button_cancel);

        mViewModel = new ItemListViewModel(requireActivity().getApplication());

        if (getArguments() != null) {
            mItemId = getArguments().getLong(ARG_ITEM_ID);
            mInventoryId = getArguments().getLong(ARG_ITEM_INV_ID);
            mCreatedTime = getArguments().getLong(ARG_ITEM_CREATED);
            mItemNameEditText.setText(getArguments().getString(ARG_ITEM_NAME));
            mItemQuantityEditText.setText(String.valueOf(getArguments().getLong(ARG_ITEM_QUANTITY)));
        }

        saveButton.setOnClickListener(v -> {
            String newName = mItemNameEditText.getText().toString();
            long newQuantity = Long.parseLong(mItemQuantityEditText.getText().toString());

            Item updatedItem = new Item(mItemId, newName, newQuantity, mInventoryId, mCreatedTime, System.currentTimeMillis());
            mViewModel.updateItem(updatedItem);


            if (getActivity() instanceof OnItemEditedListener) {
                ((OnItemEditedListener) getActivity()).onItemEdited();
            }
            dismiss();
        });

        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

}