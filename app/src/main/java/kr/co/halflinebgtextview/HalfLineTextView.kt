package kr.co.halflinebgtextview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import kr.co.halflinebgtextview.databinding.HalfLineTextBinding

open class HalfLineTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleRes) {

    private val binding = HalfLineTextBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.HalfLineTextView).run {

            binding.textView.apply {
                text = getString(R.styleable.HalfLineTextView_text)
                setTextColor(getColor(R.styleable.HalfLineTextView_textColor, 0xFFFF0000.toInt()))
                setTextSize(
                    TypedValue.COMPLEX_UNIT_PX,
                    getDimensionPixelSize(R.styleable.HalfLineTextView_textSize, 11).toFloat()
                )
                binding.bgView.setBackgroundColor(
                    getColor(
                        R.styleable.HalfLineTextView_bgColor,
                        0xFFFF0000.toInt()
                    )
                )
            }
            recycle()
        }

        binding.textView.viewTreeObserver.addOnGlobalLayoutListener {
            val param = binding.bgView.layoutParams

            param.apply {
                width = binding.textView.width
                height = binding.textView.height / 2
            }
            binding.bgView.apply {
                layoutParams = param
            }
        }

    }

    fun setBgColor(color: Int) = binding.bgView.setBackgroundColor(color)
    fun setText(text: String) {
        binding.textView.text = text
    }
    fun setTextColor(color: Int) = binding.textView.setTextColor(color)
}