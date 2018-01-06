package ru.devcorvette.infinitescroll.assembly;

import dagger.Module;
import dagger.Provides;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.pager.logic.PagerInteractor;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;
import ru.devcorvette.infinitescroll.pager.presentatation.PagerPresenter;
import ru.devcorvette.infinitescroll.pager.presentatation.view.IPagerView;
import ru.devcorvette.infinitescroll.pager.presentatation.view.PagerView;
import ru.devcorvette.infinitescroll.scroll.logic.ScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;
import ru.devcorvette.infinitescroll.scroll.presentation.view.ScrollView;

@Module
public class Assembly {
    private MainActivity mainActivity;
    private AssemblyComponent component;

    private ScrollPresenter scrollPresenter = new ScrollPresenter();
    private ScrollView scrollView = new ScrollView();
    private ScrollInteractor scrollInteractor = new ScrollInteractor();

    private PagerPresenter pagerPresenter = new PagerPresenter();
    private PagerView pagerView = new PagerView();
    private PagerInteractor pagerInteractor = new PagerInteractor();

    public Assembly(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        component = DaggerAssemblyComponent
                .builder()
                .assembly(this)
                .build();
    }

    public void assemble(){
        component.inject(mainActivity);
        component.inject(scrollPresenter);
        component.inject(scrollInteractor);
        component.inject(scrollView);

        component.inject(pagerPresenter);
        component.inject(pagerView);
        component.inject(pagerInteractor);
    }

    @Provides
    public ScrollInteractor provideBaseInteractor(){
        return scrollInteractor;
    }

    @Provides
    public PagerInteractor providePagerInteractor(){
        return pagerInteractor;
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
    public IPagerPresenter providePagerPresenter(){
        return pagerPresenter;
    }

    @Provides
    public IPagerView providePagerView(){
        return pagerView;
    }

    @Provides
    public Router provideRouter(){
        return mainActivity;
    }
}
