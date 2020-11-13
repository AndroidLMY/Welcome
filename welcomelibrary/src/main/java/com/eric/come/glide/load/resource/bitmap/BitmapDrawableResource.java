package com.eric.come.glide.load.resource.bitmap;

import android.graphics.drawable.BitmapDrawable;

import androidx.annotation.NonNull;

import com.eric.come.glide.load.engine.Initializable;
import com.eric.come.glide.load.engine.bitmap_recycle.BitmapPool;
import com.eric.come.glide.load.resource.drawable.DrawableResource;
import com.eric.come.glide.util.Util;

/**
 * A {@link com.eric.come.glide.load.engine.Resource} that wraps an {@link
 * BitmapDrawable}
 *
 * <p>This class ensures that every call to {@link #get()}} always returns a new {@link
 * BitmapDrawable} to avoid rendering issues if used in multiple views and
 * is also responsible for returning the underlying {@link android.graphics.Bitmap} to the given
 * {@link com.eric.come.glide.load.engine.bitmap_recycle.BitmapPool} when the resource is recycled.
 */
public class BitmapDrawableResource extends DrawableResource<BitmapDrawable>
    implements Initializable {
  private final BitmapPool bitmapPool;

  // Public API.
  @SuppressWarnings("WeakerAccess")
  public BitmapDrawableResource(BitmapDrawable drawable, BitmapPool bitmapPool) {
    super(drawable);
    this.bitmapPool = bitmapPool;
  }

  @NonNull
  @Override
  public Class<BitmapDrawable> getResourceClass() {
    return BitmapDrawable.class;
  }

  @Override
  public int getSize() {
    return Util.getBitmapByteSize(drawable.getBitmap());
  }

  @Override
  public void recycle() {
    bitmapPool.put(drawable.getBitmap());
  }

  @Override
  public void initialize() {
    drawable.getBitmap().prepareToDraw();
  }
}
