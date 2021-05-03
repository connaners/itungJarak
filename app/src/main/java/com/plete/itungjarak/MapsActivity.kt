package com.plete.itungjarak

import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)


        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.uiSettings.isZoomControlsEnabled = true

        mMap.setOnCameraIdleListener {
            var currentLocation: LatLng = mMap.cameraPosition.target
            val geocoder = Geocoder(this)
            geocoder.getFromLocation(currentLocation.latitude, currentLocation.longitude, 1)
            mMap.clear()
            mMap.addMarker(MarkerOptions().position(currentLocation).title("opoora")).showInfoWindow()

            var list = LatLng(currentLocation.latitude, currentLocation.longitude)

            val lat1 = currentLocation.latitude.toString()
            val long1 = currentLocation.longitude.toString()

            val lat2 = currentLocation.latitude.toString()
            val long2 = currentLocation.longitude.toString()

            btnSet.setOnClickListener {
                etLat1.setText(lat1)
                etLong1.setText(long1)
            }

            btnSetSet.setOnClickListener {
                etLat2.setText(lat2)
                etLong2.setText(long2)
            }

            btnCalc.setOnClickListener {
                val loc1 = Location("")
                val loc2 = Location("")
                val jarak = loc1.distanceTo(loc2).toString()

                tvHasil.setText(jarak)
            }
        }
    }
}