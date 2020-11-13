package com.eric.come.glide.load.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.eric.come.glide.Glide;
import com.eric.come.glide.Registry;

/**
 * An interface for creating a {@link ModelLoader} for a given model type.
 *
 * <p>The application {@link Context} can be passed in to the constructor of the
 * factory when necessary. It's unsafe to retain {@link android.app.Activity} {@link
 * Context}s in factories. The {@link Context} can be obtained from
 * {@link com.eric.come.glide.module.LibraryGlideModule#registerComponents(Context, Glide, Registry)}
 * in most cases.
 *
 * @param <T> The type of the model the {@link com.eric.come.glide.load.model.ModelLoader}s built by
 *     this factory can handle
 * @param <Y> The type of data the {@link com.eric.come.glide.load.model.ModelLoader}s built by this
 *     factory can load.
 */
public interface ModelLoaderFactory<T, Y> {

  /**
   * Build a concrete ModelLoader for this model type.
   *
   * @param multiFactory A map of classes to factories that can be used to construct additional
   *     {@link ModelLoader}s that this factory's {@link ModelLoader} may depend on
   * @return A new {@link ModelLoader}
   */
  @NonNull
  ModelLoader<T, Y> build(@NonNull MultiModelLoaderFactory multiFactory);

  /** A lifecycle method that will be called when this factory is about to replaced. */
  void teardown();
}
