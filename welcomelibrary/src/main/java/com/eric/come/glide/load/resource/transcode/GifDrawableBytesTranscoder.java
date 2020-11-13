package com.eric.come.glide.load.resource.transcode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eric.come.glide.load.Options;
import com.eric.come.glide.load.engine.Resource;
import com.eric.come.glide.load.resource.bytes.BytesResource;
import com.eric.come.glide.load.resource.gif.GifDrawable;
import com.eric.come.glide.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * An {@link com.eric.come.glide.load.resource.transcode.ResourceTranscoder} that converts {@link
 * com.eric.come.glide.load.resource.gif.GifDrawable} into bytes by obtaining the original bytes of
 * the GIF from the {@link com.eric.come.glide.load.resource.gif.GifDrawable}.
 */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
  @Nullable
  @Override
  public Resource<byte[]> transcode(
      @NonNull Resource<GifDrawable> toTranscode, @NonNull Options options) {
    GifDrawable gifData = toTranscode.get();
    ByteBuffer byteBuffer = gifData.getBuffer();
    return new BytesResource(ByteBufferUtil.toBytes(byteBuffer));
  }
}
