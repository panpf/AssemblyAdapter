package me.panpf.adapter.expandable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.panpf.adapter.AssemblyAdapter;
import me.panpf.adapter.ItemFactory;
import me.panpf.adapter.ItemHolder;
import me.panpf.adapter.ItemStorage;
import me.panpf.adapter.ViewTypeManager;

@SuppressWarnings({"unused"})
public class ExpandableItemStorage extends ItemStorage {

    @NonNull
    private ViewTypeManager childViewTypeManager = new ViewTypeManager();
    @NonNull
    private ArrayList<ItemFactory> childItemFactoryList = new ArrayList<>();

    public ExpandableItemStorage(@NonNull AssemblyAdapter adapter) {
        super(adapter);
    }

    public ExpandableItemStorage(@NonNull AssemblyAdapter adapter, @Nullable List dataList) {
        super(adapter, dataList);
    }

    public ExpandableItemStorage(@NonNull AssemblyAdapter adapter, @Nullable Object[] dataArray) {
        super(adapter, dataArray);
    }

    /**
     * 添加一个用来处理并显示 dataList 中的 child 数据的 {@link ItemFactory}
     */
    public void addChildItemFactory(@NonNull ItemFactory childItemFactory) {
        //noinspection ConstantConditions
        if (childItemFactory == null || childViewTypeManager.isLocked()) {
            throw new IllegalStateException("childItemFactory is null or item factory list locked");
        }

        childItemFactoryList.add(childItemFactory);
        int viewType = childViewTypeManager.add(childItemFactory);

        childItemFactory.attachToAdapter(getAdapter(), viewType);
    }

    /**
     * 获取 child {@link ItemFactory} 列表
     */
    @Nullable
    public List<ItemFactory> getChildItemFactoryList() {
        return childItemFactoryList;
    }

    /**
     * 获取 child {@link ItemFactory} 的个数
     */
    public int getChildItemFactoryCount() {
        return childItemFactoryList.size();
    }

    public int getChildTypeCount() {
        if (!childViewTypeManager.isLocked()) {
            childViewTypeManager.lock();
        }
        return childViewTypeManager.getCount();
    }

    /**
     * 根据 view 类型获取 {@link ItemFactory} 或 {@link ItemHolder}
     *
     * @param viewType view 类型
     * @return null：没有；{@link ItemFactory} 或 {@link ItemHolder}
     */
    @Nullable
    public Object getChildItemFactoryByViewType(int viewType) {
        return childViewTypeManager.get(viewType);
    }
}
