package me.panpf.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FixedItem<DATA> {

    @Nullable
    private ItemManager itemManager;
    @NonNull
    private ItemFactory<DATA> itemFactory;
    @Nullable
    private DATA data;
    private boolean header;

    private boolean enabled = true;

    public FixedItem(@NonNull ItemFactory<DATA> itemFactory, @Nullable DATA data) {
        this.itemFactory = itemFactory;
        this.data = data;
    }

    public FixedItem(@NonNull ItemFactory<DATA> itemFactory) {
        this.itemFactory = itemFactory;
    }

    @Nullable
    public DATA getData() {
        return data;
    }

    public void setData(@Nullable DATA data) {
        this.data = data;

        AssemblyAdapter adapter = itemFactory.getAdapter();
        if (adapter != null && adapter.isNotifyOnChange()) {
            adapter.notifyDataSetChanged();
        }
    }

    @NonNull
    public ItemFactory<DATA> getItemFactory() {
        return itemFactory;
    }

    public boolean isAttached() {
        return itemManager != null;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled == enabled) {
            return;
        }
        this.enabled = enabled;
        enableChanged();
    }

    void attachToAdapter(@NonNull ItemManager itemManager, boolean header) {
        this.itemManager = itemManager;
        this.header = header;
    }

    protected void enableChanged() {
        if (itemManager != null) {
            itemManager.fixedItemEnabledChanged(this);
        }
    }

    public boolean isHeader() {
        return header;
    }
}
