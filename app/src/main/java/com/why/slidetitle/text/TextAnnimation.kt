package com.why.slidetitle.text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat

/**
 * @ClassName: TextAnnimation
 * @Description: java类作用描述
 * @Author: wanghaiyang91
 * @Date: 4/2/21 3:17 PM
 */
class TextAnnimation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    //绘制前面文字的画笔
    val beforePaint = Paint()
    //绘制点X
    var beforeStartX = 0f
    //绘制点Y
    var beforeY = 0f;
    //绘制的文字
    var text = "测试Text动画"
    set(value) {
        field = value
        invalidate()
    }

    var rect = Rect()

    var descent = 0f
    var ascent = 0f



    var fixedLeft = true;
    var textPercent = 0f

    var clipStartX = 0f;
    var clipEndX = 0f
    //文字的宽度
    var textAniWidth = 0f;

    //绘制后面文字的画笔
    val afterPaint = Paint()

    override fun onDraw(canvas: Canvas?) {
//        super.onDraw(canvas)
        drawAfterText(canvas)
        drawBeforeText(canvas)
    }

    private fun drawBeforeText(canvas: Canvas?){
        drawTextAni(true, canvas)
    }

    private fun drawAfterText(canvas: Canvas?){
        drawTextAni(false, canvas)
    }

    fun updatePercentAndSide(percent: Float, side: Boolean){
        textPercent = percent
        fixedLeft = side
        invalidate()
    }

    private fun drawTextAni(before: Boolean, canvas: Canvas?){
        canvas?.let {
            canvas.save()
            beforePaint.isAntiAlias = true
            beforePaint.textSize = 50f
            textAniWidth = beforePaint.measureText(text)
            beforePaint.color = if(!before) ContextCompat.getColor(context, android.R.color.black) else ContextCompat.getColor(context, android.R.color.holo_red_dark)
            beforeStartX = width / 2 - textAniWidth / 2
            descent = beforePaint.fontMetrics.descent
            ascent = beforePaint.fontMetrics.ascent
            beforeY = height / 2 + (descent - ascent) / 2 - beforePaint.fontMetrics.descent
            if(fixedLeft){
                if(before){
                    clipStartX = beforeStartX
                    clipEndX = beforeStartX + textAniWidth * textPercent
                    rect.set(clipStartX.toInt() , 0, clipEndX.toInt(), height)
                }else{
                    clipStartX = beforeStartX + textAniWidth * textPercent
                    clipEndX = beforeStartX + textAniWidth
                    rect.set(clipStartX.toInt(), 0, clipEndX.toInt(), height)
                }
            }else{
                if(before){
                    clipStartX = beforeStartX + textAniWidth * textPercent
                    clipEndX = beforeStartX + textAniWidth
                    rect.set(clipStartX.toInt() , 0, clipEndX.toInt(), height)
                }else{
                    clipStartX = beforeStartX
                    clipEndX = beforeStartX + textAniWidth * textPercent
                    rect.set(clipStartX.toInt(), 0, clipEndX.toInt(), height)
                }
            }

            it.clipRect(rect)
            it.drawText(text, beforeStartX, beforeY, beforePaint)
            canvas.restore()
        }
    }

}