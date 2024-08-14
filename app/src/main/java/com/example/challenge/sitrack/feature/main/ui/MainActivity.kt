package com.example.challenge.sitrack.feature.main.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.challenge.sitrack.R
import com.example.challenge.sitrack.feature.main.di.factory
import com.example.challenge.sitrack.feature.main.ui.models.MainUiModel
import com.example.challenge.sitrack.feature.main.ui.models.RandomUserUiModel
import com.example.challenge.sitrack.databinding.ActivityMainBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels() { factory }
    private lateinit var googleMap: GoogleMap
    private lateinit var bottomSheetFragment: UserBottomSheet


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bottomSheetFragment = UserBottomSheet()
        getMap()
        initUi()
        randomUserObserver()
        viewModelGetRandomUser()
    }

    private fun viewModelGetRandomUser() = viewModel.getRandomUser()

    private fun randomUserObserver() {
        val randomUserObserver = Observer<MainUiModel> {
            binding.mainRefresh.isRefreshing = it.showRefresh
            if(it.randomUserUiModel != null) getRandomUserShowSuccess(it.randomUserUiModel)
            if(it.exception != null) getRandomUserShowError(it.exception)
        }
        viewModel.mainUiState.observe(this,randomUserObserver)
    }

    private fun showBottomSheet() {
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    private fun getMap() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initUi() {
        binding.mainRefresh.setOnRefreshListener { viewModelGetRandomUser() }
        binding.mainRefresh.isRefreshing = true
    }

    private fun getRandomUserShowSuccess(randomUserModel: RandomUserUiModel) {
        showBottomSheet()
        showUserLocation(randomUserModel.position)
    }

    private fun showUserLocation(position: LatLng) {
        googleMap.clear()
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(position.latitude, position.longitude)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 4f))

        googleMap.setOnMarkerClickListener {
            showBottomSheet()
            false
        }
    }

    private fun getRandomUserShowError(exception: Exception) {
        Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
    }

}


