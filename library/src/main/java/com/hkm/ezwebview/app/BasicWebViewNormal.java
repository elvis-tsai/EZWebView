package com.hkm.ezwebview.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.R;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by hesk on 23/9/15.
 */
public abstract class BasicWebViewNormal extends Fragment {
    protected WebView block;
    protected CircleProgressBar betterCircleBar;
    protected RelativeLayout framer;

    protected int LayoutID() {
        return R.layout.simple_main_mv;
    }

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void initBinding(View v) {
        betterCircleBar = (CircleProgressBar) v.findViewById(R.id.wv_simple_process);
        block = (WebView) v.findViewById(R.id.wv_content_block);
        framer = (RelativeLayout) v.findViewById(R.id.wv_simple_frame);
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBinding(view);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(LayoutID(), container, false);
    }

    protected void completeloading() {
        ViewCompat.animate(betterCircleBar).alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                betterCircleBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // killWebViewLowMemory(null);
    }

    protected void killWebViewLowMemory(WebView mWebView) {
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView == null) {
            if (block.getVisibility() != View.GONE && block != null) {
                block.setVisibility(View.GONE);
                block.loadUrl("about:blank");
                block.destroy();
            }
        } else if (mWebView.getVisibility() != View.GONE) {
            mWebView.setVisibility(View.GONE);
            mWebView.loadUrl("about:blank");
            mWebView.destroy();
        }
    }

    protected void killWebView(WebView mWebView) {
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView == null) {
            if (block.getVisibility() != View.GONE && block != null) {
                block.setVisibility(View.GONE);
                block.loadUrl("about:blank");
                //block.destroy();
            }
        } else if (mWebView.getVisibility() != View.GONE) {
            mWebView.setVisibility(View.GONE);
            mWebView.loadUrl("about:blank");
            //mWebView.destroy();
        }
    }

    protected void killWebViewDefault() {
        killWebView(null);
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (block != null) {
            block.onPause();
        }
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (block != null) {
            block.onResume();
        }
    }

}
