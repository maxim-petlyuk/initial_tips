package com.optimizer.tooltips.position

import com.optimizer.tooltips.entity.Bounds
import com.optimizer.tooltips.entity.Point
import com.optimizer.tooltips.utils.MARGIN_LEFT_INDEX
import com.optimizer.tooltips.utils.MARGIN_RIGHT_INDEX
import com.optimizer.tooltips.utils.MARGIN_TOP_INDEX
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class BottomStrategyTest {

    private lateinit var positionCalculator: PositionCalculator

    private val tipMargins by lazy {
        intArrayOf(20, 20, 20, 20)
    }

    private val tipViewBounds by lazy {
        Bounds(0f, 0f, 300f, 100f)
    }

    private val anchorViewBounds by lazy {
        Bounds(0f, 0f, 100f, 150f)
    }

    @Test
    fun `tip aligned left`() {
        val anchorWindowPosition = Point(0f, 0f)
        positionCalculator = BottomStrategy(TipHorizontalGravity.LEFT)

        val correctPositionX = anchorWindowPosition.x + tipMargins[MARGIN_LEFT_INDEX]
        val correctPositionY = anchorWindowPosition.y + anchorViewBounds.getHeight() + tipMargins[MARGIN_TOP_INDEX]
        val position = positionCalculator.computePosition(tipViewBounds, anchorViewBounds, anchorWindowPosition, tipMargins)

        assertThat("Position X should equal to the left margin", position.x == correctPositionX)
        assertThat("Position Y should equal to the sum of anchor view height and top margin", position.y == correctPositionY)
    }

    @Test
    fun `tip aligned left with extra margin`() {
        val anchorWindowPosition = Point(40f, 20f)
        positionCalculator = BottomStrategy(TipHorizontalGravity.LEFT)

        val correctPositionX = anchorWindowPosition.x + tipMargins[MARGIN_LEFT_INDEX]
        val correctPositionY = anchorWindowPosition.y + anchorViewBounds.getHeight() + tipMargins[MARGIN_TOP_INDEX]
        val position = positionCalculator.computePosition(tipViewBounds, anchorViewBounds, anchorWindowPosition, tipMargins)

        assertThat("Position X should equal to the sum of left margin and anchor`s x position", position.x == correctPositionX)
        assertThat("Position Y should equal to the sum of top margin, anchor view height and anchor`s y position", position.y == correctPositionY)
    }

    @Test
    fun `tip aligned right`() {
        val anchorWindowPosition = Point(350f, 20f)
        positionCalculator = BottomStrategy(TipHorizontalGravity.RIGHT)

        val correctPositionX = anchorWindowPosition.x + anchorViewBounds.getWidth() - tipViewBounds.getWidth() - tipMargins[MARGIN_RIGHT_INDEX]
        val position = positionCalculator.computePosition(tipViewBounds, anchorViewBounds, anchorWindowPosition, tipMargins)

        assertThat("Right edge of the tip view should be aligned to the right edge of the anchored view, " +
                "and don`t forget to correct x coordinate with wished margin", position.x == correctPositionX)
    }

    @Test
    fun `tip aligned center`() {
        val anchorWindowPosition = Point(150f, 20f)
        positionCalculator = BottomStrategy(TipHorizontalGravity.CENTER)

        val correctPositionX = anchorWindowPosition.x + anchorViewBounds.getWidth() / 2 - tipViewBounds.getWidth() / 2
        val position = positionCalculator.computePosition(tipViewBounds, anchorViewBounds, anchorWindowPosition, tipMargins)

        assertThat("Center of the tip view should exactly the center of the anchor view. Notice, that " +
                "margins should be ignored in this case", position.x == correctPositionX)
    }
}