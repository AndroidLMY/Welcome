package com.eric.come.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.ImageDecoder.Source;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.eric.come.glide.load.Options;
import com.eric.come.glide.load.ResourceDecoder;
import com.eric.come.glide.load.engine.Resource;
import com.eric.come.glide.util.ByteBufferUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/** {@link InputStream} specific implementation of {@link BitmapImageDecoderResourceDecoder}. */
@RequiresApi(api = 28)
public final class InputStreamBitmapImageDecoderResourceDecoder
    implements ResourceDecoder<InputStream, Bitmap> {
  private final BitmapImageDecoderResourceDecoder wrapped = new BitmapImageDecoderResourceDecoder();

  @Override
  public boolean handles(@NonNull InputStream source, @NonNull Options options) throws IOException {
    return true;
  }

  @Nullable
  @Override
  public Resource<Bitmap> decode(
      @NonNull InputStream stream, int width, int height, @NonNull Options options)
      throws IOException {
    ByteBuffer buffer = ByteBufferUtil.fromStream(stream);
    Source source = ImageDecoder.createSource(buffer);
    return wrapped.decode(source, width, height, options);
  }
}
