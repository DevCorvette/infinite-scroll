package ru.devcorvette.infinitescroll.assembly;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.IBaseInteractor;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.logic.ScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.ScrollView;

@Module
public class Assembly {
    private MainActivity mainActivity;
    private AssemblyComponent component;

    private ScrollPresenter scrollPresenter = new ScrollPresenter();
    private ScrollInteractor scrollInteractor = new ScrollInteractor();
    private ScrollView scrollView = new ScrollView();

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
    }

    @Provides
    @Singleton
    public IScrollPresenter provideScrollPresenter(){
        return scrollPresenter;
    }

    @Provides
    @Singleton
    public IScrollView provideScrollView(){
        return scrollView;
    }

    @Provides
    @Singleton
    public IScrollInteractor provideScrollInteractor(){
        return scrollInteractor;
    }

    @Provides
    public Router provideRouter(){
        return mainActivity;
    }
}
