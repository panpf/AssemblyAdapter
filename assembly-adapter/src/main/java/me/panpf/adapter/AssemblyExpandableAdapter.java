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
import android.widget.BaseExpandableListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import me.panpf.adapter.expandable.ExpandableItemActor;
import me.panpf.adapter.expandable.ExpandableItemStorage;
import me.panpf.adapter.more.MoreItemFactory;
import me.panpf.adapter.more.MoreItemHolder;

/**
 * 通用组合式 {@link BaseExpandableListAdapter}，支持组合式多 item，支持头、尾巴以及加载更多
 */
public class AssemblyExpandableAdapter extends BaseExpandableListAdapter implements AssemblyAdapter {

    @NonNull
    private ExpandableItemStorage storage;
    @NonNull
    private ExpandableItemActor actor = new ExpandableItemActor(this);

    @Nullable
    private ExpandCallback expandCallback;

    public AssemblyExpandableAdapter() {
        this.storage = new ExpandableItemStorage(this);
    }

    public AssemblyExpandableAdapter(@Nullable List dataList) {
        this.storage = new ExpandableItemStorage(this, dataList);
    }

    public AssemblyExpandableAdapter(@Nullable Object[] dataArray) {
        this.storage = new ExpandableItemStorage(this, dataArray);
    }


    /* ************************ 数据 ItemFactory *************************** */

    @Override
    public <DATA> void addItemFactory(@NonNull ItemFactory<DATA> itemFactory) {
        storage.addItemFactory(itemFactory);
    }

    public <DATA> void addGroupItemFactory(@NonNull ItemFactory<DATA> groupItemFactory) {
        storage.addItemFactory(groupItemFactory);
    }

    public <DATA> void addChildItemFactory(@NonNull ItemFactory<DATA> childItemFactory) {
        storage.addChildItemFactory(childItemFactory);
    }

    @Nullable
    @Override
    public List<ItemFactory> getItemFactoryList() {
        return storage.getItemFactoryList();
    }

    /**
     * 获取 group {@link ItemFactory} 列表
     */
    @Nullable
    public List<ItemFactory> getGroupItemFactoryList() {
        return storage.getItemFactoryList();
    }

    /**
     * 获取 child {@link ItemFactory} 列表
     */
    @Nullable
    public List<ItemFactory> getChildItemFactoryList() {
        return storage.getChildItemFactoryList();
    }

    @Override
    public int getItemFactoryCount() {
        return storage.getItemFactoryCount();
    }

    public int getGroupItemFactoryCount() {
        return storage.getItemFactoryCount();
    }

    public int getChildItemFactoryCount() {
        return storage.getChildItemFactoryCount();
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
        return storage.addFooterItem(itemFactory, data);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemFactory<DATA> itemFactory) {
        return storage.addHeaderItem(itemFactory);
    }

    @NonNull
    @Override
    public <DATA> ItemHolder<DATA> addFooterItem(@NonNull ItemHolder<DATA> itemHolder) {
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
    public Object getFooterData(int positionInFooterList) {
        return storage.getFooterItemHolderManager().getItemData(positionInFooterList);
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

    @Nullable
    @Override
    public ItemFactory getItemFactoryByViewType(int viewType) {
        return storage.getItemFactoryByViewType(viewType);
    }

    @Override
    public int getGroupCount() {
        return actor.getItemCount();
    }

    @Nullable
    @Override
    public Object getGroup(int groupPosition) {
        return actor.getItem(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getGroupTypeCount() {
        return storage.getViewTypeCount();
    }

    @Override
    public int getGroupType(int groupPosition) {
        return actor.getItemViewType(groupPosition);
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return actor.getChildrenCount(groupPosition);
    }

    @Nullable
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return actor.getChild(groupPosition, childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildTypeCount() {
        return storage.getChildTypeCount();
    }

    @Override
    public int getChildType(int groupPosition, int childPosition) {
        return actor.getChildViewType(groupPosition, childPosition);
    }

    @Override
    public boolean hasStableIds() {
        return expandCallback != null && expandCallback.hasStableIds();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return expandCallback != null && expandCallback.isChildSelectable(groupPosition, childPosition);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Item groupItem;
        if (convertView == null) {
            groupItem = createGroupItem(parent, getGroupType(groupPosition));
            convertView = groupItem.getItemView();
            convertView.setTag(groupItem);
        } else {
            groupItem = (Item) convertView.getTag();
        }
        bindGroupItem(groupItem, isExpanded, groupPosition);
        return convertView;
    }

    @NonNull
    private Item createGroupItem(@NonNull ViewGroup parent, int viewType) {
        @Nullable
        ItemFactory itemFactory = storage.getItemFactoryByViewType(viewType);
        if (itemFactory != null) {
            return itemFactory.dispatchCreateItem(parent);
        } else {
            throw new IllegalStateException(String.format("Not found ItemFactory by groupViewType: %d", viewType));
        }
    }

    private void bindGroupItem(@NonNull Item groupItem, boolean isExpanded, int groupPosition) {
        Object group = getGroup(groupPosition);
        groupItem.setExpanded(isExpanded);
        //noinspection unchecked
        groupItem.setData(groupPosition, group);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, @Nullable View convertView, @NonNull ViewGroup parent) {
        Item childItem;
        if (convertView == null) {
            childItem = createChildItem(parent, getChildType(groupPosition, childPosition));
            convertView = childItem.getItemView();
            convertView.setTag(childItem);
        } else {
            childItem = (Item) convertView.getTag();
        }
        bindChildItem(childItem, groupPosition, childPosition, isLastChild);
        return convertView;
    }

    @NonNull
    private Item createChildItem(@NonNull ViewGroup parent, int viewType) {
        @Nullable
        Object itemObject = storage.getChildItemFactoryByViewType(viewType);
        if (itemObject instanceof ItemFactory) {
            ItemFactory itemFactory = (ItemFactory) itemObject;
            return itemFactory.dispatchCreateItem(parent);
        } else {
            throw new IllegalStateException(String.format("Unknown childViewType: %d, itemFactory: %s",
                    viewType, itemObject != null ? itemObject.getClass().getName() : "null"));
        }
    }

    private void bindChildItem(@NonNull Item childItem, int groupPosition, int childPosition, boolean isLastChild) {
        Object child = getChild(groupPosition, childPosition);
        childItem.setGroupPosition(groupPosition);
        childItem.setLastChild(isLastChild);
        //noinspection unchecked
        childItem.setData(childPosition, child);
    }

    /**
     * 设置扩展回调
     */
    public void setExpandCallback(@Nullable ExpandCallback expandCallback) {
        this.expandCallback = expandCallback;
    }

    public interface ExpandCallback {
        boolean hasStableIds();

        boolean isChildSelectable(int groupPosition, int childPosition);
    }
}
