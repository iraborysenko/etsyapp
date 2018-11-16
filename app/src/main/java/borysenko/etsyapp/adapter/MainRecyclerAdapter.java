package borysenko.etsyapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

/**
 * Created by Android Studio.
 * User: Iryna
 * Date: 16/11/18
 * Time: 23:21
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

//    private static ClickListener clickListener;
private static Merchandise[] mMerch;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener, View.OnLongClickListener*/ {
        @BindView(R.id.merch_image)
        ImageView mImage;
        @BindView(R.id.merch_title)
        TextView mTitle;


        ViewHolder(View v) {
            super(v);
//            v.setOnClickListener(this);
//            v.setOnLongClickListener(this);
            ButterKnife.bind(this, v);
        }

//        @Override
//        public void onClick(View v) {
//            movieId = Objects.requireNonNull(mMovies.get(getAdapterPosition())).getId();
//            dataLang = Objects.requireNonNull(mMovies.get(getAdapterPosition())).getSavedLang();
//            clickListener.onItemClick(v, movieId, dataLang);
//        }
//
//        @Override
//        public boolean onLongClick(View v) {
//            movieId = Objects.requireNonNull(mMovies.get(getAdapterPosition())).getId();
//            dataLang = Objects.requireNonNull(mMovies.get(getAdapterPosition())).getSavedLang();
//            clickListener.onItemLongClick(v, movieId, dataLang);
//            return true;
//        }
    }

    public MainRecyclerAdapter(Merchandise[] merch, Context context) {
        mMerch = merch;
        mContext = context;
    }

    @NonNull
    @Override
    public MainRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycler_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder merchViewHolder, int i) {

        Merchandise merchandise = mMerch[i];
        assert merchandise != null;

        // get poster
//        RequestOptions options = new RequestOptions()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE);
//        Glide.with(mContext)
//                .asBitmap()
//                .load(stringToBitmap(movie.getPosterBitmap()))
//                .apply(options)
//                .into(movieViewHolder.mPoster);
//        Log.e("recyclerAdapter", merchandise.getImageUrl());
        Log.e("recyclerTitle", merchandise.getTitle());

        merchViewHolder.mTitle.setText(merchandise.getTitle());


    }

    @Override
    public int getItemCount() {
        return mMerch.length;
    }

//    public void setOnItemClickListener(ClickListener clickListener) {
//        MainRecyclerAdapter.clickListener = clickListener;
//    }
//
//    public interface ClickListener {
//        void onItemClick(View v, String movieId, String dataLang);
//        void onItemLongClick(View v, String movieId, String dataLang);
//    }


}

