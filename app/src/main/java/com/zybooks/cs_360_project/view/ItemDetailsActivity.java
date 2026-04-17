package com.zybooks.cs_360_project.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zybooks.cs_360_project.ItemEditFragment;
import com.zybooks.cs_360_project.R;
import com.zybooks.cs_360_project.viewmodel.ItemListViewModel;

public class ItemDetailsActivity extends AppCompatActivity implements ItemEditFragment.OnItemEditedListener {

    public static final String EXTRA_ITEM_ID = "com.zybooks.cs_360_project.item_id";
    public static final String EXTRA_ITEM_NAME = "com.zybooks.cs_360_project.item_name";
    public static final String EXTRA_ITEM_QUANTITY = "com.zybooks.cs_360_project.item_quantity";
    public static final String EXTRA_ITEM_CREATED = "com.zybooks.cs_360_project.item_created";
    public static final String EXTRA_ITEM_UPDATED = "com.zybooks.cs_360_project.item_updated";

    private ItemListViewModel mItemListViewModel;
    private long mItemId;

    private TextView itemNameTextView;
    private TextView itemQuantityTextView;
    private TextView itemCreatedTextView;
    private TextView itemUpdatedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        mItemListViewModel = new ItemListViewModel(getApplication());
        mItemId = getIntent().getLongExtra(EXTRA_ITEM_ID, 0);

        itemNameTextView = findViewById(R.id.item_name_text_view);
        itemQuantityTextView = findViewById(R.id.item_quantity_text_view);
        itemCreatedTextView = findViewById(R.id.item_created_text_view);
        itemUpdatedTextView = findViewById(R.id.item_updated_text_view);
        View itemEditButton = findViewById(R.id.item_edit_button);
        View itemDeleteButton = findViewById(R.id.item_delete_button);

        loadItemDetails();

        itemEditButton.setOnClickListener(view -> onItemEditClick());
        itemDeleteButton.setOnClickListener(view -> onItemDeleteClick());
    }

    private void loadItemDetails() {
        com.zybooks.cs_360_project.model.Item item = com.zybooks.cs_360_project.repo.InventoryRepository.getInstance(this).getItem(mItemId);
        if (item != null) {
            itemNameTextView.setText(item.getName());
            itemQuantityTextView.setText(getString(R.string.item_quantity, item.getQuantity()));
            itemCreatedTextView.setText(getString(R.string.item_created, item.getCreatedTime()));
            itemUpdatedTextView.setText(getString(R.string.item_updated, item.getUpdateTime()));
        }
    }

    @Override
    public void onItemEdited() {
        finish();
    }

    public void onItemEditClick() {
        com.zybooks.cs_360_project.model.Item item = com.zybooks.cs_360_project.repo.InventoryRepository.getInstance(this).getItem(mItemId);
        if (item != null) {
            ItemEditFragment fragment = ItemEditFragment.newInstance(
                    item.getId(),
                    item.getName(),
                    item.getQuantity(),
                    item.getInventoryId(),
                    item.getCreatedTime(),
                    item.getUpdateTime()
            );
            fragment.show(getSupportFragmentManager(), "ItemEditFragment");
        }
    }

    public void onItemDeleteClick() {
        mItemListViewModel.deleteItem(mItemId);
        finish();
    }
}
