package org.tiqr.authenticator;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.tiqr.authenticator.general.HeaderView;

/**
 * About screen.
 */
public class AboutActivity extends Activity {
    /**
     * Open URL in browser.
     *
     * @param url url
     */
    private void _openURL(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    /**
     * Create activity.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about);

        HeaderView headerView = (HeaderView)findViewById(R.id.headerView);
        headerView.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        headerView.hideRightButton();

        TextView versionTextView = (TextView)findViewById(R.id.versionName);
        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            versionTextView.setText(getString(R.string.app_name) + " " + versionName);
        } catch (PackageManager.NameNotFoundException e) {
            versionTextView.setVisibility(View.GONE);
        }

        ImageView logoSurfnet = (ImageView)findViewById(R.id.logo_surfnet);
        logoSurfnet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _openURL("http://www.surfnet.nl/");
            }
        });

        ImageView logoEgeniq = (ImageView)findViewById(R.id.logo_egeniq);
        logoEgeniq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _openURL("http://www.egeniq.com/");
            }
        });

        ImageView logoTiqr = (ImageView)findViewById(R.id.logo_tiqr);
        logoTiqr.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _openURL("http://tiqr.org/");
            }
        });

        ImageView logoStroomt = (ImageView)findViewById(R.id.logo_stroomt);
        logoStroomt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                _openURL("http://www.stroomt.com/");
            }
        });
        TextView debugInfo = (TextView)findViewById(R.id.debug_info);
        debugInfo.setText(_getDebugInfos());
    }

    /**
     * Returns informations about the device the app is running on.
     *
     * @return OS and device details as a string, to display to the user.
     */
    private String _getDebugInfos() {
        String result = "Debug informations (make a screenshot of this when submitting a bug report):";
        result += "\n OS Version: " + System.getProperty("os.version") + "(" + android.os.Build.VERSION.INCREMENTAL + ")";
        result += "\n API Level: " + android.os.Build.VERSION.SDK_INT;
        result += "\n Device: " + android.os.Build.DEVICE;
        result += "\n Model and product: " + android.os.Build.MODEL + " (" + android.os.Build.PRODUCT + ")";
        return result;
    }
}
