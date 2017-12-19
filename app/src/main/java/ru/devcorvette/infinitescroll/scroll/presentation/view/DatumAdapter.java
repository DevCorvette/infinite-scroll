package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * Привязывает изображения из data к image_view.
 * И привязывает progress bar.
 */
class DatumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_ITEM = 0;
    private final int PROGRESS_ITEM = 1;
    private static final String TAG = Router.TAG + DatumAdapter.class.getSimpleName();
    private boolean isVisibleProgress = false;

    private List<String> urls = new ArrayList<>();

    private ScrollView scrollView;

    DatumAdapter(ScrollView scrollView) {
        this.scrollView = scrollView;
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
     * Для ImageView загружает изображение и
     * устанавливает TouchListener.
     *
     * Для ProgressHolder - прикрепляет прогресс бар.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            ImageView imageView = ((ViewHolder) holder).imageView;
            loadImage(imageView, urls.get(position));
            imageView.setOnTouchListener(new TouchListener(position));

        } else if (holder instanceof ProgressHolder) {

            ProgressBar progressBar = ((ProgressHolder) holder).progressBar;
            progressBar.setIndeterminate(true);
        }
    }

    /**
     * Загружает изображение при помощи Picasso.
     */
    private void loadImage(ImageView imageView, String imageURL){
        Picasso.with(imageView.getContext())
                .load(imageURL)
                .placeholder(R.drawable.rectangle)
                .fit()
                .centerInside()
                .into(imageView);

        if(BuildConfig.DEBUG) Log.d(TAG, "load image " + imageURL);
    }

    @Override
    public int getItemCount() {
        if(isVisibleProgress){
            return urls.size()+1;
        } else {
            return urls.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount()-1 && isVisibleProgress ? PROGRESS_ITEM : VIEW_ITEM;
    }

    /**
     * Меняет индикатор прогресс бара.
     */
    void setVisibleProgress(boolean isVisibleProgress) {
        this.isVisibleProgress = isVisibleProgress;
    }

    /**
     * Добавляет новые данные.
     */
    void setUrls(List<String> urls){
        this.urls.addAll(urls);
    }

    /**
     * Содержит экземпляр ImageView.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
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
    class ProgressHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        ProgressHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);

            ((StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    /**
     * При клике вызывает модуль отображения страницы.
     */
    class TouchListener implements View.OnTouchListener {
        private final int position;

        public TouchListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            scrollView.showPage(position);
            return true;
        }
    }
}
