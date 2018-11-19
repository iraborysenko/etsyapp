package borysenko.etsyapp.ui.main.searchtab.adapter;

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

import borysenko.etsyapp.R;
import borysenko.etsyapp.model.Merchandise;
import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.Arrays;

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/11/18
 * Time: 23:21
 */
public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder> {

    private static ClickListener clickListener;
    private static Merchandise[] mMerch;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.merch_image)
        ImageView mImage;
        @BindView(R.id.merch_title)
        TextView mTitle;


        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);
        }

        @Override
        public void onClick(View v) {
            Merchandise merchandise = mMerch[getAdapterPosition()];
            clickListener.onItemClick(v, merchandise);
        }

    }

    public SearchRecyclerAdapter(Merchandise[] merch, Context context) {
        mMerch = merch;
        mContext = context;
    }

    @NonNull
    @Override
    public SearchRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder merchViewHolder, int i) {

        Merchandise merchandise = mMerch[i];
        if (merchandise != null) {
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .placeholder(R.mipmap.product_placeholder)
                    .error(R.mipmap.no_image)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);

            Glide.with(mContext)
                    .load(merchandise.getImageUrl())
                    .apply(options)
                    .into(merchViewHolder.mImage);

            merchViewHolder.mTitle.setText(merchandise.getTitle());
        }

    }

    @Override
    public int getItemCount() {
        return mMerch.length;
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        SearchRecyclerAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(View v, Merchandise merchandise);
    }

    public void add(Merchandise merchandise) {
        mMerch = Arrays.copyOf(mMerch, mMerch.length +1);
        mMerch[mMerch.length - 1] = merchandise;
        notifyItemInserted(mMerch.length - 1);
    }

    public void clear() {
        mMerch=new Merchandise[0];
        notifyDataSetChanged();
    }
}