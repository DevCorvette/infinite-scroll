package ru.devcorvette.infinitescroll.base.presentation;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.R;

public abstract class BaseRouter implements IBaseRouter {


    protected void addFragment(Fragment fragment, boolean isBackStack){
        MainActivity.getInstance().addFragment(fragment, isBackStack);
    }

    protected void showToastMessage(String message){
        Toast.makeText(
                MainActivity.getInstance().getApplicationContext(),
                message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showConnectError() {
        showToastMessage(MainActivity
                .getInstance()
                .getString(R.string.connect_error));
    }
}
