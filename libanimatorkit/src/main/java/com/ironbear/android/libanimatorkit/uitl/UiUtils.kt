package com.ironbear.android.libanimatorkit.uitl

import com.ironbear.android.libanimatorkit.animator.*

/**
 * UiUtils
 *
 * @Author: Xym
 * @Date: 2020-02-12 10:40
 */
internal object UiUtils {
    /**
     * 动画是否为Float或Int范围自定义动画
     *
     * @param type
     */
    fun isValueType(@AnimatorType type: Int) = type == RANGE_FLOAT_TYPE || type == RANGE_INT_TYPE

    /**
     * 动画是否为多个同时播放
     *
     * @param type
     */
    fun isTogetherType(@AnimatorType type: Int) = type == TOGETHER_TYPE
}