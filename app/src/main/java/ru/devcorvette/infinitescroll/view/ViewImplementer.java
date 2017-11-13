package ru.devcorvette.infinitescroll.view;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.model.api.Datum;
import rx.subjects.PublishSubject;

public class ViewImplementer implements IView {
    private final Activity activity;

    private PublishSubject<Integer> subject;

    private RecyclerView recyclerView;
    private DatumAdapter adapter;

    private final List<Datum> datumList = new ArrayList<>();

    private static final String TAG = "my_debug_" + ViewImplementer.class.getSimpleName();

    public ViewImplementer(Activity activity) {
        this.activity = activity;

        subject = PublishSubject.create();
    }

    @Override
    public void onCreate() {
        recyclerView = activity.findViewById(R.id.recyclerView);
        adapter = new DatumAdapter(datumList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setItemViewCacheSize(100);
        recyclerView.addOnScrollListener(new InfiniteScrollListener(this));
    }

    @Override
    public PublishSubject<Integer> getObservable() {
        return subject;
    }

    @Override
    public void needData() {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, " needData datumList.size() == " + datumList.size());
        }
        subject.onNext(datumList.size());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(activity.getApplicationContext(),
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setProgressVisible() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                datumList.add(null);
                adapter.notifyItemInserted(datumList.size() - 1);
            }
        });
    }

    /**
     * Добавляет данные к datumList, удаляет прогресс бар и вызывает обновление адаптера.
     */
    @Override
    public void showData(final List<Datum> data) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "showData datumList.size == "
                    + datumList.size()
                    + " data.size == "
                    + data.size());
        }

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                int progressItem = datumList.size() - 1;
                int dataSize = data.size();

                datumList.remove(progressItem);
                datumList.addAll(data);

                if(dataSize == 0){
                    adapter.notifyItemRemoved(progressItem);
                } else {
                    adapter.notifyItemRangeChanged(progressItem, dataSize);
                }
            }
        });
    }
}
