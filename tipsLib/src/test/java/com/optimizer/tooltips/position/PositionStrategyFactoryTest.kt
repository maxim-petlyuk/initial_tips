package com.optimizer.tooltips.position

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class PositionStrategyFactoryTest {

    @Test
    fun `create vertical top strategy`() {
        val strategy = PositionStrategyFactory.createPositionStrategy(
                TipVerticalGravity.TOP,
                TipHorizontalGravity.CENTER)

        assertThat("Factory should create ${TopStrategy::class.java.name}", strategy is TopStrategy)
    }

    @Test
    fun `create vertical bottom strategy`() {
        val strategy = PositionStrategyFactory.createPositionStrategy(
                TipVerticalGravity.BOTTOM,
                TipHorizontalGravity.CENTER)

        assertThat("Factory should create ${BottomStrategy::class.java.name}", strategy is BottomStrategy)
    }
}