package ru.devcorvette.infinitescroll.scroll.assembly;

import dagger.Component;
import ru.devcorvette.infinitescroll.scroll.logic.ScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollRouter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.ScrollView;

@Component(modules = {ScrollAssembly.class})
public interface ScrollComponent {

    void inject(ScrollPresenter scrollPresenter);

    void inject(ScrollView scrollView);

    void inject(ScrollInteractor scrollInteractor);

    void inject(ScrollRouter scrollRouter);
}
