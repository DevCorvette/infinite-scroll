package ru.devcorvette.infinitescroll.assembly;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.scroll.logic.DataService;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.logic.ScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.DatumAdapter;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollView;
import ru.devcorvette.infinitescroll.scroll.presentation.InfiniteScrollListener;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollView;

@Module
public class Assembly {
    private MainActivity mainActivity;
    private AssemblyComponent component;

    private InfiniteScrollListener scrollListener = new InfiniteScrollListener();
    private ScrollPresenter scrollPresenter = new ScrollPresenter();
    private ScrollInteractor scrollInteractor = new ScrollInteractor();
    private DatumAdapter datumAdapter = new DatumAdapter();
    private ScrollView scrollView = new ScrollView();
    private DataService dataService = new DataService();

    public Assembly(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        component = DaggerAssemblyComponent
                .builder()
                .assembly(this)
                .build();
    }

    public void assemble(){
        component.inject(mainActivity);
        component.inject(scrollListener);
        component.inject(scrollPresenter);
        component.inject(scrollInteractor);
        component.inject(scrollView);
        component.inject(dataService);
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

    @Provides
    @Singleton
    public DatumAdapter provideDatumAdapter(){
        return datumAdapter;
    }

    @Provides
    @Singleton
    public InfiniteScrollListener provideScrollListener(){
        return scrollListener;
    }

    @Provides
    @Singleton
    public DataService provideDataService(){
        return dataService;
    }
}
