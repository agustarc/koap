package github.agustarc.koap.sample

import github.agustarc.koap.CacheStrategy
import github.agustarc.koap.PreferenceHolder
import github.agustarc.koap.delegator.*
import github.agustarc.koap.inferType

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
object AccountPreference : PreferenceHolder(name = "account", cacheStrategy = CacheStrategy.LAZY) {
    var name: String by ReadWriteString(predicate = { it.length > 2 })
    var age: Int by ReadWriteInt(default = 1, caching = false)
    var hireable: Boolean by ReadWriteBoolean()
    var score: Float by ReadWriteFloat(default = 0.1f)
    var repos: Long by ReadWriteLong()
    var followers: ArrayList<User>? by ReadWriteSerializable(type = inferType<ArrayList<User>>())
    var friend: User? by ReadWriteSerializable(type = inferType<User>())
}