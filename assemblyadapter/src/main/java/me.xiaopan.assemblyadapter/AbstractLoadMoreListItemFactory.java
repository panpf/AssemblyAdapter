package me.xiaopan.assemblyadapter;

import android.content.Context;
import android.view.View;

public abstract class AbstractLoadMoreListItemFactory extends AssemblyItemFactory<AbstractLoadMoreListItemFactory.AbstractLoadMoreListItem> {
    private boolean loadMoreRunning;
    private boolean end;
    private OnLoadMoreListener eventListener;

    public AbstractLoadMoreListItemFactory(OnLoadMoreListener eventListener) {
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

    public abstract class AbstractLoadMoreListItem extends AssemblyItem<String>{
        protected AbstractLoadMoreListItem(View convertView) {
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
                        setData(getPosition(), getData());
                    }
                }
            });
        }

        @Override
        public void onSetData(int position, String s) {
            if(end){
                showEnd();
            }else{
                showLoading();
                if (eventListener != null && !loadMoreRunning) {
                    loadMoreRunning = true;
                    eventListener.onLoadMore(adapter);
                }
            }
        }
    }
}
