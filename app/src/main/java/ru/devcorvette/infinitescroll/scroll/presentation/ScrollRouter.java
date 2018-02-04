package ru.devcorvette.infinitescroll.scroll.presentation;


import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.presentation.BaseRouter;
import ru.devcorvette.infinitescroll.scroll.assembly.IScrollAssembly;

public class ScrollRouter extends BaseRouter implements IScrollRouter {

    @Inject IScrollAssembly assembly;

    @Override
    public void showPager() {
        addFragment(assembly.getPagerFragment(), true);
    }

}
