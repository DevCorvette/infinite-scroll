package ru.devcorvette.infinitescroll.scroll.presentation;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.base.presentation.BaseRouter;

public class ScrollRouter extends BaseRouter implements IScrollRouter {

//    @Inject IScrollAssembly assembly;

    @Inject
    public ScrollRouter(MainActivity mainActivity) {
        super(mainActivity);
    }

    @Override
    public void showPager() {
//        addFragment(assembly.getPagerFragment(), true);
    }

}
