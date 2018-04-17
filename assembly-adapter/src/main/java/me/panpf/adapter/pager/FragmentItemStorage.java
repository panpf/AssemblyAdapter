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

package me.panpf.adapter.pager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import me.panpf.adapter.ItemStorage;

@SuppressWarnings({"unused", "WeakerAccess"})
public class FragmentItemStorage {

    @NonNull
    private PagerAdapter adapter;

    @Nullable
    private List dataList;

    private boolean itemFactoryLocked;
    @Nullable
    private ArrayList<FixedFragmentItemInfo> headerItemList;
    @Nullable
    private ArrayList<FixedFragmentItemInfo> footerItemList;
    @Nullable
    private ArrayList<AssemblyFragmentItemFactory> itemFactoryList;

    public FragmentItemStorage(@NonNull PagerAdapter adapter) {
        this.adapter = adapter;
    }

    public FragmentItemStorage(@NonNull PagerAdapter adapter, @NonNull List dataList) {
        this.adapter = adapter;
        this.dataList = dataList;
    }

    public FragmentItemStorage(@NonNull PagerAdapter adapter, @Nullable Object[] dataArray) {
        this.adapter = adapter;
        if (dataArray != null && dataArray.length > 0) {
            this.dataList = new ArrayList(dataArray.length);
            Collections.addAll(dataList, dataArray);
        }
    }


    /* ************************ 数据 ItemFactory *************************** */

    public void addItemFactory(@NonNull AssemblyFragmentItemFactory itemFactory) {
        //noinspection ConstantConditions
        if (itemFactory == null || itemFactoryLocked) {
            throw new IllegalArgumentException("itemFactory is null or item factory list locked");
        }

        itemFactory.setAdapter(adapter);

        if (itemFactoryList == null) {
            itemFactoryList = new ArrayList<AssemblyFragmentItemFactory>(2);
        }
        itemFactoryList.add(itemFactory);
    }

    /**
     * 获取 {@link me.panpf.adapter.ItemFactory} 列表
     */
    @Nullable
    public List<AssemblyFragmentItemFactory> getItemFactoryList() {
        return itemFactoryList;
    }

    /**
     * 获取 {@link AssemblyFragmentItemFactory} 的个数
     */
    public int getItemFactoryCount() {
        return itemFactoryList != null ? itemFactoryList.size() : 0;
    }


    /* ************************ 头部 ItemFactory *************************** */

    /**
     * 添加一个将按添加顺序显示在列表头部的 {@link AssemblyFragmentItemFactory}
     */
    public void addHeaderItem(@NonNull AssemblyFragmentItemFactory itemFactory, @NonNull Object data) {
        //noinspection ConstantConditions
        if (itemFactory == null || itemFactoryLocked) {
            throw new IllegalArgumentException("itemFactory is null or item factory list locked");
        }

        //noinspection ConstantConditions
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }

        itemFactory.setAdapter(adapter);

        if (headerItemList == null) {
            headerItemList = new ArrayList<FixedFragmentItemInfo>(1);
        }
        headerItemList.add(new FixedFragmentItemInfo(itemFactory, data));
    }

    /**
     * 添加一个将按添加顺序显示在列表头部的 {@link AssemblyFragmentItemFactory}
     */
    public void addHeaderItem(@NonNull AssemblyFragmentItemFactory itemFactory) {
        addHeaderItem(itemFactory, ItemStorage.NONE_DATA);
    }

    /**
     * 获取 header 列表
     */
    @Nullable
    public List<FixedFragmentItemInfo> getHeaderItemList() {
        return headerItemList;
    }

    /**
     * 获取列表头的个数
     */
    public int getHeaderItemCount() {
        return headerItemList != null ? headerItemList.size() : 0;
    }

    @Nullable
    public Object getHeaderData(int positionInHeaderList) {
        return headerItemList != null ? headerItemList.get(positionInHeaderList).getData() : null;
    }


    /* ************************ 尾巴 ItemFactory *************************** */

    /**
     * 添加一个将按添加顺序显示在列表尾部的 {@link AssemblyFragmentItemFactory}
     */
    public void addFooterItem(@NonNull AssemblyFragmentItemFactory itemFactory, @NonNull Object data) {
        //noinspection ConstantConditions
        if (itemFactory == null || itemFactoryLocked) {
            throw new IllegalArgumentException("itemFactory is null or item factory list locked");
        }

        //noinspection ConstantConditions
        if (data == null) {
            throw new IllegalArgumentException("data is null");
        }

        itemFactory.setAdapter(adapter);

        if (footerItemList == null) {
            footerItemList = new ArrayList<FixedFragmentItemInfo>(1);
        }
        footerItemList.add(new FixedFragmentItemInfo(itemFactory, data));
    }

    /**
     * 添加一个将按添加顺序显示在列表尾部的 {@link AssemblyFragmentItemFactory}
     */
    public void addFooterItem(@NonNull AssemblyFragmentItemFactory itemFactory) {
        addFooterItem(itemFactory, ItemStorage.NONE_DATA);
    }

    /**
     * 获取 footer 列表
     */
    @Nullable
    public List<FixedFragmentItemInfo> getFooterItemList() {
        return footerItemList;
    }

    /**
     * 获取列表头的个数
     */
    public int getFooterItemCount() {
        return footerItemList != null ? footerItemList.size() : 0;
    }

    @Nullable
    public Object getFooterData(int positionInFooterList) {
        return footerItemList != null ? footerItemList.get(positionInFooterList).getData() : null;
    }


    /* ************************ 数据列表 *************************** */

    /**
     * 获取数据列表
     */
    @Nullable
    public List getDataList() {
        return dataList;
    }

    /**
     * 获取数据列表的长度
     */
    public int getDataCount() {
        return dataList != null ? dataList.size() : 0;
    }

    @Nullable
    public Object getData(int positionInDataList) {
        return dataList != null ? dataList.get(positionInDataList) : null;
    }
}