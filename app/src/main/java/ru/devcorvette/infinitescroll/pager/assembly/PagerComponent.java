package ru.devcorvette.infinitescroll.pager.assembly;

import dagger.Component;
import ru.devcorvette.infinitescroll.pager.logic.PagerInteractor;
import ru.devcorvette.infinitescroll.pager.presentatation.PagerPresenter;
import ru.devcorvette.infinitescroll.pager.presentatation.PagerRouter;
import ru.devcorvette.infinitescroll.pager.presentatation.view.PagerView;

@Component(modules = {PagerAssembly.class})
public interface PagerComponent {

    void inject(PagerInteractor interactor);

    void inject(PagerPresenter presenter);

    void inject(PagerView pagerView);

    void inject(PagerRouter pagerRouter);

}
