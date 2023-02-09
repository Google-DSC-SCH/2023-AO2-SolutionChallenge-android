package com.ao2.run_eat.ui.running

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.ao2.run_eat.R
import com.ao2.run_eat.base.BaseFragment
import com.ao2.run_eat.databinding.FragmentRunningBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunningFragment : BaseFragment<FragmentRunningBinding, RunningViewModel>(R.layout.fragment_running) ,
    OnMapReadyCallback {

    private val TAG = "RunningFragment"
    // GoogleMap - 기본 지도 기능 및 데이터를 관리하기 위한 진입점
    private lateinit var mMap: MapView
    override val layoutResourceId: Int
        get() = R.layout.fragment_running

    override val viewModel : RunningViewModel by viewModels()

    override fun initStartView() {
        binding.apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.errorEvent
        mMap = binding.googleMapView
        mMap.onCreate(Bundle())
        mMap.getMapAsync(this)

    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

    override fun onMapReady(googleMap: GoogleMap) {

        // 초기 위치 설정 및 마커 표시
        val latLng = LatLng(37.566168, 126.901609)
        googleMap.addMarker(MarkerOptions().position(latLng).title("여기"))
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
    }


    override fun onStart() {
        super.onStart()
        mMap.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMap.onStop()
    }

    override fun onResume() {
        super.onResume()
        mMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMap.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMap.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMap.onDestroy()
    }


}