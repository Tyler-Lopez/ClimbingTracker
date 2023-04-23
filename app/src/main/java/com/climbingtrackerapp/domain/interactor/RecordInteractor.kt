package com.climbingtrackerapp.domain.interactor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/** Architecture logic from https://github.com/Esri/arcgis-runtime-demos-android/blob/master/android-demos/GeotriggerMonitoring/GeotriggerMonitoringDemo-WithGeotriggers/app/src/main/java/com/arcgisruntime/sample/geotriggermonitoringdemo/domain/GeotriggerMonitoringService.kt **/
class RecordInteractor @Inject constructor(
    @ApplicationContext private val context: Context
) {
    

}
