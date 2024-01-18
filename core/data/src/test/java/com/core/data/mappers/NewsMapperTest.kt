package com.core.data.mappers

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.core.data.TestData
import org.junit.jupiter.api.Test

class NewsMapperTest {

    @Test
    fun `Map ArticleDto to Article`() {
        assertThat(TestData.article).isEqualTo(TestData.articleDto.toArticle())
    }
}
