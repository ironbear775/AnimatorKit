package com.ironbear.android.libanimatorkit.animator

import android.animation.Animator
import androidx.core.animation.*
import com.ironbear.android.libanimatorkit.interpolator.Interpolator

/**
 * BaseAnimator
 *
 * @Author: Xym
 * @Date: 2020-02-10 14:26
 *
 * @property type
 * @property animator
 */
abstract class BaseAnimator(@AnimatorType val type: Int, protected val animator: Animator) {
    var duration = DEFAULT_DURATION
        set(value) {
            animator.duration = value
            field = value
        }
    var interpolator: android.view.animation.Interpolator = Interpolator.linearInterpolator
        set(value) {
            animator.interpolator = value
            field = value
        }
    var startDelay = 0L
        set(value) {
            animator.startDelay = value
            field = value
        }
    var target: Any? = null
        set(value) {
            animator.setTarget(value)
            field = value
        }

    var doOnStart: (animator: Animator) -> Unit = {}
        set(value) {
            animator.doOnStart {
                field.invoke(it)
            }
            field = value
        }
    var doOnPause: (animator: Animator) -> Unit = {}
        set(value) {
            animator.doOnPause {
                field.invoke(it)
            }
            field = value
        }
    var doOnResume: (animator: Animator) -> Unit = {}
        set(value) {
            animator.doOnResume {
                field.invoke(it)
            }
            field = value
        }
    var doOnRepeat: (animator: Animator) -> Unit = {}
        set(value) {
            animator.doOnRepeat {
                field.invoke(it)
            }
            field = value
        }
    var doOnCancel: (animator: Animator) -> Unit = {}
        set(value) {
            animator.doOnCancel {
                field.invoke(it)
            }
            field = value
        }
    var doOnEnd: (animator: Animator) -> Unit = {}
        set(value) {
            animator.doOnEnd {
                field.invoke(it)
            }
            field = value
        }

    var pivotX: Float? = null
    var pivotY: Float? = null

    companion object {
        const val DEFAULT_DURATION = 600L
    }

    /**
     * 获取真正的Animator
     */
    fun getTargetAnimator() = animator
}