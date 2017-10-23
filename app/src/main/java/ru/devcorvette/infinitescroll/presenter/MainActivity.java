package ru.devcorvette.infinitescroll.presenter;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.model.IModel;
import ru.devcorvette.infinitescroll.model.Model;
import ru.devcorvette.infinitescroll.view.IView;
import ru.devcorvette.infinitescroll.view.View;

public class MainActivity extends Activity {
    private IPresenter presenter;
    private IView view;
    private IModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        view = new View(this);
        model = new Model(this);
        presenter = new Presenter(view, model, this);

        presenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

}
