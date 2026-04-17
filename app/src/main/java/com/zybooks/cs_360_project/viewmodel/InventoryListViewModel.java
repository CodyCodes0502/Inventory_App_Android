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

    public List<Inventory> getInventoryList() {
        return invRepo.getInventoryList();
    }

    public void addInventory(Inventory inventory) {
        invRepo.addInventory(inventory);
    }

    public void deleteInventory(long inventoryId) {
        invRepo.deleteInventory(inventoryId);
    }
}
