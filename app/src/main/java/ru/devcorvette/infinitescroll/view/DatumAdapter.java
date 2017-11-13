package ru.devcorvette.infinitescroll.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.model.api.Datum;

/**
 * Привязывает изображения из data к image_view.
 * И привязывает progress bar.
 */
public class DatumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int PROGRESS_ITEM = 1;
    private List<Datum> data;

    private static final String TAG = "my_debug_" + DatumAdapter.class.getSimpleName();

    public DatumAdapter(List<Datum> data) {
        this.data = data;
    }

    /**
     * В зависимости от viewType создает ViewHolder или ProgressView.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == VIEW_ITEM) {
            viewHolder = new ViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.card_view, parent, false));
        } else {
            viewHolder = new ProgressHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.progress_bar, parent, false));
        }
        return viewHolder;
    }

    /**
     * Для ViewHolder вытаскивает из data imageURL для отображения
     * и загружает изображение.
     * Для ProgressHolder - прикрепляет прогресс бар.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            ImageView imageView = ((ViewHolder) holder).imageView;
            String imageURL = data.get(position).getCoverInfo()[0].getImage();
            loadImage(imageView, imageURL);

        } else if (holder instanceof ProgressHolder) {

            ProgressBar progressBar = ((ProgressHolder) holder).progressBar;
            progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) != null ? VIEW_ITEM : PROGRESS_ITEM;
    }

    /**
     * Загружает изображения при помощи Picasso и помещает в imageView.
     */
    private void loadImage(ImageView imageView, String imageURL) {
        Picasso.with(imageView.getContext())
                .load(imageURL)
                .placeholder(R.drawable.rectangle)
                .fit()
                .centerInside()
                .into(imageView);

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "load image " + imageURL);
        }
    }

    /**
     * Содержит экземпляр ImageView.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    /**
     * Содержит экземпляр ProgressBar.
     * Расширяет progressBar на все столбцы StaggeredGridLayoutManager.
     */
    static class ProgressHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        ProgressHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);

            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }
}
