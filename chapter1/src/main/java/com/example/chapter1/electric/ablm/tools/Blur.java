package com.example.chapter1.electric.ablm.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

/**
 * Created by Chenbocong on 2016/8/30.
 */

public class Blur {
    private static final String TAG = "Blur";
    Context context;
    Bitmap blurBitmap, sentBitmap;
    RenderScript rs;
    Allocation input, output;
    ScriptIntrinsicBlur script;
    int width, height, wm, hm, wh, div, rsum, gsum, bsum, x, y, i, p, yp, yi, yw, divsum, stackpointer, stackstart, rbs, r1, routsum, rinsum, ginsum, binsum, goutsum, boutsum;
    int[] pix, r, g, b, vmin, dv, sir;
    int[][] stack;

    public Blur(Context context, Bitmap sentBitmap) {
        if (sentBitmap == null) {
            return;
        }
        this.context = context;
        this.sentBitmap = sentBitmap;
        try {
            blurBitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            Bitmap smallBitmap = getResizedBitmap(sentBitmap, sentBitmap.getWidth() / 2, sentBitmap.getHeight() / 2);
            blurBitmap = smallBitmap.copy(smallBitmap.getConfig(), true);
        }
        initRes(context);
    }

    private Bitmap getResizedBitmap(Bitmap bm, int newWidthPX, int newHeightPX) {

        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidthPX) / width;
        float scaleHeight = ((float) newHeightPX) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap;
        try {
            resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width, height, matrix, false);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            resizedBitmap = Bitmap.createBitmap(
                    bm, 0, 0, width / 2, height / 2, matrix, false);
        }
        bm.recycle();
        return resizedBitmap;
    }

    private void initRes(Context context) {
        if (VERSION.SDK_INT > 16) {

        } else {
            try {
                width = blurBitmap.getWidth();
                height = blurBitmap.getHeight();
                pix = new int[width * height];
                blurBitmap.getPixels(pix, 0, width, 0, 0, width, height);

                wm = width - 1;
                hm = height - 1;
                wh = width * height;
                r = new int[wh];
                g = new int[wh];
                b = new int[wh];
                vmin = new int[Math.max(width, height)];
            } catch (OutOfMemoryError error) {
                error.printStackTrace();
            }
        }
    }

    @SuppressLint("NewApi")
    public Bitmap fastBlur(int radius) {
        if (radius <= 0 || radius > 25) {
            return sentBitmap;
        }
        try {


            if (VERSION.SDK_INT > 16) {
                try {
                    rs = RenderScript.create(context);
                    input = Allocation.createFromBitmap(rs,
                            sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                            Allocation.USAGE_SCRIPT);
                    output = Allocation.createTyped(rs,
                            input.getType());
                    script = ScriptIntrinsicBlur.create(rs,
                            Element.U8_4(rs));
                    script.setRadius(radius);
                    script.setInput(input);
                    script.forEach(output);
                    output.copyTo(blurBitmap);
                    rs.destroy();
                    return blurBitmap;
                } catch (OutOfMemoryError error) {
                    // reduce picture quality
                    sentBitmap.setConfig(Bitmap.Config.ARGB_4444);
                    blurBitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
                    rs = RenderScript.create(context);
                    input = Allocation.createFromBitmap(rs,
                            sentBitmap, Allocation.MipmapControl.MIPMAP_NONE,
                            Allocation.USAGE_SCRIPT);
                    output = Allocation.createTyped(rs,
                            input.getType());
                    script = ScriptIntrinsicBlur.create(rs,
                            Element.U8_2(rs));
                    script.setRadius(radius);
                    script.setInput(input);
                    script.forEach(output);
                    output.copyTo(blurBitmap);
                    rs.destroy();
                    return blurBitmap;
                }
            }
            if (radius < 1) {
                return sentBitmap;
            }
            div = radius + radius + 1;
            divsum = (div + 1) >> 1;
            divsum *= divsum;
            dv = new int[256 * divsum];
            for (i = 0; i < 256 * divsum; i++) {
                dv[i] = (i / divsum);
            }

            yw = yi = 0;

            stack = new int[div][3];
            //123
            r1 = radius + 1;

            for (y = 0; y < height; y++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                for (i = -radius; i <= radius; i++) {
                    p = pix[yi + Math.min(wm, Math.max(i, 0))];
                    sir = stack[i + radius];
                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);
                    rbs = r1 - Math.abs(i);
                    rsum += sir[0] * rbs;
                    gsum += sir[1] * rbs;
                    bsum += sir[2] * rbs;
                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }
                }
                stackpointer = radius;

                for (x = 0; x < width; x++) {

                    r[yi] = dv[rsum];
                    g[yi] = dv[gsum];
                    b[yi] = dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm);
                    }
                    p = pix[yw + vmin[x]];

                    sir[0] = (p & 0xff0000) >> 16;
                    sir[1] = (p & 0x00ff00) >> 8;
                    sir[2] = (p & 0x0000ff);

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[(stackpointer) % div];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi++;
                }
                yw += width;
            }
            for (x = 0; x < width; x++) {
                rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
                yp = -radius * width;
                for (i = -radius; i <= radius; i++) {
                    yi = Math.max(0, yp) + x;

                    sir = stack[i + radius];

                    sir[0] = r[yi];
                    sir[1] = g[yi];
                    sir[2] = b[yi];

                    rbs = r1 - Math.abs(i);

                    rsum += r[yi] * rbs;
                    gsum += g[yi] * rbs;
                    bsum += b[yi] * rbs;

                    if (i > 0) {
                        rinsum += sir[0];
                        ginsum += sir[1];
                        binsum += sir[2];
                    } else {
                        routsum += sir[0];
                        goutsum += sir[1];
                        boutsum += sir[2];
                    }

                    if (i < hm) {
                        yp += width;
                    }
                }
                yi = x;
                stackpointer = radius;
                for (y = 0; y < height; y++) {
                    // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                    pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                            | (dv[gsum] << 8) | dv[bsum];

                    rsum -= routsum;
                    gsum -= goutsum;
                    bsum -= boutsum;

                    stackstart = stackpointer - radius + div;
                    sir = stack[stackstart % div];

                    routsum -= sir[0];
                    goutsum -= sir[1];
                    boutsum -= sir[2];

                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * width;
                    }
                    p = x + vmin[y];

                    sir[0] = r[p];
                    sir[1] = g[p];
                    sir[2] = b[p];

                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];

                    rsum += rinsum;
                    gsum += ginsum;
                    bsum += binsum;

                    stackpointer = (stackpointer + 1) % div;
                    sir = stack[stackpointer];

                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];

                    rinsum -= sir[0];
                    ginsum -= sir[1];
                    binsum -= sir[2];

                    yi += width;
                }
            }

            Log.e("pix", width + " " + height + " " + pix.length);
            blurBitmap.setPixels(pix, 0, width, 0, 0, width, height);
        }catch (Exception e){
            e.printStackTrace();
        }
        return (blurBitmap);
    }
}