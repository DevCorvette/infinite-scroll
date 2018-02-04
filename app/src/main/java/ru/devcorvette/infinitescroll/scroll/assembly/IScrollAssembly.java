package ru.devcorvette.infinitescroll.scroll.assembly;

import android.support.v4.app.Fragment;

import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;

public interface IScrollAssembly {

    Fragment getPagerFragment();

    IPagerPresenter getPagerPresenter();

}
