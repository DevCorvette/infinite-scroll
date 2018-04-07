package ru.devcorvette.infinitescroll.baselist.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.R;

/**
 * Адаптер для загрузки и отображения изображений.
 */
public abstract class BaseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final String TAG = MainActivity.TAG + BaseListAdapter.class.getSimpleName();

    public final int NORMAL_ITEM = 0;
    public final int PROGRESS_ITEM = 1;

    private int visibleItemCount = 0;

    private boolean isVisibleProgress = false;

    protected IBaseListView listView;

    public BaseListAdapter(IBaseListView listView) {
        this.listView = listView;
    }

    /**
     * Создает ProgressHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProgressHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.progress_bar, parent, false));
    }

    /**
     * Если isVisibleItemCount == true, прибавляет +1 к отображаемым элементам.
     */
    @Override
    public int getItemCount() {
        if(isVisibleProgress){
            return visibleItemCount +1;
        } else {
            return visibleItemCount;
        }
    }

    public void setVisibleItemCount(int visibleItemCount) {
        this.visibleItemCount = visibleItemCount;
    }

    /**
     * Если элемент последний или единственный и isVisibleProgress == true
     * то элемент - прогресс бар.
     */
    public int getItemViewType(int position) {
        return position == getItemCount()-1 && isVisibleProgress ? PROGRESS_ITEM : NORMAL_ITEM;
    }

    /**
     * Меняет индикатор отображения прогресс бара.
     */
    public void setVisibleProgress(boolean isVisibleProgress) {
        this.isVisibleProgress = isVisibleProgress;
    }

    public boolean isVisibleProgress() {
        return isVisibleProgress;
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

    /**
     * Содержит экземпляр ProgressBar.
     * Расширяет progressBar на все столбцы StaggeredGridLayoutManager.
     */
    public class ProgressHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}
