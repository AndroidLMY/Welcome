package com.eric.come.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

/**
 * A {@link TransitionFactory} for {@link Bitmap}s that uses a Drawable transition
 * factory to transition from an existing drawable already visible on the target to the new bitmap.
 *
 * @see BitmapContainerTransitionFactory
 */
public class BitmapTransitionFactory extends BitmapContainerTransitionFactory<Bitmap> {
  public BitmapTransitionFactory(@NonNull TransitionFactory<Drawable> realFactory) {
    super(realFactory);
  }

  @Override
  @NonNull
  protected Bitmap getBitmap(@NonNull Bitmap current) {
    return current;
  }
}
