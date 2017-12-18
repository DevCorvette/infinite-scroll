package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.*;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;

/**
 * Привязывает изображения из data к image_view.
 * И привязывает progress bar.
 */
public class DatumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int itemCount = 0;
    private final int VIEW_ITEM = 0;
    private final int PROGRESS_ITEM = 1;
    private static final String TAG = Router.TAG + DatumAdapter.class.getSimpleName();
    private boolean isVisibleProgress = false;

    private ScrollView scrollView;

    public DatumAdapter(ScrollView scrollView) {
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
     * todo
     * Устанавливает TouchListener.
     *
     * Для ProgressHolder - прикрепляет прогресс бар.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {

            ImageView imageView = ((ViewHolder) holder).imageView;
            scrollView.putBitmapInView(position, 0, imageView);
            imageView.setOnTouchListener(new TouchListener(position));

        } else if (holder instanceof ProgressHolder) {

            ProgressBar progressBar = ((ProgressHolder) holder).progressBar;
            progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return position == itemCount - 1 && isVisibleProgress ? PROGRESS_ITEM : VIEW_ITEM;
    }

    /**
     * todo
     */
    public void setVisibleProgress(boolean isVisibleProgress) {
        if (isVisibleProgress){
            itemCount++;
        } else {
            itemCount--;
        }
        this.isVisibleProgress = isVisibleProgress;
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

            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
        }
    }

    /**
     * todo
     */
    class TouchListener implements View.OnTouchListener {
        private final int position;

        public TouchListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }
}
