package com.pthw.codemanagement.cache.home.typeconverter

import androidx.room.TypeConverter

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class GenresTypeConverter {
    @TypeConverter
    fun toList(genreIds: String): List<Int> {
        val list = mutableListOf<Int>()

        val array = genreIds.split(",".toRegex()).dropLastWhile {
            it.isEmpty()
        }.toTypedArray()

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toInt())
            }
        }
        return list
    }

    @TypeConverter
    fun fromList(list: List<Int>): String {
        var genreIds = ""
        for (i in list) genreIds += ",$i"
        return genreIds
    }
}