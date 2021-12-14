package com.pouyaheydari.android.evehiclerenting.presentation.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

fun bitmapDescriptor(@DrawableRes icon: Int, resources: Resources): BitmapDescriptor {
    val b = BitmapFactory.decodeResource(resources, icon)
    val smallMarker = Bitmap.createScaledBitmap(b, 100, 100, false)
    return BitmapDescriptorFactory.fromBitmap(smallMarker)
}
