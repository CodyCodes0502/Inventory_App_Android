package com.zybooks.cs_360_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zybooks.cs_360_project.model.Inventory;
import com.zybooks.cs_360_project.model.Item;
import com.zybooks.cs_360_project.viewmodel.ItemListViewModel;

import java.util.List;

public class Items_Activity extends AppCompatActivity
            implements ItemDialogFragment.OnItemEnteredListener {

    public static final String EXTRA_INVENTORY_ID = "com.zybooks.cs_360_project.inventory_id";
    public static final String EXTRA_INVENTORY_NAME = "com.zybooks.cs_360_project.inventory_name";

    private ItemListViewModel mItemListViewModel;
    private Inventory mInventory;
    private List<Item> mItemList;
    private int[] mItemColors;
    private RecyclerView mRecyclerView;
    private ItemAdapter mItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        mItemListViewModel = new ItemListViewModel(getApplication());

        String inventoryName = getIntent().getStringExtra(EXTRA_INVENTORY_NAME);
        if (inventoryName != null) {
            setTitle(inventoryName);
        }

        mItemColors = getResources().getIntArray(R.array.InventoryColors);

        findViewById(R.id.add_item_button).setOnClickListener(view -> addItemClick());

        mRecyclerView = findViewById(R.id.item_recycler_view);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        updateUI(mItemListViewModel.getItemList(getIntent().getLongExtra(EXTRA_INVENTORY_ID, 0)));

    }

    private void updateUI(List<Item> itemList) {
        if (itemList == null) {
            itemList = new java.util.ArrayList<>();
        }
        mItemAdapter = new ItemAdapter(itemList);
        mRecyclerView.setAdapter(mItemAdapter);
        updateAppBarTitle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            addItemClick();
            return true;
        }
        else if (item.getItemId() == R.id.alerts) {
            Intent intent = new Intent(this, AlertsActivity.class);
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }

    private void updateAppBarTitle() {
        String inventoryName = getIntent().getStringExtra(EXTRA_INVENTORY_NAME);
        if (inventoryName != null) {
            setTitle(inventoryName);
        }
    }


    @Override
    public void onItemEntered(String itemName, int quantity) {
        if(!itemName.isEmpty()) {
            long inventoryId = getIntent().getLongExtra(EXTRA_INVENTORY_ID, 0);
            Item item = new Item(itemName, quantity);
            item.setInventoryId(inventoryId);
            mItemListViewModel.addItem(item);

            updateUI(mItemListViewModel.getItemList(inventoryId));

            Toast.makeText(this, "Added " + itemName, Toast.LENGTH_SHORT).show();
        }
    }

    private void addItemClick() {
        ItemDialogFragment dialog = new ItemDialogFragment();
        dialog.show(getSupportFragmentManager(), "ItemDialog");
    }

    private class ItemHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {
        private Item mItem;
        private final TextView mItemTextView;
        private final TextView mItemInventoryTextView;
        private final View mItemLayout;

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.recycler_view_items, parent, false));
            itemView.setOnClickListener(this);
            mItemTextView = itemView.findViewById(R.id.item_text_view);
            mItemInventoryTextView = itemView.findViewById(R.id.item_inventory_text_view);
            mItemLayout = itemView.findViewById(R.id.item_layout);
        }

        public void bind(Item item, int position) {
            mItem = item;
            mItemTextView.setText(mItem.getName());
            mItemInventoryTextView.setText(getString(R.string.item_quantity, mItem.getQuantity()));

            int colorIndex = item.getName().length() % mItemColors.length;
            mItemLayout.setBackgroundColor(mItemColors[colorIndex]);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Items_Activity.this, ItemDetailsActivity.class);

            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        private final List<Item> mItemList;

        public ItemAdapter (List<Item> itemList) {
            mItemList = itemList;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            holder.bind(mItemList.get(position), position);
        }

        @Override
        public int getItemCount() {
            return mItemList.size();
        }
    }

}