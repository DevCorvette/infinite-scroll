package ru.devcorvette.infinitescroll.base.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;

/**
 * Адаптер для загрузки и отображения изображений.
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = Router.TAG + BaseRecyclerAdapter.class.getSimpleName();
    public final int IMAGE_ITEM = 0;

    /**
     * Создает ImageHolder.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_view, parent, false));
    }

    /**
     * Загружает изображение при помощи Picasso.
     */
    public void loadImage(ImageView imageView, String imageURL){
        Picasso.with(imageView.getContext())
                .load(imageURL)
                .placeholder(R.drawable.rectangle)
                .fit()
                .centerInside()
                .into(imageView);

        if(BuildConfig.DEBUG) Log.d(TAG, "load image " + imageURL);
    }

    @Override
    public abstract int getItemViewType(int position);

    /**
     * Содержит экземпляр ImageView.
     */
    public class ImageHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
