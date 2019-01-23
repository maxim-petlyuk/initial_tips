package tooltips.optimizer.com

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.optimizer.tooltips.TipsManager
import com.optimizer.tooltips.animations.AnimationComposer
import com.optimizer.tooltips.animations.fading_in.FadeInAnimator
import com.optimizer.tooltips.animations.fading_out.FadeOutAnimator
import com.optimizer.tooltips.positionStrategy.TooltipFactory
import com.optimizer.tooltips.positionStrategy.TooltipPosition
import com.optimizer.tooltips.positionStrategy.strategies.TipGravity
import com.optimizer.tooltips.tips.ButtonTip
import com.optimizer.tooltips.tips.Tip
import tooltips.optimizer.com.databinding.ActivityMainBinding
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

const val ANIM_DURATION = 300L

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        TipsManager.showTips(binding.root as ViewGroup) { createTipsQueue(LayoutInflater.from(this), binding) }
    }

    private fun createTipsQueue(inflater: LayoutInflater, binding: ActivityMainBinding): Queue<Tip> {
        val menuTipView = createTipView(inflater)
        menuTipView.background = ContextCompat.getDrawable(this, R.drawable.shape_tooltip_window_purple)
        menuTipView.text = "This is menu button. Click on it to open menu!"

        val actionbarMenuTooltip = ButtonTip.Builder()
                .attachTooltipView(menuTipView)
                .withEnterAnimation(AnimationComposer(FadeInAnimator()).duration(ANIM_DURATION))
                .withExitAnimation(AnimationComposer(FadeOutAnimator()).duration(ANIM_DURATION))
                .withPositionStrategy(TooltipFactory.createPositionStrategy(TooltipPosition.BOTTOM, TipGravity.LEFT))
                .withAnchorView(binding.ivActionbarMenu)
                .buildTooltip()

        val moreTipView = createTipView(inflater)
        moreTipView.background = ContextCompat.getDrawable(this, R.drawable.shape_tooltip_window_red)
        moreTipView.text = "This is more button. Click on it to expand menu!"

        val actionbarMoreTooltip = ButtonTip.Builder()
                .attachTooltipView(moreTipView)
                .withEnterAnimation(AnimationComposer(FadeInAnimator()).duration(ANIM_DURATION))
                .withExitAnimation(AnimationComposer(FadeOutAnimator()).duration(ANIM_DURATION))
                .withPositionStrategy(TooltipFactory.createPositionStrategy(TooltipPosition.BOTTOM, TipGravity.RIGHT))
                .withAnchorView(binding.ivActionbarMore)
                .buildTooltip()

        val galleryTipView = createTipView(inflater)
        galleryTipView.background = ContextCompat.getDrawable(this, R.drawable.shape_tooltip_window_red)
        galleryTipView.text = "This is gallery tab"

        val tabGalleryTooltip = ButtonTip.Builder()
                .attachTooltipView(galleryTipView)
                .withEnterAnimation(AnimationComposer(FadeInAnimator()).duration(300))
                .withExitAnimation(AnimationComposer(FadeOutAnimator()).duration(300))
                .withPositionStrategy(TooltipFactory.createPositionStrategy(TooltipPosition.TOP, TipGravity.LEFT))
                .withAnchorView(binding.ivTabGallery)
                .buildTooltip()

        val cameraTipView = createTipView(inflater)
        cameraTipView.background = ContextCompat.getDrawable(this, R.drawable.shape_tooltip_window_red)
        cameraTipView.text = "This is camera tab"

        val tabGameraTooltip = ButtonTip.Builder()
                .attachTooltipView(cameraTipView)
                .withEnterAnimation(AnimationComposer(FadeInAnimator()).duration(ANIM_DURATION))
                .withExitAnimation(AnimationComposer(FadeOutAnimator()).duration(ANIM_DURATION))
                .withPositionStrategy(TooltipFactory.createPositionStrategy(TooltipPosition.TOP, TipGravity.CENTER))
                .withAnchorView(binding.ivTabCamera)
                .buildTooltip()

        val sourceTipView = createTipView(inflater)
        sourceTipView.background = ContextCompat.getDrawable(this, R.drawable.shape_tooltip_window_red)
        sourceTipView.text = "This is just another tab"

        val tabSourceTooltip = ButtonTip.Builder()
                .attachTooltipView(sourceTipView)
                .withEnterAnimation(AnimationComposer(FadeInAnimator()).duration(ANIM_DURATION))
                .withExitAnimation(AnimationComposer(FadeOutAnimator()).duration(ANIM_DURATION))
                .withPositionStrategy(TooltipFactory.createPositionStrategy(TooltipPosition.TOP, TipGravity.RIGHT))
                .withAnchorView(binding.ivTabSource)
                .buildTooltip()

        val tooltipsQueue = LinkedBlockingQueue<Tip>(
                listOf(actionbarMenuTooltip,
                        actionbarMoreTooltip,
                        tabGalleryTooltip,
                        tabGameraTooltip,
                        tabSourceTooltip))

        return tooltipsQueue
    }

    private fun createTipView(inflater: LayoutInflater): TextView {
        val menuTipView = DataBindingUtil.inflate<ViewDataBinding>(inflater, R.layout.item_tooltip, null, false).getRoot() as TextView
        val menuTipParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        menuTipParams.marginStart = resources.getDimensionPixelSize(R.dimen.actionbar_icon_padding)
        menuTipParams.marginEnd = resources.getDimensionPixelSize(R.dimen.actionbar_icon_padding)
        menuTipView.layoutParams = menuTipParams
        return menuTipView
    }
}
