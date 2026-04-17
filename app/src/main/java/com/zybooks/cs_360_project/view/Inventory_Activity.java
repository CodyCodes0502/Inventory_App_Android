package com.zybooks.cs_360_project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.cs_360_project.InventoryDialogFragment;
import com.zybooks.cs_360_project.R;
import com.zybooks.cs_360_project.model.Inventory;
import com.zybooks.cs_360_project.viewmodel.InventoryListViewModel;

import java.util.List;


public class Inventory_Activity extends AppCompatActivity
            implements InventoryDialogFragment.OnInventoryEnteredListener {

    private InventoryAdapter mInventoryAdapter;
    private RecyclerView mRecyclerView;
    private int[] mInventoryColors;
    private InventoryListViewModel mInventoryListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        mInventoryListViewModel = new InventoryListViewModel(getApplication());

        mInventoryColors = getResources().getIntArray(R.array.InventoryColors);

        findViewById(R.id.add_inventory_button).setOnClickListener(view -> addInventoryClick());

        mRecyclerView = findViewById(R.id.inventory_recycler_view);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        updateUI(mInventoryListViewModel.getInventoryList());
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI(mInventoryListViewModel.getInventoryList());
    }

    private void updateUI(List<Inventory> inventoryList) {
        mInventoryAdapter = new InventoryAdapter(inventoryList);
        mRecyclerView.setAdapter(mInventoryAdapter);
        setTitle(R.string.app_name);
    }

    @Override
    public void onInventoryEntered(String inventoryName) {
        if(!inventoryName.isEmpty()) {
            Inventory inventory = new Inventory(inventoryName);
            mInventoryListViewModel.addInventory(inventory);
            updateUI(mInventoryListViewModel.getInventoryList());

            Toast.makeText(this, "Added " + inventoryName, Toast.LENGTH_SHORT).show();
        }
    }

    private void addInventoryClick() {
        InventoryDialogFragment dialog = new InventoryDialogFragment();
        dialog.show(getSupportFragmentManager(), "InventoryDialogFragment");
    }

    private class InventoryHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
        private Inventory mInventory;
        private final TextView mInventoryTextView;

        public InventoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recycler_view_inventory, parent, false));
            itemView.setOnClickListener(this);
            mInventoryTextView = itemView.findViewById(R.id.inventory_text_view);
        }

        public void bind(Inventory inventory, int position) {
            mInventory = inventory;
            mInventoryTextView.setText(mInventory.getName());

            int colorIndex = inventory.getName().length() % mInventoryColors.length;
            mInventoryTextView.setBackgroundColor(mInventoryColors[colorIndex]);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Inventory_Activity.this, Items_Activity.class);
            intent.putExtra(Items_Activity.EXTRA_INVENTORY_ID, mInventory.getId());
            intent.putExtra(Items_Activity.EXTRA_INVENTORY_NAME, mInventory.getName());

            startActivity(intent);
        }
    }

    private class InventoryAdapter extends RecyclerView.Adapter<InventoryHolder> {
        private final List<Inventory> mInventoryList;

        public InventoryAdapter (List<Inventory> inventoryList) {
            mInventoryList = inventoryList;
        }

        @NonNull
        @Override
        public InventoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(Inventory_Activity.this);
            return new InventoryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull InventoryHolder holder, int position) {
            holder.bind(mInventoryList.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mInventoryList.size();
        }

    }



}