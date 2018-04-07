package ru.devcorvette.infinitescroll.base.presentation;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.R;

public class BaseRouter implements IBaseRouter {

    protected MainActivity mainActivity;

    @Inject
    public BaseRouter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    protected void addFragment(Fragment fragment, boolean isBackStack){
        mainActivity.addFragment(fragment, isBackStack);
    }

    protected void showToastMessage(String message){
        Toast.makeText(
                mainActivity.getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConnectError() {
        showToastMessage(mainActivity.getString(R.string.connect_error));
    }
}
