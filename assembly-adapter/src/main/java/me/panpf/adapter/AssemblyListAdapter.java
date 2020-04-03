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

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import me.panpf.adapter.more.MoreItemFactory;
import me.panpf.adapter.more.MoreItemHolder;

/**
 * 通用组合式 {@link BaseAdapter}，支持组合式多类型 item，支持头、尾巴以及加载更多
 */
public class AssemblyListAdapter extends BaseAdapter implements AssemblyAdapter {

    @NonNull
    private ItemStorage storage;

    public AssemblyListAdapter() {
        this.storage = new ItemStorage(this);
    }

    public AssemblyListAdapter(@Nullable List dataList) {
        this.storage = new ItemStorage(this, dataList);
    }

    public AssemblyListAdapter(@Nullable Object[] dataArray) {
        this.storage = new ItemStorage(this, dataArray);
    }


    /* ************************ 数据 ItemFactory *************************** */

    @Override
    public <DATA> void addItemFactory(@NonNull ItemFactory<DATA> itemFactory) {
        storage.addItemFactory(itemFactory);
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
        return storage.addHeaderItem(itemFactory, data);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addHeaderItem(@NonNull ItemFactory<DATA> itemFactory) {
        return storage.addHeaderItem(itemFactory);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addHeaderItem(@NonNull ItemHolder<DATA> itemHolder) {
        return storage.addHeaderItem(itemHolder);
    }

    @Nullable
    @Override
    public List<ItemHolder> getHeaderItemList() {
        return storage.getHeaderItemHolderManager().getItemList();
    }

    @Override
    public int getHeaderItemCount() {
        return storage.getHeaderItemCount();
    }

    @Nullable
    @Override
    public Object getHeaderData(int positionInHeaderList) {
        return storage.getHeaderData(positionInHeaderList);
    }


    /* ************************ 尾巴 ItemFactory *************************** */

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemFactory<DATA> itemFactory, @Nullable DATA data) {
        return storage.addFooterItem(itemFactory, data);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemFactory<DATA> itemFactory) {
        return storage.addFooterItem(itemFactory);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemHolder<DATA> itemHolder) {
        return storage.addFooterItem(itemHolder);
    }

    @Nullable
    @Override
    public List<ItemHolder> getFooterItemList() {
        return storage.getFooterItemHolderManager().getItemList();
    }

    @Override
    public int getFooterItemCount() {
        return storage.getFooterItemCount();
    }

    @Nullable
    @Override
    public Object getFooterData(int positionInFooterList) {
        return storage.getFooterData(positionInFooterList);
    }


    /* ************************ 加载更多 *************************** */

    @NonNull
    @Override
    public <DATA> MoreItemHolder<DATA> setMoreItem(@NonNull MoreItemFactory<DATA> itemFactory, @Nullable DATA data) {
        return storage.setMoreItem(itemFactory, data);
    }

    @NonNull
    @Override
    public <DATA> MoreItemHolder<DATA> setMoreItem(@NonNull MoreItemFactory<DATA> itemFactory) {
        return storage.setMoreItem(itemFactory);
    }

    @NonNull
    @Override
    public <DATA> MoreItemHolder<DATA> setMoreItem(@NonNull MoreItemHolder<DATA> itemHolder) {
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
        return storage.getItemCount();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return storage.getItem(position);
    }

    @Override
    public int getPositionInPart(int position) {
        return storage.getPositionInPart(position);
    }

    @Override
    public int getCount() {
        return storage.getItemCount();
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
        ItemFactory itemFactory = storage.getItemFactoryByPosition(position);
        return itemFactory != null ? itemFactory.getSpanSize() : 1;
    }

    @Nullable
    @Override
    public ItemFactory getItemFactoryByViewType(int viewType) {
        return storage.getItemFactoryByViewType(viewType);
    }

    @Nullable
    @Override
    public ItemFactory getItemFactoryByPosition(int position) {
        return storage.getItemFactoryByPosition(position);
    }

    @Override
    public boolean isHeaderItem(int position) {
        return storage.isHeaderItem(position);
    }

    @Override
    public boolean isBodyItem(int position) {
        return storage.isBodyItem(position);
    }

    @Override
    public boolean isFooterItem(int position) {
        return storage.isFooterItem(position);
    }

    @Override
    public boolean isMoreFooterItem(int position) {
        return storage.isMoreFooterItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return storage.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        ItemFactory itemFactory = storage.getItemFactoryByPosition(position);
        if (itemFactory != null) {
            return itemFactory.getViewType();
        } else {
            throw new IllegalStateException("Not found viewType by position: " + position);
        }
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Item item;
        if (convertView == null) {
            item = createItem(parent, getItemViewType(position));
            convertView = item.getItemView();
            convertView.setTag(item);
        } else {
            item = (Item) convertView.getTag();
        }
        bindItem(item, position);
        return convertView;
    }

    @NonNull
    private Item createItem(@NonNull ViewGroup parent, int viewType) {
        @Nullable
        ItemFactory itemFactory = storage.getItemFactoryByViewType(viewType);
        if (itemFactory != null) {
            return itemFactory.dispatchCreateItem(parent);
        } else {
            throw new IllegalStateException(String.format("Not found ItemFactory by viewType: %d", viewType));
        }
    }

    private void bindItem(@NonNull Item item, int position) {
        Object itemObject = getItem(position);
        //noinspection unchecked
        item.setData(position, itemObject);
    }
}
