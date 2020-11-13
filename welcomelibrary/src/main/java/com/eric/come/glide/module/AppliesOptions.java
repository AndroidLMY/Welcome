package com.eric.come.glide.module;

import android.content.Context;

import androidx.annotation.NonNull;

import com.eric.come.glide.GlideBuilder;

/** An internal interface, to be removed when {@link GlideModule}s are removed. */
@Deprecated
interface AppliesOptions {
  /**
   * Lazily apply options to a {@link com.eric.come.glide.GlideBuilder} immediately before the Glide
   * singleton is created.
   *
   * <p>This method will be called once and only once per implementation.
   *
   * @param context An Application {@link Context}.
   * @param builder The {@link com.eric.come.glide.GlideBuilder} that will be used to create Glide.
   */
  void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder);
}
