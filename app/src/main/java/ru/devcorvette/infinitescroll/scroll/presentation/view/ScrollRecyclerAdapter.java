package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.base.presentation.view.BaseRecyclerAdapter;

class ScrollRecyclerAdapter extends BaseRecyclerAdapter {
    public final int PROGRESS_ITEM = 1;

    private int visibleItemCount = 0;

    private boolean isVisibleProgress = false;

    private IScrollView scrollView;

    ScrollRecyclerAdapter(ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == PROGRESS_ITEM) {
            viewHolder = new ProgressHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.progress_bar, parent, false));
        } else {
            viewHolder = super.onCreateViewHolder(parent, viewType);
        }

        return viewHolder;
    }

    /**
     * Для ImageView загружает изображение и устанавливает ClickListener.
     * Устанавливает progress bar.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImageHolder) {

            ImageView imageView = ((ImageHolder) holder).imageView;
            loadImage(imageView, getImageURL(position));
            imageView.setOnClickListener(new ClickListener(position));

        } else if (holder instanceof ProgressHolder) {

            ProgressBar progressBar = ((ProgressHolder) holder).progressBar;
            progressBar.setIndeterminate(true);
        }
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
    @Override
    public int getItemViewType(int position) {
        return position == getItemCount()-1 && isVisibleProgress ? PROGRESS_ITEM : IMAGE_ITEM;
    }

    private String getImageURL(int position){
        return scrollView.getImageURL(position);
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
     * Содержит экземпляр ProgressBar.
     * Расширяет progressBar на все столбцы StaggeredGridLayoutManager.
     */
    public class ProgressHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);

            ((StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams()).setFullSpan(true);
        }
    }

    /**
     * При клике вызывает модуль отображения страницы.
     */
    class ClickListener implements View.OnClickListener {
        private final int position;

        public ClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            scrollView.showPage(position);
        }
    }
}
