package com.elliewu.taoyuanapp3

import android.location.Location
import com.elliewu.taoyuanapp3.clusters.ZoneClusterItem

data class MapState(
    val lastKnownLocation: Location?,
    val clusterItems: List<ZoneClusterItem>,
)
