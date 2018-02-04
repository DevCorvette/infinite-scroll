package ru.devcorvette.infinitescroll.scroll.assembly;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import ru.devcorvette.infinitescroll.pager.assembly.PagerAssembly;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.logic.ScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollRouter;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollRouter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;
import ru.devcorvette.infinitescroll.scroll.presentation.view.ScrollView;

@Module
public class ScrollAssembly implements IScrollAssembly {

    private ScrollComponent component;

    private ScrollPresenter scrollPresenter = new ScrollPresenter();
    private ScrollView scrollView = new ScrollView();
    private ScrollInteractor scrollInteractor = new ScrollInteractor();
    private ScrollRouter scrollRouter  = new ScrollRouter();

    private PagerAssembly pagerAssembly = null;

    public ScrollAssembly(){
        component = DaggerScrollComponent
                .builder()
                .scrollAssembly(this)
                .build();

        assemble();
    }

    private void assemble(){
        component.inject(scrollPresenter);
        component.inject(scrollInteractor);
        component.inject(scrollView);
        component.inject(scrollRouter);
    }

    @Provides
    public IScrollInteractor provideScrollInteractor(){
        return scrollInteractor;
    }

    @Provides
    public IScrollPresenter provideScrollPresenter(){
        return scrollPresenter;
    }

    @Provides
    public IScrollView provideScrollView(){
        return scrollView;
    }

    @Provides
    public IScrollAssembly provideScrollAssembly(){
        return this;
    }

    @Provides
    public IScrollRouter provideScrollRouter(){
        return scrollRouter;
    }

    @Override
    public Fragment getPagerFragment() {
        if(pagerAssembly == null) assemblePager();

        return (Fragment) pagerAssembly.providePagerView();
    }

    @Override
    public IPagerPresenter getPagerPresenter() {
        if(pagerAssembly == null) assemblePager();

        return pagerAssembly.providePagerPresenter();
    }

    private void assemblePager(){
        pagerAssembly = new PagerAssembly(scrollPresenter);
    }
}
