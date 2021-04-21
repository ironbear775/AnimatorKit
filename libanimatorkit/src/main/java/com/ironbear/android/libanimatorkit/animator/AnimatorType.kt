package com.ironbear.android.libanimatorkit.animator

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(
    UNKNOWN_TYPE,
    SCALE_X_TYPE,
    SCALE_Y_TYPE,
    SCALE_TYPE,
    TRANSLATION_X_TYPE,
    TRANSLATION_Y_TYPE,
    TRANSLATION_TYPE,
    ALPHA_TYPE,
    FADE_IN_TYPE,
    FADE_OUT_TYPE,
    ROTATION_X_TYPE,
    ROTATION_Y_TYPE,
    ROTATION_TYPE,
    RANGE_INT_TYPE,
    RANGE_FLOAT_TYPE
)
annotation class AnimatorType

const val UNKNOWN_TYPE = -1
const val SCALE_X_TYPE = 0
const val SCALE_Y_TYPE = SCALE_X_TYPE + 1
const val SCALE_TYPE = SCALE_Y_TYPE + 1

const val TRANSLATION_X_TYPE = SCALE_TYPE + 1
const val TRANSLATION_Y_TYPE = TRANSLATION_X_TYPE + 1
const val TRANSLATION_TYPE = TRANSLATION_Y_TYPE + 1

const val ALPHA_TYPE = TRANSLATION_TYPE + 1
const val FADE_IN_TYPE = ALPHA_TYPE + 1
const val FADE_OUT_TYPE = FADE_IN_TYPE + 1

const val ROTATION_X_TYPE = FADE_OUT_TYPE + 1
const val ROTATION_Y_TYPE = ROTATION_X_TYPE + 1
const val ROTATION_TYPE = ROTATION_Y_TYPE + 1

const val RANGE_INT_TYPE = ROTATION_TYPE + 1
const val RANGE_FLOAT_TYPE = RANGE_INT_TYPE + 1
const val TOGETHER_TYPE = RANGE_FLOAT_TYPE + 1




