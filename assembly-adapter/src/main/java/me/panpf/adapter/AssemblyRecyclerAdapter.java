/*
 * Copyright (C) 2017 Peng fei Pan <sky@panpf.me>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.panpf.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import me.panpf.adapter.more.MoreItemFactory;
import me.panpf.adapter.more.MoreItemHolder;
import me.panpf.adapter.recycler.RecyclerItemWrapper;

/**
 * 通用组合式 {@link RecyclerView.Adapter}，支持组合式多类型 item，支持头、尾巴以及加载更多
 */
public class AssemblyRecyclerAdapter extends RecyclerView.Adapter implements AssemblyAdapter {

    @NonNull
    private ItemStorage storage;
    @NonNull
    private ItemActor actor = new ItemActor(this);

    public AssemblyRecyclerAdapter() {
        this.storage = new ItemStorage(this);
    }

    public AssemblyRecyclerAdapter(@Nullable List dataList) {
        this.storage = new ItemStorage(this, dataList);
    }

    public AssemblyRecyclerAdapter(@Nullable Object[] dataArray) {
        this.storage = new ItemStorage(this, dataArray);
    }


    /* ************************ 数据 ItemFactory *************************** */

    @Override
    public <DATA> void addItemFactory(@NonNull ItemFactory<DATA> itemFactory) {
        storage.addItemFactory(itemFactory.setInRecycler(true));
    }

    @Nullable
    @Override
    public List<ItemFactory> getItemFactoryList() {
        return storage.getItemFactoryList();
    }

    @Override
    public int getItemFactoryCount() {
        return storage.getItemFactoryCount();
    }


    /* ************************ 头部 ItemFactory *************************** */

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addHeaderItem(@NonNull ItemFactory<DATA> itemFactory, @Nullable DATA data) {
        return storage.addHeaderItem(itemFactory.setInRecycler(true), data);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addHeaderItem(@NonNull ItemFactory<DATA> itemFactory) {
        return storage.addHeaderItem(itemFactory.setInRecycler(true));
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addHeaderItem(@NonNull ItemHolder<DATA> itemHolder) {
        itemHolder.getItemFactory().setInRecycler(true);
        return storage.addHeaderItem(itemHolder);
    }

    @NonNull
    @Override
    public ItemHolderManager getHeaderItemHolderManager() {
        return storage.getHeaderItemHolderManager();
    }

    /**
     * @deprecated Use {@link #getHeaderItemHolderManager()} instead
     */
    @Nullable
    @Override
    @Deprecated
    public List<ItemHolder> getHeaderItemList() {
        return storage.getHeaderItemHolderManager().getItemList();
    }

    @Override
    public int getHeaderItemCount() {
        return storage.getHeaderItemHolderManager().getItemCount();
    }

    @Nullable
    @Override
    public Object getHeaderData(int positionInHeaderList) {
        return storage.getHeaderItemHolderManager().getItemData(positionInHeaderList);
    }


    /* ************************ 尾巴 ItemFactory *************************** */

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemFactory<DATA> itemFactory, @Nullable DATA data) {
        return storage.addFooterItem(itemFactory.setInRecycler(true), data);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemFactory<DATA> itemFactory) {
        return storage.addFooterItem(itemFactory.setInRecycler(true));
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemHolder<DATA> itemHolder) {
        itemHolder.getItemFactory().setInRecycler(true);
        return storage.addFooterItem(itemHolder);
    }

    @NonNull
    @Override
    public ItemHolderManager getFooterItemHolderManager() {
        return storage.getFooterItemHolderManager();
    }

    /**
     * @deprecated Use {@link #getFooterItemHolderManager()} instead
     */
    @Nullable
    @Override
    @Deprecated
    public List<ItemHolder> getFooterItemList() {
        return storage.getFooterItemHolderManager().getItemList();
    }

    @Override
    public int getFooterItemCount() {
        return storage.getFooterItemHolderManager().getItemCount();
    }

    @Nullable
    @Override
    public Object getFooterData(int positionInFooterList) {
        return storage.getFooterItemHolderManager().getItemData(positionInFooterList);
    }


    /* ************************ 加载更多 *************************** */

    @NonNull
    @Override
    public <DATA> MoreItemHolder<DATA> setMoreItem(@NonNull MoreItemFactory<DATA> itemFactory, @Nullable DATA data) {
        return storage.setMoreItem(itemFactory.setInRecycler(true), data);
    }

    @NonNull
    @Override
    public <DATA> MoreItemHolder<DATA> setMoreItem(@NonNull MoreItemFactory<DATA> itemFactory) {
        return storage.setMoreItem(itemFactory.setInRecycler(true));
    }

    @NonNull
    @Override
    public <DATA> MoreItemHolder<DATA> setMoreItem(@NonNull MoreItemHolder<DATA> itemHolder) {
        itemHolder.getItemFactory().setInRecycler(true);
        return storage.setMoreItem(itemHolder);
    }

    @Nullable
    @Override
    public MoreItemHolder getMoreItemHolder() {
        return storage.getMoreItemHolder();
    }

    @Override
    public boolean hasMoreFooter() {
        return storage.hasMoreFooter();
    }

    @Override
    public void setEnabledMoreItem(boolean enabledMoreItem) {
        storage.setEnabledMoreItem(enabledMoreItem);
    }

    @Override
    public void loadMoreFinished(boolean loadMoreEnd) {
        storage.loadMoreFinished(loadMoreEnd);
    }

    @Override
    public void loadMoreFailed() {
        storage.loadMoreFailed();
    }


    /* ************************ 数据列表 *************************** */

    @Nullable
    @Override
    public List getDataList() {
        return storage.getDataList();
    }

    @Override
    public void setDataList(@Nullable List dataList) {
        storage.setDataList(dataList);
    }

    @Override
    public void addAll(@Nullable Collection collection) {
        storage.addAll(collection);
    }

    @Override
    public void addAll(@Nullable Object... items) {
        storage.addAll(items);
    }

    @Override
    public void insert(@NonNull Object object, int index) {
        storage.insert(object, index);
    }

    @Override
    public void remove(@NonNull Object object) {
        storage.remove(object);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void sort(@NonNull Comparator comparator) {
        storage.sort(comparator);
    }

    @Override
    public int getDataCount() {
        return storage.getDataCount();
    }

    @Nullable
    @Override
    public Object getData(int positionInDataList) {
        return storage.getData(positionInDataList);
    }

    /* ************************ 完整列表 *************************** */

    @Override
    public int getItemCount() {
        return actor.getItemCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return actor.getItem(position);
    }

    @Override
    public int getPositionInPart(int position) {
        return actor.getPositionInPart(position);
    }


    /* ************************ 其它 *************************** */

    @Override
    public boolean isNotifyOnChange() {
        return storage.isNotifyOnChange();
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        storage.setNotifyOnChange(notifyOnChange);
    }

    @Override
    public int getSpanSize(int position) {
        return actor.getSpanSize(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return actor.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @Nullable
        Object itemObject = storage.getItemFactoryByViewType(viewType);
        if (itemObject instanceof ItemFactory) {
            ItemFactory itemFactory = (ItemFactory) itemObject;
            Item item = itemFactory.dispatchCreateItem(parent);
            if (item instanceof RecyclerItemWrapper) {
                return (RecyclerItemWrapper) item;
            } else {
                throw new IllegalStateException(String.format("Item not RecyclerItemWrapper. itemFactory: %s", itemFactory.toString()));
            }
        } else if (itemObject instanceof ItemHolder) {
            ItemFactory itemFactory = ((ItemHolder) itemObject).getItemFactory();
            Item item = itemFactory.dispatchCreateItem(parent);
            if (item instanceof RecyclerItemWrapper) {
                return (RecyclerItemWrapper) item;
            } else {
                throw new IllegalStateException(String.format("Item not RecyclerItemWrapper. itemFactory: %s", itemFactory.toString()));
            }
        } else {
            throw new IllegalStateException(String.format("Unknown viewType: %d, itemFactory: %s", viewType, itemObject != null ? itemObject.toString() : "null"));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof Item) {
            Object itemData = getItem(position);
            //noinspection unchecked
            ((Item) viewHolder).setData(position, itemData);
        }
    }
}
