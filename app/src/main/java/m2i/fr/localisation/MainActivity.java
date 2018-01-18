package m2i.fr.localisation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView tvLatitude;
    TextView tvLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitude = findViewById(R.id.latitudeTxt);
        tvLongitude = findViewById(R.id.longitudeTxt);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.LOCATION_HARDWARE}, 1);


    }

    public void lirePositionGps(View v) {

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "Localisation non disponoble", Toast.LENGTH_LONG).show();
            return;
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "GPS non autorisé", Toast.LENGTH_LONG).show();
            return;
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, this);


    }

    public void lirePositionReseau(View v) {

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            Toast.makeText(this, "Localisation reseau non disponoble", Toast.LENGTH_LONG).show();
            return;
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "GPS non autorisé", Toast.LENGTH_LONG).show();
            return;
        }

        //vérification toute les 1000 milliseconde et 1F pour 1m
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1F, this);
    }


    @Override
    public void onLocationChanged(Location location) {

        tvLatitude.setText(String.format("%.5f", location.getLatitude()));
        tvLongitude.setText(String.format("%.5f", location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
