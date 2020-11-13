package com.eric.come.glide.load.engine;

import com.eric.come.glide.load.Key;
import com.eric.come.glide.load.Options;
import com.eric.come.glide.load.Transformation;

import java.util.Map;

class EngineKeyFactory {

  @SuppressWarnings("rawtypes")
  EngineKey buildKey(
      Object model,
      Key signature,
      int width,
      int height,
      Map<Class<?>, Transformation<?>> transformations,
      Class<?> resourceClass,
      Class<?> transcodeClass,
      Options options) {
    return new EngineKey(
        model, signature, width, height, transformations, resourceClass, transcodeClass, options);
  }
}
