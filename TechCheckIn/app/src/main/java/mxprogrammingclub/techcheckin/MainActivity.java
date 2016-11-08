package mxprogrammingclub.techcheckin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.estimote.sdk.SystemRequirementsChecker;


public class MainActivity extends AppCompatActivity
{
    private Button button = (Button)findViewById(R.id.button);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //from http://developer.estimote.com/android/tutorial/part-1-setting-up/
    @Override
    protected void onResume()
    {
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

    public void toggleEnter(boolean entered)
    {
        button.setEnabled(entered);
    }
}
