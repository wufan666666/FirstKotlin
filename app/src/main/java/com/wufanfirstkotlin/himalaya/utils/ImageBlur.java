package com.wufanfirstkotlin.himalaya.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

/**
 * @author : wf
 * @date : 2021年10月19日 10:42
 */
public class ImageBlur {
    public static void makeBlur(ImageView imageView,Context context){
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        Bitmap blurRenderScript = blurRenderScript(bitmap, 20, context);
        imageView.setImageBitmap(blurRenderScript);
    }

    private static Bitmap blurRenderScript(Bitmap bitmap, int radius, Context context) {
        bitmap = RGB565toARGB888(bitmap);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        RenderScript renderScript = RenderScript.create(context);
        Allocation fromInBitmap = Allocation.createFromBitmap(renderScript, bitmap);
        Allocation fromOutBitmap = Allocation.createFromBitmap(renderScript, bitmap1);
        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        blur.setInput(fromInBitmap);
        blur.setRadius(radius);
        blur.forEach(fromOutBitmap);
        fromOutBitmap.copyTo(bitmap1);
        renderScript.destroy();
        return bitmap1;
    }

    private static Bitmap RGB565toARGB888(Bitmap bitmap) {
        int numPixels = bitmap.getWidth() * bitmap.getHeight();
        int[] pixels = new int[numPixels];
        bitmap.getPixels(pixels,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());
        Bitmap result = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        result.setPixels(pixels,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());

        return result;
    }
}