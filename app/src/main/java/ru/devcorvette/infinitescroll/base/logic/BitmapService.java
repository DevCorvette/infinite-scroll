package ru.devcorvette.infinitescroll.base.logic;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;

/**
 * todo
 */
public class BitmapService {
    private final String TAG = Router.TAG + BitmapService.class.getSimpleName();

    /**todo*/
    private List<Target> targets = new ArrayList<>();

    private Map<Datum, Bitmap[]> bitmaps;

    public BitmapService(Map<Datum, Bitmap[]> bitmaps) {
        this.bitmaps = bitmaps;
    }

    /**
     * todo
     */
    public void setBitmaps(List<Datum> downloadedData){
        for(Datum datum : downloadedData){
            bitmaps.put(datum, new Bitmap[datum.getCoverInfo().length]);
        }

        if(BuildConfig.DEBUG) Log.d(TAG, "set bitmaps. bitmaps.size == " + bitmaps.size());
    }

    /**
     * todo
     */
    public void putBitmapInView(Datum datum, int bitmapPosition, ImageView imageView) {
        Bitmap bitmap = bitmaps.get(datum)[bitmapPosition];
        if(bitmap == null){
            loadBitmap(imageView, datum, bitmapPosition);
        } else {
            imageView.setImageBitmap(bitmap);

            if(BuildConfig.DEBUG) Log.d(TAG, bitmap + " put in " + imageView);
        }
    }

    /**
     * todo
     */
    private void loadBitmap(ImageView imageView,
                            Datum datum,
                            int bitmapPosition){

        String bitmapURL = datum.getCoverInfo()[bitmapPosition].getImage();

        Target target = new ScrollTarget(datum, bitmapPosition, imageView, bitmapURL);
        targets.add(target);

        Picasso.with(imageView.getContext())
                .load(bitmapURL)
                .placeholder(R.drawable.rectangle)
                .into(target);
    }

    private class ScrollTarget implements Target {
        private Datum datum;
        private int bitmapPosition;
        private ImageView imageView;
        private String bitmapURL;

        public ScrollTarget(Datum datum, int bitmapPosition, ImageView imageView, String bitmapURL) {
            this.datum = datum;
            this.bitmapPosition = bitmapPosition;
            this.imageView = imageView;
            this.bitmapURL = bitmapURL;
        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            bitmaps.get(datum)[bitmapPosition] = bitmap;
            imageView.setImageBitmap(bitmap);

            targets.remove(this);

            if(BuildConfig.DEBUG) Log.d(TAG, "load " + bitmapURL);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            imageView.setImageDrawable(errorDrawable);
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
            imageView.setImageDrawable(placeHolderDrawable);
        }
    }
}
