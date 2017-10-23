package ru.devcorvette.infinitescroll.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.List;

import ru.devcorvette.infinitescroll.R;

public class BitmapsAdapter extends ArrayAdapter<Bitmap> {

    private LayoutInflater inflater;
    private int resource;

    public BitmapsAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Bitmap> bitmaps) {
        super(context, resource, bitmaps);

        this.inflater = LayoutInflater.from(context);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imageView;
        ViewHolder viewHolder;

        if (convertView == null) {

            convertView =  inflater.inflate(resource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        imageView = viewHolder.imageView;

        Bitmap bitmap = getItem(position);

        //закругляет изображению каря
        RoundedBitmapDrawable rbd = RoundedBitmapDrawableFactory.create(convertView.getResources(), bitmap);
        rbd.setCornerRadius(10);
        imageView.setImageDrawable(rbd);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
    }
}
