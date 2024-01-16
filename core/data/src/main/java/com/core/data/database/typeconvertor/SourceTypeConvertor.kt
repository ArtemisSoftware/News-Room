package com.core.data.database.typeconvertor

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.core.domain.models.Source

@ProvidedTypeConverter
class SourceTypeConvertor {

    @TypeConverter
    fun sourceToString(source: Source): String{
        //return "${source.id},${source.name}"
        return source.name
    }

    @TypeConverter
    fun stringToSource(source: String): Source{
        return source.split(',').let { sourceArray ->
            // Source(id = sourceArray[0], name = sourceArray[1])
            Source(name = sourceArray[0])
        }
    }
}