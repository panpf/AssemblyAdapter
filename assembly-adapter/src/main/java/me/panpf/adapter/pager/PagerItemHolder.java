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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * {@link AssemblyPagerAdapter} 专用的固定位置 item 管理器
 */
public class PagerItemHolder<DATA> {
    @NonNull
    private PagerItemStorage storage;
    @NonNull
    private AssemblyPagerItemFactory itemFactory;
    @Nullable
    private DATA data;
    private boolean header;

    private int position;
    private boolean enabled = true;

    public PagerItemHolder(@NonNull PagerItemStorage storage, @NonNull AssemblyPagerItemFactory itemFactory, @Nullable DATA data, boolean header) {
        this.storage = storage;
        this.itemFactory = itemFactory;
        this.data = data;
        this.header = header;
    }

    @Nullable
    public DATA getData() {
        return data;
    }

    public void setData(@Nullable DATA data) {
        this.data = data;

        AssemblyPagerAdapter adapter = itemFactory.getAdapter();
        if (adapter != null && adapter.isNotifyOnChange()) {
            adapter.notifyDataSetChanged();
        }
    }

    @NonNull
    public AssemblyPagerItemFactory getItemFactory() {
        return itemFactory;
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

    protected void enableChanged() {
        if (header) {
            storage.headerEnabledChanged(this);
        } else {
            storage.footerEnabledChanged(this);
        }
    }

    public int getPosition() {
        return position;
    }

    void setPosition(int position) {
        this.position = position;
    }

    public boolean isHeader() {
        return header;
    }
}
