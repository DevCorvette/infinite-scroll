package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.baselist.presentation.view.BaseListAdapter;
import ru.devcorvette.infinitescroll.baselist.presentation.view.IBaseListView;

class ScrollListAdapter extends BaseListAdapter {

    protected IScrollView listView;

    public ScrollListAdapter(IBaseListView listView) {
        super(listView);
    }

    /**
     * todo
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == NORMAL_ITEM){

            return new ImageHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.card_view, parent, false));
        } else {
            return super.onCreateViewHolder(parent, viewType);
        }
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
            imageView.getRootView().setOnClickListener(new ClickListener(position));

        } else if (holder instanceof ProgressHolder) {

            ProgressBar progressBar = ((ProgressHolder) holder).progressBar;
            //todo проверить работает ли после рефакторинга
            ((StaggeredGridLayoutManager.LayoutParams) progressBar.getRootView().getLayoutParams()).setFullSpan(true);
            progressBar.setIndeterminate(true);
        }
    }

    private String getImageURL(int position){
        return listView.getImageURL(position);
    }

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
            listView.showPage(position);
        }
    }
}
