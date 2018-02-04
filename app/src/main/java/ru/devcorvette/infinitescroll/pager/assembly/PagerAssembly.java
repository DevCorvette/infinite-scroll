package ru.devcorvette.infinitescroll.pager.assembly;

import dagger.Module;
import dagger.Provides;
import ru.devcorvette.infinitescroll.pager.logic.PagerInteractor;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerRouter;
import ru.devcorvette.infinitescroll.pager.presentatation.PagerPresenter;
import ru.devcorvette.infinitescroll.pager.presentatation.PagerRouter;
import ru.devcorvette.infinitescroll.pager.presentatation.view.IPagerView;
import ru.devcorvette.infinitescroll.pager.presentatation.view.PagerView;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;

@Module
public class PagerAssembly {

    private PagerComponent component;

    private ScrollPresenter scrollPresenter;
    private PagerPresenter pagerPresenter = new PagerPresenter();
    private PagerView pagerView = new PagerView();
    private PagerInteractor pagerInteractor = new PagerInteractor();
    private PagerRouter pagerRouter = new PagerRouter();

    public PagerAssembly(ScrollPresenter scrollPresenter) {
        this.scrollPresenter = scrollPresenter;

        component = DaggerPagerComponent
                .builder()
                .pagerAssembly(this)
                .build();

        assemble();
    }

    private void assemble() {
        component.inject(pagerPresenter);
        component.inject(pagerView);
        component.inject(pagerInteractor);
        component.inject(pagerRouter);
    }

    @Provides
    public PagerInteractor providePagerInteractor(){
        return pagerInteractor;
    }

    @Provides
    public IPagerPresenter providePagerPresenter(){
        return pagerPresenter;
    }

    @Provides
    public IPagerView providePagerView(){
        return pagerView;
    }

    @Provides
    public IScrollPresenter provideScrollPresenter(){
        return scrollPresenter;
    }

    @Provides
    public IPagerRouter providePagerRouter(){
        return pagerRouter;
    }

}
