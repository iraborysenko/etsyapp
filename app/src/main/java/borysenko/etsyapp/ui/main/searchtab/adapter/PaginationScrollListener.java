package borysenko.etsyapp.ui.main.searchtab.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 17/11/18
 * Time: 21:28
 */
public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    private int visibleThreshold;
    private int currentPage;
    private int previousTotalItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex;

    private RecyclerView.LayoutManager mLayoutManager;

    protected PaginationScrollListener(int visibleThreshold, int startPage, LinearLayoutManager linearLayoutManager) {
        this.visibleThreshold = visibleThreshold;
        this.mLayoutManager = linearLayoutManager;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int lastVisibleItemPosition = 0;
        int totalItemCount = mLayoutManager.getItemCount();

        lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

}
