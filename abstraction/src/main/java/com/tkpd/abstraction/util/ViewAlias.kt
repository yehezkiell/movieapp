package com.tkpd.abstraction.util

/**
 * Created by Yehezkiel on 13/09/20
 */

import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.item_error_view.*
import kotlinx.android.synthetic.main.item_loading_view.*

inline val Fragment.getErrorLayout: LinearLayout get() = error_view
inline val Fragment.getLoadingLayout: FrameLayout get() = progress_bar_container