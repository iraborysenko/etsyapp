package borysenko.etsyapp.ui.main.selectedtab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import borysenko.etsyapp.R;
import borysenko.etsyapp.model.Merchandise;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 18/11/18
 * Time: 20:26
 */
public class SelectedRecyclerAdapter extends RecyclerView.Adapter<SelectedRecyclerAdapter.ViewHolder> {
    private static ClickListener clickListener;
    private static List<Merchandise> mMerchs;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.m_image)
        ImageView mImage;
        @BindView(R.id.m_title)
        TextView mTitle;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, mMerchs.get(getAdapterPosition()));
        }
    }

    SelectedRecyclerAdapter(List<Merchandise> merchs, Context context) {
        mMerchs = merchs;
        mContext = context;
    }

    @NonNull
    @Override
    public SelectedRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                 int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selected_recycler_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder merchViewHolder, int i) {

        Merchandise merchandise = mMerchs.get(i);

        if (merchandise != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.product_placeholder)
                    .error(R.mipmap.no_image)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            assert merchViewHolder.mImage != null;
            Glide.with(mContext)
                    .load(merchandise.getImageUrl())
                    .apply(options)
                    .into(merchViewHolder.mImage);

            merchViewHolder.mTitle.setText(merchandise.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return mMerchs.size();
    }

    void setOnItemClickListener(ClickListener clickListener) {
        SelectedRecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, Merchandise merchandise);
    }

    public void add(Merchandise merchandise) {
        mMerchs.add(merchandise);
        notifyItemInserted(mMerchs.size() - 1);
    }

    public void addAll(List<Merchandise> merchandises) {
        for (Merchandise merch: merchandises) {
            add(merch);
        }
    }

    public void clear() {
        mMerchs.clear();
        notifyDataSetChanged();
    }
}
