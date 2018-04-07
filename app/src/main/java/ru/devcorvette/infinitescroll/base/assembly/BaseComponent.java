package ru.devcorvette.infinitescroll.base.assembly;

import dagger.Component;

@Component(modules = BaseAssembly.class)
public interface BaseComponent {

    void inject(BaseAssembly assembly);

}
