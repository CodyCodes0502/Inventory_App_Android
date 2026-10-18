package com.zybooks.cs_360_project.viewmodel;

import android.app.Application;

import com.zybooks.cs_360_project.model.Inventory;
import com.zybooks.cs_360_project.repo.InventoryRepository;

import java.util.List;

public class InventoryListViewModel {
    private InventoryRepository invRepo;

    public InventoryListViewModel(Application application) {
        invRepo = InventoryRepository.getInstance(application.getApplicationContext());
    }

    public List<Inventory> getInventoryList(long userId) {
        return invRepo.getInventoryList(userId);
    }

    public void addInventory(Inventory inventory) {
        invRepo.addInventory(inventory);
    }

    public void deleteInventory(long inventoryId, long userId) {
        invRepo.deleteInventory(inventoryId, userId);
    }
}
