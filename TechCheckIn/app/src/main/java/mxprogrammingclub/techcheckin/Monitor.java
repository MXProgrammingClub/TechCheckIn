package mxprogrammingclub.techcheckin;

import android.app.Application;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import java.util.List;
import java.util.UUID;

/**
 * Background monitoring for the beacon. Created with http://developer.estimote.com/android/tutorial/part-2-background-monitoring/
 * Created by Julia on 10/27/2016.
 */
public class Monitor extends Application
{
    //Identifiers for the beacon used for this project
    public static final String BEACON_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    public static final int BEACON_MAJOR = 17937, BEACON_MINOR = 35131;

    private BeaconManager manager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        manager = new BeaconManager(getApplicationContext());

        manager.setMonitoringListener(new BeaconManager.MonitoringListener()
        {
            @Override
            public void onEnteredRegion(Region region, List<Beacon> list)
            {
                //method call to ask if user wants to sign in/out
            }
            @Override
            public void onExitedRegion(Region region) {}
        });

        manager.connect(new BeaconManager.ServiceReadyCallback()
        {
            @Override
            public void onServiceReady()
            {
                manager.startMonitoring(new Region("monitored region", UUID.fromString(BEACON_UUID), BEACON_MAJOR, BEACON_MINOR));
            }
        });
    }
}