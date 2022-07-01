package com.pd.mydictionary.model.data

import com.pd.mydictionary.room.HistoryEntity

class SkyEngToHistory {
    fun skyEngWordsToHistory(skyEngWords: List<SkyEngWords>): MutableList<HistoryEntity> {

        val historyWords = mutableListOf<HistoryEntity>()

        skyEngWords.forEach {
            historyWords.add(
                HistoryEntity(
                    it.text,
                    it.meanings[0].translation.toString()
                )
            )
        }
        return historyWords
    }
}