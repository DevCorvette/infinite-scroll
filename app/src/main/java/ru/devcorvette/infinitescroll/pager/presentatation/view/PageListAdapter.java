package ru.devcorvette.infinitescroll.pager.presentatation.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.baselist.presentation.view.BaseListAdapter;
import ru.devcorvette.infinitescroll.baselist.presentation.view.IBaseListView;

/**
 * Адаптер для отображения деталей конкретной страницы.
 */
class PageListAdapter extends BaseListAdapter {
    public final int TEXT_ITEM = 2;

    private String[] imagesURL;
    private String[] strings;


    //todo wtf?
    public PageListAdapter(IBaseListView listView) {
        super(listView);
    }

//    public PageListAdapter(String[] imagesURL, String[] strings) {
//        this.imagesURL = imagesURL;
//        this.strings = strings;
//    }

    /**
     * Если viewType == TEXT_ITEM - создает TextHolder,
     * остальное создает BaseListAdapter.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;

        if (viewType == TEXT_ITEM) {
            viewHolder = new TextHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.text_view, parent, false));
        } else {
            viewHolder = super.onCreateViewHolder(parent, viewType);
        }
        return viewHolder;
    }

    /**
     * Прикрепляет текст к TextHolder и изображение к ImageHolder.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //todo wtf?
//        if (holder instanceof TextHolder) {
//
//            TextView textView = ((TextHolder)holder).textView;
//            textView.setText(strings[position - imagesURL.length]);
//
//        } else if (holder instanceof ImageHolder){
//
//            ImageView imageView = ((ImageHolder) holder).imageView;
//            loadImage(imageView, imagesURL[position]);
//        }
    }

    /**
     * Сначала отображаются изображения, затем строки.
     */
    @Override
    public int getItemViewType(int position) {
        int viewType;

        if(position < imagesURL.length){
            viewType = NORMAL_ITEM;

        } else {
            viewType = TEXT_ITEM;
        }

        return viewType;
    }

    /**
     * Количество изображений + строк = общее количество элементов.
     */
    @Override
    public int getItemCount() {
        return imagesURL.length + strings.length;
    }

    /**
     * Содержит экземпляр textView.
     */
    public class TextHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public TextHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
