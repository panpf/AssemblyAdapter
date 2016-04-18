package me.xiaopan.assemblyadapter;

import android.content.Context;
import android.view.View;

public abstract class AbstractLoadMoreRecyclerItemFactory extends AssemblyRecyclerItemFactory<AbstractLoadMoreRecyclerItemFactory.AbstractLoadMoreRecyclerItem> {
    private boolean loadMoreRunning;
    private boolean end;
    private OnRecyclerLoadMoreListener eventListener;

    public AbstractLoadMoreRecyclerItemFactory(OnRecyclerLoadMoreListener eventListener) {
        this.eventListener = eventListener;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public void setLoadMoreRunning(boolean loadMoreRunning) {
        this.loadMoreRunning = loadMoreRunning;
    }

    @Override
    public boolean isTarget(Object itemObject) {
        return false;
    }

    public abstract class AbstractLoadMoreRecyclerItem extends AssemblyRecyclerItem<String> {
        protected AbstractLoadMoreRecyclerItem(View convertView) {
            super(convertView);
        }

        public abstract View getErrorRetryView();

        public abstract void showLoading();

        public abstract void showErrorRetry();

        public abstract void showEnd();

        @Override
        public void onConfigViews(Context context) {
            getErrorRetryView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (eventListener != null) {
                        loadMoreRunning = false;
                        setData(getLayoutPosition(), getData());
                    }
                }
            });
        }

        @Override
        public void onSetData(int position, String s) {
            if (end) {
                showEnd();
            } else {
                showLoading();
                if (eventListener != null && !loadMoreRunning) {
                    loadMoreRunning = true;
                    eventListener.onLoadMore(getAdapter());
                }
            }
        }
    }
}
