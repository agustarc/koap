package github.agustarc.koap

import java.io.Serializable
import java.lang.reflect.*

/**
 * @author Leopold Baik
 * https://medium.com/@joongwon
 */
abstract class TypeToken<T> protected constructor() {
    private val superclass = javaClass.genericSuperclass as ParameterizedType
    val type: Type = canonicalize(superclass.actualTypeArguments[0])

    private class ParameterizedTypeImpl(ownerType: Type?, rawType: Type, vararg typeArguments: Type) :
        ParameterizedType,
        Serializable {
        private val ownerType: Type?
        private val rawType: Type
        private val typeArguments: List<Type>

        init {
            // require an owner inferType if the raw inferType needs it
            if (rawType is Class<*>) {
                val isStaticOrTopLevelClass = Modifier.isStatic(rawType.modifiers) || rawType.enclosingClass == null
                checkArgument(ownerType != null || isStaticOrTopLevelClass)
            }

            this.ownerType = if (ownerType == null) null else canonicalize(ownerType)
            this.rawType = canonicalize(rawType)
            this.typeArguments = typeArguments.map { canonicalize(it) }
        }

        override fun getActualTypeArguments() = typeArguments.toTypedArray()

        override fun getRawType() = rawType

        override fun getOwnerType() = ownerType

        override fun toString(): String = buildString(30 * (typeArguments.size + 1)) {
            append(typeToString(rawType))

            if (typeArguments.isEmpty()) {
                return toString()
            }

            append("<").append(typeToString(typeArguments[0]))
            for (i in 1 until typeArguments.size) {
                append(", ").append(typeToString(typeArguments[i]))
            }

            append(">").toString()
        }
    }

    private class GenericArrayTypeImpl(componentType: Type) : GenericArrayType, Serializable {
        private val componentType: Type = canonicalize(componentType)

        override fun getGenericComponentType() = componentType

        override fun toString() = typeToString(componentType) + "[]"
    }

    private class WildcardTypeImpl(upperBounds: Array<Type>, lowerBounds: Array<Type>) : WildcardType, Serializable {
        private val upperBound: Type
        private val lowerBound: Type?

        init {
            checkArgument(lowerBounds.size <= 1)
            checkArgument(upperBounds.size == 1)

            if (lowerBounds.size == 1) {
                checkNotPrimitive(lowerBounds[0])
                checkArgument(upperBounds[0] === Any::class.java)
                this.lowerBound = canonicalize(lowerBounds[0])
                this.upperBound = Any::class.java

            } else {
                checkNotPrimitive(upperBounds[0])
                this.lowerBound = null
                this.upperBound = canonicalize(upperBounds[0])
            }
        }

        override fun getUpperBounds() = arrayOf(upperBound)

        override fun getLowerBounds() = if (lowerBound != null) arrayOf(lowerBound) else EMPTY_TYPE_ARRAY

        override fun toString(): String = when {
            lowerBound != null -> "? super " + typeToString(lowerBound)
            upperBound === Any::class.java -> "?"
            else -> "? extends " + typeToString(upperBound)
        }
    }

    companion object {
        internal val EMPTY_TYPE_ARRAY = arrayOf<Type>()

        internal fun canonicalize(type: Type): Type = when (type) {
            is Class<*> -> if (type.isArray) GenericArrayTypeImpl(canonicalize(type.componentType)) else type
            is ParameterizedType -> ParameterizedTypeImpl(type.ownerType, type.rawType, *type.actualTypeArguments)
            is GenericArrayType -> GenericArrayTypeImpl(type.genericComponentType)
            is WildcardType -> WildcardTypeImpl(type.upperBounds, type.lowerBounds)
            else -> type
        }

        internal fun typeToString(type: Type): String {
            return if (type is Class<*>) type.name else type.toString()
        }

        internal fun checkNotPrimitive(type: Type) {
            checkArgument(type !is Class<*> || !type.isPrimitive)
        }

        internal fun checkArgument(condition: Boolean) {
            if (!condition) throw IllegalArgumentException()
        }
    }
}

