/*
 * Copyright 2016-2024 JetBrains s.r.o.
 * Use of this source code is governed by the Apache 2.0 License that can be found in the LICENSE.txt file.
 */

package kotlinx.collections.immutable.serialization.util

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.serialization.ImmutableListSerializer
import kotlinx.collections.immutable.serialization.ImmutableMapSerializer
import kotlinx.collections.immutable.serialization.ImmutableSetSerializer
import kotlinx.collections.immutable.serialization.PersistentHashSetSerializer
import kotlinx.collections.immutable.serialization.PersistentListSerializer
import kotlinx.collections.immutable.serialization.PersistentMapSerializer
import kotlinx.collections.immutable.serialization.PersistentSetSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual

internal object JsonConfigurationFactory {
    fun createJsonConfiguration() = Json {
        isLenient = false
        prettyPrint = true
        ignoreUnknownKeys = false
        serializersModule = SerializersModule {
            contextual(ImmutableList::class) {
                ImmutableListSerializer(it.first())
            }
            contextual(PersistentList::class) {
                PersistentListSerializer(it.first())
            }
            contextual(ImmutableSet::class) {
                ImmutableSetSerializer(it.first())
            }
            contextual(PersistentSet::class) {
                PersistentSetSerializer(it.first())
            }
            contextual(ImmutableMap::class) {
                ImmutableMapSerializer(it.first(), it.last())
            }
            contextual(PersistentMap::class) {
                PersistentMapSerializer(it.first(), it.last())
            }
        }
    }
}