package com.optimizer.tooltips.positionStrategy;

import com.optimizer.tooltips.positionStrategy.strategies.BottomStrategy;
import com.optimizer.tooltips.positionStrategy.strategies.PositionStrategy;
import com.optimizer.tooltips.positionStrategy.strategies.TipGravity;
import com.optimizer.tooltips.positionStrategy.strategies.TopStrategy;

public class TooltipFactory {

    public static PositionStrategy createPositionStrategy(TooltipPosition position, TipGravity gravity) {
        if (position == null) {
            throw new NullPointerException();
        }

        switch (position) {
            case TOP:
                return new TopStrategy(gravity);

            case BOTTOM:
                return new BottomStrategy(gravity);

            default:
                throw new RuntimeException(String.format("Unknown position strategy %s", position.toString()));
        }
    }
}
