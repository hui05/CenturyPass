package infrastructure.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioButton;

import sjtb.e_nci.com.mylibrary.R;


/**
 * Created by hui on 2016/7/19 0019.
 *
 * @author hui
 *         <p>
 *         自定义RadioButton 用来解决图片显示过大的问题
 */
public class MainRadioButton extends RadioButton {

    private int mDrawableSize;// xml文件中设置的大小

    public MainRadioButton(Context context) {
        this(context, null, 0);
    }

    public MainRadioButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        Drawable drawableLeft = null, drawableTop = null, drawableRight = null, drawableBottom =
                null;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MainRadioButton);

        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            Log.i("MainRadioButton", "attr:" + attr);
            if (attr == R.styleable.MainRadioButton_MainRadioButton_drawableSize) {
                mDrawableSize = a.getDimensionPixelSize(R.styleable
                        .MainRadioButton_MainRadioButton_drawableSize, 50);
                Log.i("MainRadioButton", "mDrawableSize:" + mDrawableSize);

            } else if (attr == R.styleable.MainRadioButton_MainRadioButton_drawableTop) {
                drawableTop = a.getDrawable(attr);

            } else if (attr == R.styleable.MainRadioButton_MainRadioButton_drawableBottom) {
                drawableRight = a.getDrawable(attr);

            } else if (attr == R.styleable.MainRadioButton_MainRadioButton_drawableRight) {
                drawableBottom = a.getDrawable(attr);

            } else if (attr == R.styleable.MainRadioButton_MainRadioButton_drawableLeft) {
                drawableLeft = a.getDrawable(attr);

            } else {
            }
        }
        a.recycle();

        setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight,
                drawableBottom);
    }

    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable
            right, Drawable bottom) {

        if (left != null) {
            left.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (right != null) {
            right.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (top != null) {
            top.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mDrawableSize, mDrawableSize);
        }
        setCompoundDrawables(left, top, right, bottom);
    }

}
