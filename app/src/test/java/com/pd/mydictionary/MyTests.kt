package com.pd.mydictionary

import com.pd.mydictionary.model.data.Meanings
import com.pd.mydictionary.model.data.SkyEngWords
import com.pd.mydictionary.model.data.SkyEngToHistory
import com.pd.mydictionary.model.data.Translation
import com.pd.mydictionary.room.HistoryEntity
import org.junit.Test

import org.junit.Assert.*


class MyTests {

    @Test
    fun EqualsEmptyString() {
        assertEquals("", String.Companion.getEmptyString())
    }

    @Test
    fun NotEmptyString() {
        assertEquals("1h", String.Companion.getEmptyString())
    }


    @Test
    fun transformToHistory_ToHistory_ArrayEquals() {
        val translation = Translation("text")
        val meanings = listOf(Meanings(translation, "imageUrl"))
        val skyEngWords = listOf(
            SkyEngWords(1, "text1", meanings),
            SkyEngWords(2, "text2", meanings)
        )
        val historyWords = arrayOf(
            HistoryEntity("text1", "text"),
            HistoryEntity("text2", "text")
        )
        assertArrayEquals(
            SkyEngToHistory().skyEngWordsToHistory(skyEngWords).toTypedArray(),
            historyWords
        )
    }

    @Test
    fun transformToHistory_ToHistory_NullError() {
        val translation = Translation("text 2")
        val meanings = listOf(Meanings(translation, "imageUrl"))
        val skyEngWords = listOf(SkyEngWords(1, "text", meanings))

        assertNull(SkyEngToHistory().skyEngWordsToHistory(skyEngWords)[0])
    }

    @Test
    fun transformToHistory_ToHistory_NotNull() {
        val translation = Translation("text 2")
        val meanings = listOf(Meanings(translation, "imageUrl"))
        val skyEngWords = listOf(SkyEngWords(1, "text", meanings))

        assertNotNull(SkyEngToHistory().skyEngWordsToHistory(skyEngWords)[0])
    }

    @Test
    fun transformToHistory_ToHistory_SameError() {
        val translation = Translation("text 2")
        val meanings = listOf(Meanings(translation, "imageUrl"))
        val skyEngWords = listOf(SkyEngWords(1, "text", meanings))
        val historyWords = HistoryEntity("text", "text")

        assertSame(SkyEngToHistory().skyEngWordsToHistory(skyEngWords)[0], historyWords)
    }


}