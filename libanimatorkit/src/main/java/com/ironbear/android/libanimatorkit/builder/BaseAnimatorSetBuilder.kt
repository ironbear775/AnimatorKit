package com.ironbear.android.libanimatorkit.builder

import android.animation.Animator
import android.animation.AnimatorSet
import android.view.View
import android.view.animation.Interpolator
import androidx.core.animation.*
import com.ironbear.android.libanimatorkit.uitl.UiUtils
import com.ironbear.android.libanimatorkit.animator.BaseAnimator
import com.ironbear.android.libanimatorkit.animator.TogetherAnimator

/**
 * BaseAnimatorSetBuilder
 *
 * @Author: Xym
 * @Date: 2020-02-12 10:49
 *
 * @property target
 * @property animators
 * @property buildFromSingle
 */
abstract class BaseAnimatorSetBuilder(protected val target: View? = null, protected val animators: List<BaseAnimator>, protected val buildFromSingle: Boolean) {
    protected var duration: Long? = null
    protected var interpolator: Interpolator? = null
    protected var startDelay: Long? = null

    protected var doOnStart: ((animator: Animator) -> Unit)? = null
    protected var doOnPause: ((animator: Animator) -> Unit)? = null
    protected var doOnResume: ((animator: Animator) -> Unit)? = null
    protected var doOnRepeat: ((animator: Animator) -> Unit)? = null
    protected var doOnCancel: ((animator: Animator) -> Unit)? = null
    protected var doOnEnd: ((animator: Animator) -> Unit)? = null

    abstract fun build(): Animator?

    protected fun prepareForBuild(list: List<BaseAnimator>, buildFromSingle: Boolean) {
        if (!buildFromSingle) {
            buildAttrs(list)
            buildPivot(list)
            return
        }

        if (target == null) {
            return
        }

        list.forEach { baseAnimator ->
            if (!UiUtils.isValueType(baseAnimator.type)) {
                if (UiUtils.isTogetherType(baseAnimator.type)) {
                    setTogetherAnimatorAttrs((baseAnimator as? TogetherAnimator), true)
                }
                baseAnimator.target = target
            }
        }

        buildPivot(list)
    }

    private fun buildAttrs(animators: List<BaseAnimator>) {
        animators.forEach {
            if (UiUtils.isTogetherType(it.type)) {
                setTogetherAnimatorAttrs(it as? TogetherAnimator)
            }
        }
    }

    private fun setTogetherAnimatorAttrs(togetherAnimator: TogetherAnimator?, setTarget: Boolean = false) {
        (togetherAnimator?.getTargetAnimator() as? AnimatorSet)?.childAnimations?.forEachIndexed { index, realAnimator ->
            val savedAnimator = togetherAnimator.saveAnimators[index]
            if (setTarget) {
                savedAnimator.target = target
                realAnimator.setTarget(target)
            }

            val pivotX = savedAnimator.pivotX
            val pivotY = savedAnimator.pivotY
            realAnimator.run {
                if (savedAnimator.target != null && (savedAnimator.target is View) && (pivotX != null || pivotY != null)) {
                    (savedAnimator.target as View).let { view ->
                        pivotX?.let {
                            view.pivotX = it
                        }
                        pivotY?.let {
                            view.pivotX = it
                        }
                        view.invalidate()
                    }
                }
            }
        }
    }

    private fun assemblePivot(animator: BaseAnimator) {
        animator.run {
            if (target != null && (target is View) && (pivotX != null || pivotY != null)) {
                (target as View).let { view ->
                    pivotX?.let {
                        view.pivotX = it
                    }
                    pivotY?.let {
                        view.pivotX = it
                    }
                    view.invalidate()
                }
            }
        }
    }

    private fun buildPivot(animators: List<BaseAnimator>) {
        animators.forEach {
            assemblePivot(it)
        }
    }

    protected fun buildBaseAttr(animator: Animator) {
        duration?.let {
            animator.duration = it
        }

        interpolator?.let {
            animator.interpolator = it
        }

        startDelay?.let {
            animator.startDelay = it
        }

        doOnStart?.let {
            animator.doOnStart(it)
        }

        doOnPause?.let {
            animator.doOnPause(it)
        }

        doOnResume?.let {
            animator.doOnResume(it)
        }

        doOnRepeat?.let {
            animator.doOnRepeat(it)
        }

        doOnCancel?.let {
            animator.doOnCancel(it)
        }

        doOnEnd?.let {
            animator.doOnEnd(it)
        }
    }

    /**
     * 设置动画集合时长
     * 会覆盖每个动画中设置的动画时长
     *
     * @param duration
     * @return
     */
    fun duration(duration: Long): BaseAnimatorSetBuilder {
        this.duration = duration
        return this
    }

    /**
     * 设置动画集合差值器
     * 会覆盖每个动画中设置的差值器
     *
     * @param interpolator
     * @return
     */
    fun interpolator(interpolator: Interpolator): BaseAnimatorSetBuilder {
        this.interpolator = interpolator
        return this
    }

    /**
     * 设置动画集合延迟播放时长
     * 不会覆盖每个动画中设置的延迟播放时长
     *
     * @param startDelay
     * @return
     */
    fun startDelay(startDelay: Long): BaseAnimatorSetBuilder {
        this.startDelay = startDelay
        return this
    }

    /**
     * 添加动画集合的开始播放监听
     * 不会覆盖每个动画中设置的开始播放监听
     *
     * @param doOnStart
     * @return
     */
    fun doOnStart(doOnStart: ((animator: Animator) -> Unit)): BaseAnimatorSetBuilder {
        this.doOnStart = doOnStart
        return this
    }

    /**
     * 添加动画集合的暂停播放监听
     * 不会覆盖每个动画中设置的暂停播放监听
     *
     * @param doOnPause
     * @return
     */
    fun doOnPause(doOnPause: ((animator: Animator) -> Unit)): BaseAnimatorSetBuilder {
        this.doOnPause = doOnPause
        return this
    }

    /**
     * 添加动画集合的继续播放监听
     * 不会覆盖每个动画中设置的继续播放监听
     *
     * @param doOnResume
     * @return
     */
    fun doOnResume(doOnResume: ((animator: Animator) -> Unit)): BaseAnimatorSetBuilder {
        this.doOnResume = doOnResume
        return this
    }

    /**
     * 添加动画集合的重播监听
     * 不会覆盖每个动画中设置的重播监听
     *
     * @param doOnRepeat
     * @return
     */
    fun doOnRepeat(doOnRepeat: ((animator: Animator) -> Unit)): BaseAnimatorSetBuilder {
        this.doOnRepeat = doOnRepeat
        return this
    }

    /**
     * 添加动画集合的取消播放监听
     * 不会覆盖每个动画中设置的取消播放监听
     *
     * @param doOnCancel
     * @return
     */
    fun doOnCancel(doOnCancel: ((animator: Animator) -> Unit)): BaseAnimatorSetBuilder {
        this.doOnCancel = doOnCancel
        return this
    }

    /**
     * 添加动画集合的结束播放监听
     * 不会覆盖每个动画中设置的结束播放监听
     *
     * @param doOnEnd
     * @return
     */
    fun doOnEnd(doOnEnd: ((animator: Animator) -> Unit)): BaseAnimatorSetBuilder {
        this.doOnEnd = doOnEnd
        return this
    }
}