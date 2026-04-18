package com.zybooks.cs_360_project.viewmodel;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;

import com.zybooks.cs_360_project.model.Item;
import com.zybooks.cs_360_project.repo.InventoryRepository;
import com.zybooks.cs_360_project.view.AlertsActivity;

import java.util.List;

public class ItemListViewModel {
    private InventoryRepository invRepo;
    private Context mContext;

    public ItemListViewModel(Application application) {
        invRepo = InventoryRepository.getInstance(application.getApplicationContext());
        mContext = application.getApplicationContext();
    }

    public List<Item> getItemList(long inventoryId) {
        return invRepo.getItemList(inventoryId);
    }

    public void addItem(Item item) {
        invRepo.addItem(item);
    }
    public void deleteItem(long itemId) {
        invRepo.deleteItem(itemId);
    }

    public void updateItem(Item item) {
        invRepo.updateItem(item);

        if (item.getQuantity() <= 0) {
            checkAndSendSmsAlert(item.getName());
        }
    }

    private void checkAndSendSmsAlert(String itemName) {
        SharedPreferences prefs = mContext.getSharedPreferences(AlertsActivity.PREFS_NAME, Context.MODE_PRIVATE);
        boolean smsAlertEnabled = prefs.getBoolean(AlertsActivity.SMS_ALERT_KEY, false);
        String phoneNumber = prefs.getString(AlertsActivity.KEY_PHONE_NUMBER, "");
        if (smsAlertEnabled && !phoneNumber.isEmpty() && mContext.checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, "Item " + itemName + " is out of stock!", null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
