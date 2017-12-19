package ru.devcorvette.infinitescroll.assembly;

import javax.inject.Singleton;

import dagger.Component;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.scroll.logic.ScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.ScrollView;

@Singleton
@Component(modules = {Assembly.class})
public interface AssemblyComponent {

    void inject(MainActivity mainActivity);

    void inject(ScrollPresenter scrollPresenter);

    void inject(ScrollInteractor scrollInteractor);

    void inject(ScrollView scrollView);
}