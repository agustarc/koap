package github.agustarc.koap.sample

import android.app.Application
import com.google.gson.Gson
import github.agustarc.koap.Koap
import github.agustarc.koap.gson.GsonSerializer

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Koap.serializer = GsonSerializer(Gson())
        Koap.bind(this, AccountPreference)
    }
}