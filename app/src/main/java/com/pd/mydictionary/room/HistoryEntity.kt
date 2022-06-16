package com.pd.mydictionary.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pd.mydictionary.DESCRIPTION
import com.pd.mydictionary.WORD

@Entity(indices = arrayOf(Index(value = arrayOf(WORD), unique = true)))
class HistoryEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = WORD)
    var word: String, @field:ColumnInfo(name = DESCRIPTION)
    var description: String?
)