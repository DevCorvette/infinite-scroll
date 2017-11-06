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
        recyclerView = (RecyclerView) activity.findViewById(R.id.recyclerView);
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

    /**
     * На данный момент работает некорректно - recyclerView прокручивает
     * пустое пространсво под ProgressBar.
     */
    @Override
    public void setProgressVisibility(boolean visible) {
        if (visible) {
            datumList.add(null);
            adapter.notifyItemInserted(datumList.size() - 1);
        } else {
            int removeItem = datumList.size();
            datumList.remove(removeItem - 1);
            adapter.notifyItemRemoved(removeItem);
        }
    }

    /**
     * Добавляет данные к datumList и вызывает обновление адаптера.
     */
    @Override
    public void showData(List<Datum> data) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "showData datumList.size == "
                    + datumList.size()
                    + " data.size == "
                    + data.size());
        }

        int oldSize = datumList.size();
        datumList.addAll(data);
        adapter.notifyItemRangeChanged(oldSize, datumList.size());
    }
}
