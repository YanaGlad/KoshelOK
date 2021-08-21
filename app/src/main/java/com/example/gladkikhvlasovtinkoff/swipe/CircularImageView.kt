package com.example.gladkikhvlasovtinkoff.swipe

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

class CircularImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    AppCompatImageView(context, attrs, defStyleAttr) {
    private val borderWidth = 0f
    private var canvasSize = 0
    private val shadowRadius = 0f
    private val shadowColor = Color.BLACK
    private var image: Bitmap? = null
    private var paint: Paint? = null
    private var paintBorder: Paint? = null
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)
        paintBorder = Paint(Paint.ANTI_ALIAS_FLAG)
        paintBorder!!.color = Color.TRANSPARENT
    }

    override fun getScaleType(): ScaleType {
        return SCALE_TYPE
    }

    override fun setScaleType(scaleType: ScaleType) {}
    public override fun onDraw(canvas: Canvas) {
        loadBitmap()
        if (image == null) return
        if (!isInEditMode) {
            canvasSize = width
            if (height < canvasSize) {
                canvasSize = height
            }
        }
        val circleCenter = (canvasSize - borderWidth * 2).toInt() / 2
        canvas.drawCircle(
            circleCenter + borderWidth,
            circleCenter + borderWidth,
            circleCenter + borderWidth - (shadowRadius + shadowRadius / 2),
            paintBorder!!
        )
        canvas.drawCircle(
            (width / 2).toFloat(),
            (height / 2).toFloat(),
            circleCenter - (shadowRadius + shadowRadius / 2),
            paint!!
        )
    }

    private fun loadBitmap() {
        if (drawable === getDrawable()) return
        val drawable = getDrawable()
        image = drawableToBitmap(drawable)
        updateShader()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasSize = w
        if (h < canvasSize) canvasSize = h
        if (image != null) updateShader()
    }

    private fun updateShader() {
        if (image == null) return
        image = cropBitmap(image!!)
        val shader = BitmapShader(image!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        val matrix = Matrix()
        matrix.setScale(
            canvasSize.toFloat() / image!!.width.toFloat(), canvasSize.toFloat() / image!!.height
                .toFloat()
        )
        shader.setLocalMatrix(matrix)
        paint!!.shader = shader
    }

    private fun cropBitmap(bitmap: Bitmap): Bitmap {
        val bmp: Bitmap
        bmp = if (bitmap.width >= bitmap.height) {
            Bitmap.createBitmap(
                bitmap,
                bitmap.width / 2 - bitmap.height / 2,
                0,
                bitmap.height, bitmap.height
            )
        } else {
            Bitmap.createBitmap(
                bitmap,
                0,
                bitmap.height / 2 - bitmap.width / 2,
                bitmap.width, bitmap.width
            )
        }
        return bmp
    }

    private fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        var intrinsicWidth = drawable.intrinsicWidth
        var intrinsicHeight = drawable.intrinsicHeight
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            intrinsicWidth = width
            intrinsicHeight = height
        }
        return if (!(intrinsicWidth > 0 && intrinsicHeight > 0)) null else try {
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } catch (e: OutOfMemoryError) {
            Log.e(javaClass.toString(), "Encountered OutOfMemoryError while generating bitmap!")
            null
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureWidth(widthMeasureSpec)
        val height = measureHeight(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    private fun measureWidth(measureSpec: Int): Int {
        val result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        result = if (specMode == MeasureSpec.EXACTLY) {
            specSize
        } else if (specMode == MeasureSpec.AT_MOST) {
            specSize
        } else {
            canvasSize
        }
        return result
    }

    private fun measureHeight(measureSpecHeight: Int): Int {
        val result: Int
        val specMode = MeasureSpec.getMode(measureSpecHeight)
        val specSize = MeasureSpec.getSize(measureSpecHeight)
        result = if (specMode == MeasureSpec.EXACTLY) {
            specSize
        } else if (specMode == MeasureSpec.AT_MOST) {
            specSize
        } else {
            canvasSize
        }
        return result + 2
    }

    companion object {
        private val SCALE_TYPE = ScaleType.CENTER_CROP
    }

    init {
        init(context, attrs, defStyleAttr)
    }
}