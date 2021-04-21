package com.ironbear.android.libanimatorkit.builder

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.Interpolator
import com.ironbear.android.libanimatorkit.animator.BaseAnimator
import com.ironbear.android.libanimatorkit.animator.BaseValueAnimator
import com.ironbear.android.libanimatorkit.animator.TogetherAnimator

/**
 * SingleViewBuilder
 *
 * @Author: Xym
 * @Date: 2020-02-10 10:52
 *
 * SingleViewBuilder中的方法设置后会覆盖动画[BaseAnimator]或[BaseValueAnimator]中同名方法
 * 如[repeatCount]会覆盖[BaseValueAnimator.repeatCount]
 */
class SingleViewBuilder : BaseAnimatorBuilder() {
    private var animator: BaseAnimator? = null
    private var repeatCount: Int? = null
    private var repeatMode: Int? = null
    private var pivotX: Float? = null
    private var pivotY: Float? = null

    init {
        buildFromSingle = true
    }

    /**
     * 设置动画对象View
     *
     * @param view
     * @return
     */
    fun setTarget(view: View): SingleViewBuilder {
        this.view = view
        return this
    }

    /**
     * 给View添加一个动画
     * 会覆盖[BaseValueAnimator]中设置的[BaseValueAnimator.target]
     *
     * @param animator
     * @return
     */
    fun singleAnimator(animator: BaseValueAnimator): SingleViewBuilder {
        this.animator = animator
        return this
    }

    /**
     * 动画重复次数
     *
     * @param repeatCount
     * @return
     */
    fun repeatCount(repeatCount: Int): SingleViewBuilder {
        this.repeatCount = repeatCount
        return this
    }

    /**
     * 动画重复模式 [ValueAnimator.RESTART] or [ValueAnimator.REVERSE]
     * 会覆盖[BaseValueAnimator]中设置的[BaseValueAnimator.repeatMode]
     *
     * @param repeatMode
     * @return
     */
    fun repeatMode(repeatMode: Int): SingleViewBuilder {
        this.repeatMode = repeatMode
        return this
    }

    /**
     * 设置动画X轴基准点
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.pivotX]
     *
     * @param pivotX
     * @return
     */
    fun pivotX(pivotX: Float): SingleViewBuilder {
        this.pivotX = pivotX
        return this
    }

    /**
     * 设置动画Y轴基准点
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.pivotY]
     *
     * @param pivotY
     * @return
     */
    fun pivotY(pivotY: Float): SingleViewBuilder {
        this.pivotY = pivotY
        return this
    }

    /**
     * 设置动画时长
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.duration]
     *
     * @param duration
     * @return
     */
    fun duration(duration: Long): SingleViewBuilder {
        this.duration = duration
        return this
    }

    /**
     * 设置动画差值器
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.interpolator]
     *
     * @param interpolator
     * @return
     */
    fun interpolator(interpolator: Interpolator): SingleViewBuilder {
        this.interpolator = interpolator
        return this
    }

    /**
     * 设置动画延迟播放时长
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.startDelay]
     *
     * @param startDelay
     * @return
     */
    fun startDelay(startDelay: Long): SingleViewBuilder {
        this.startDelay = startDelay
        return this
    }

    /**
     * 添加开始播放监听
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.doOnStart]
     *
     * @param doOnStart
     * @return
     */
    fun doOnStart(doOnStart: ((animator: Animator) -> Unit)): SingleViewBuilder {
        this.doOnStart = doOnStart
        return this
    }

    /**
     * 添加暂停播放监听
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.doOnPause]
     *
     * @param doOnPause
     * @return
     */
    fun doOnPause(doOnPause: ((animator: Animator) -> Unit)): SingleViewBuilder {
        this.doOnPause = doOnPause
        return this
    }

    /**
     * 添加继续播放监听
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.doOnResume]
     *
     * @param doOnResume
     * @return
     */
    fun doOnResume(doOnResume: ((animator: Animator) -> Unit)): SingleViewBuilder {
        this.doOnResume = doOnResume
        return this
    }

    /**
     * 添加重播监听
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.doOnRepeat]
     *
     * @param doOnRepeat
     * @return
     */
    fun doOnRepeat(doOnRepeat: ((animator: Animator) -> Unit)): SingleViewBuilder {
        this.doOnRepeat = doOnRepeat
        return this
    }

    /**
     * 添加取消播放监听
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.doOnCancel]
     *
     * @param doOnCancel
     * @return
     */
    fun doOnCancel(doOnCancel: ((animator: Animator) -> Unit)): SingleViewBuilder {
        this.doOnCancel = doOnCancel
        return this
    }

    /**
     * 添加结束播放监听
     * 会覆盖[BaseAnimator]中设置的[BaseAnimator.doOnEnd]
     *
     * @param doOnEnd
     * @return
     */
    fun doOnEnd(doOnEnd: ((animator: Animator) -> Unit)): SingleViewBuilder {
        this.doOnEnd = doOnEnd
        return this
    }

    /**
     * 添加动画更新监听
     * 会覆盖[BaseValueAnimator]中设置的[BaseValueAnimator.doOnUpdate]
     *
     * @param onUpdate
     * @return
     */
    fun doOnUpdate(onUpdate: ((animator: ValueAnimator) -> Unit)): SingleViewBuilder {
        this.onUpdate = onUpdate
        return this
    }

    override fun build(): Animator? {
        return if (animator != null) buildSingleAnimator(animator)
        else null
    }

    private fun buildSingleAnimator(animator: BaseAnimator?): Animator? {
        animator?.let { anim ->
            view?.let { view ->
                if (anim.pivotX != null || anim.pivotY != null) {
                    anim.pivotX?.let {
                        view.pivotX = it
                    }
                    anim.pivotY?.let {
                        view.pivotY = it
                    }
                    view.invalidate()
                }

                if (pivotX != null || pivotY != null) {
                    pivotX?.let {
                        view.pivotX = it
                    }
                    pivotY?.let {
                        view.pivotY = it
                    }
                    view.invalidate()
                }
                anim.target = view
            }

            duration?.let {
                anim.duration = it
            }

            interpolator?.let {
                anim.interpolator = it
            }

            startDelay?.let {
                anim.startDelay = it
            }

            doOnStart?.let {
                anim.doOnStart = it
            }

            doOnPause?.let {
                anim.doOnPause = it
            }

            doOnResume?.let {
                anim.doOnResume = it
            }

            doOnRepeat?.let {
                anim.doOnRepeat = it
            }

            doOnCancel?.let {
                anim.doOnCancel = it
            }

            doOnEnd?.let {
                anim.doOnEnd = it
            }

            repeatCount?.let {
                (anim as? BaseValueAnimator)?.repeatCount = it
            }

            repeatMode?.let {
                (anim as? BaseValueAnimator)?.repeatMode = it
            }

            onUpdate?.let {
                (anim as? BaseValueAnimator)?.doOnUpdate = it
            }

            (anim as? TogetherAnimator)?.let {
                it.getTargetAnimator()
            }

            return anim.getTargetAnimator()
        }
        return null
    }
}