package me.panpf.adapter.more;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.panpf.adapter.AssemblyAdapter;
import me.panpf.adapter.AssemblyItem;

public abstract class AssemblyMoreItem<DATA> extends AssemblyItem<DATA> implements MoreItem<DATA> {

    @NonNull
    private AssemblyMoreItemFactory itemFactory;

    public AssemblyMoreItem(@NonNull AssemblyMoreItemFactory itemFactory, int itemLayoutId, @NonNull ViewGroup parent) {
        super(itemLayoutId, parent);
        this.itemFactory = itemFactory;
        this.itemFactory.item = this;
    }

    public AssemblyMoreItem(@NonNull AssemblyMoreItemFactory itemFactory, @NonNull View convertView) {
        super(convertView);
        this.itemFactory = itemFactory;
        this.itemFactory.item = this;
    }

    @Override
    public void onConfigViews(@NonNull Context context) {
        View errorView = getErrorRetryView();
        //noinspection ConstantConditions
        if (errorView != null) {
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemFactory.listener != null) {
                        itemFactory.paused = false;
                        setData(getPosition(), getData());
                    }
                }
            });
        }
    }

    @Override
    public void onSetData(int position, @Nullable DATA data) {
        final AssemblyAdapter adapter = itemFactory.getAdapter();
        if (itemFactory.end) {
            showEnd();
        } else if (adapter != null) {
            showLoading();
            if (itemFactory.listener != null && !itemFactory.paused) {
                itemFactory.paused = true;
                itemFactory.listener.onLoadMore(adapter);
            }
        }
    }
}
