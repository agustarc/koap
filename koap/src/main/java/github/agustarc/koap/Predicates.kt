package github.agustarc.koap

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */

val UNSET_PREDICATE: (Any) -> Boolean = { true }
val NULLABLE_UNSET_PREDICATE: (Any?) -> Boolean = { true }

internal fun <T> existsPredicate(predicate: (T) -> Boolean): Boolean = predicate != UNSET_PREDICATE

internal fun <T> existsNullablePredicate(predicate: (T?) -> Boolean): Boolean = predicate != NULLABLE_UNSET_PREDICATE