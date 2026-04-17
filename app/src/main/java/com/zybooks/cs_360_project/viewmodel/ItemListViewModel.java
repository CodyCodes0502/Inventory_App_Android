package com.zybooks.cs_360_project.viewmodel;

import android.app.Application;

import com.zybooks.cs_360_project.model.Item;
import com.zybooks.cs_360_project.repo.InventoryRepository;

import java.util.List;

public class ItemListViewModel {
    private InventoryRepository invRepo;

    public ItemListViewModel(Application application) {
        invRepo = InventoryRepository.getInstance(application.getApplicationContext());
    }

    public List<Item> getItemList(long inventoryId) {
        return invRepo.getItemList(inventoryId);
    }

    public void addItem(Item item) {
        invRepo.addItem(item);
    }


}
