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

    @Test
    fun `Map ArticleDto to ArticleEntity`() {
        assertThat(TestData.articleEntity).isEqualTo(TestData.articleDto.toEntity())
    }

    @Test
    fun `Map ArticleEntity to Article`() {
        assertThat(TestData.article).isEqualTo(TestData.articleEntity.toArticle())
    }
}
