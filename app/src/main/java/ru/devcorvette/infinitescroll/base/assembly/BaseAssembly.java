package ru.devcorvette.infinitescroll.base.assembly;

import android.util.Log;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.base.logic.IBaseInteractor;
import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;
import ru.devcorvette.infinitescroll.base.presentation.IBaseRouter;
import ru.devcorvette.infinitescroll.base.presentation.view.IBaseView;

@Module
public class BaseAssembly<
        Presenter extends IBasePresenter,
        View extends IBaseView,
        Interactor extends IBaseInteractor,
        Router extends IBaseRouter> {

    private static final String TAG = MainActivity.TAG + BaseAssembly.class.getSimpleName();

    private MainActivity mainActivity;
    private BaseComponent component;

    private Class<Presenter> presenterClass;
    private Class<View> viewClass;
    private Class<Interactor> interactorClass;
    private Class<Router> routerClass;

    private Presenter presenter;
    private View view;

    public BaseAssembly(MainActivity mainActivity,
                         Class<Presenter> presenterClass,
                         Class<View> viewClass,
                         Class<Interactor> interactorClass,
                         Class<Router> routerClass) {

        this.presenterClass = presenterClass;
        this.viewClass = viewClass;
        this.interactorClass = interactorClass;
        this.routerClass = routerClass;
        this.mainActivity = mainActivity;

        component = DaggerBaseComponent
                .builder()
                .baseAssembly(this)
                .build()
                .inject(this);
    }

    @Provides
    @Singleton
    public Presenter providePresenter(IBaseView view, IBaseInteractor interactor, IBaseRouter router){
        try {
            presenter = presenterClass
                    .getConstructor(IBaseView.class, IBaseInteractor.class, IBaseRouter.class)
                    .newInstance(view, interactor, router);
        } catch (Exception ex) {
            Log.e(TAG, "Did not provide presenter.", ex);
        }

        return presenter;
    }

    @Provides
    @Singleton
    public View provideView(){
        try {
            view = viewClass.newInstance();
        } catch (Exception ex) {
            Log.e(TAG, "Did not provide view.", ex);
        }

        return view;
    }

    @Provides
    @Singleton
    public Interactor provideInteractor(IBasePresenter presenter){
        try {

            return interactorClass.getConstructor(IBasePresenter.class).newInstance(presenter);

        } catch (Exception ex) {
            Log.e(TAG, "Did not provide interactor.", ex);
            return null;
        }
    }

    @Provides
    @Singleton
    public Router provideRouter(){
        try {

            return routerClass.getConstructor(MainActivity.class).newInstance(mainActivity);

        } catch (Exception ex) {
            Log.e(TAG, "Did not provide router.", ex);
            return null;
        }
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public View getView() {
        return view;
    }
}
