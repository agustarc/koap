package github.agustarc.koap

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
object Errors {
    const val CONTEXT_NOT_SET = "No preferences in PreferenceHolder instance. Add in Application class Koap.bind(applicationContext, ..)."
    const val SERIALIZER_NOT_SET = "Serializer needs to be set if you want to use custom types"
}