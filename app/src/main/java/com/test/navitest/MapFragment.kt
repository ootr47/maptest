package com.test.navitest

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.navigation.NavigationView
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.test.navitest.databinding.FragmentMapBinding


class MapFragment : Fragment(), OnMapReadyCallback{
	lateinit var binding:FragmentMapBinding
	private lateinit var locationSource: FusedLocationSource
	private lateinit var naverMap: NaverMap

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		locationSource =
			FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)
	}



	override fun onRequestPermissionsResult(requestCode: Int,
											permissions: Array<String>,
											grantResults: IntArray) {
		if (locationSource.onRequestPermissionsResult(requestCode, permissions,
				grantResults)) {
			if (!locationSource.isActivated) { // 권한 거부됨
				naverMap.locationTrackingMode = LocationTrackingMode.None
			}
			return
		}
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		// Inflate the layout for this fragment
		binding = FragmentMapBinding.inflate(inflater, container, false)

		val fm = childFragmentManager
		val mapFragment = fm.findFragmentById(R.id.map) as MapFragment?
			?: MapFragment.newInstance().also {
				fm.beginTransaction().add(R.id.map, it).commit()
			}
		//mapFragment.getMapAsync(this)

		return binding.root

	}


	override fun onMapReady(p0: NaverMap) {
		this.naverMap = naverMap
		naverMap.locationSource = locationSource
		naverMap.locationTrackingMode = LocationTrackingMode.Follow
	}
	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
	}

}