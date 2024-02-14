package com.artemissoftware.newsroom.core.database.typeconvertor

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.artemissoftware.newsroom.core.model.Source

@ProvidedTypeConverter
class SourceTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String {
        // TODO: tentar meter mais argumentos aqui para ver como a migração da base de dados vai funcionar
        // return "${source.id},${source.name}"
        return source.name
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(',').let { sourceArray ->
            // Source(id = sourceArray[0], name = sourceArray[1])
            Source(name = sourceArray[0])
        }
    }
}
