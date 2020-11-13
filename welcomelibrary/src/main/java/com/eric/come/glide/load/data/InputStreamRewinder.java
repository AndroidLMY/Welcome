package com.eric.come.glide.load.data;

import androidx.annotation.NonNull;

import com.eric.come.glide.load.engine.bitmap_recycle.ArrayPool;
import com.eric.come.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import com.eric.come.glide.util.Synthetic;

import java.io.IOException;
import java.io.InputStream;

/**
 * Implementation for {@link InputStream}s that rewinds streams by wrapping them in a buffered
 * stream.
 */
public final class InputStreamRewinder implements DataRewinder<InputStream> {
  // 5MB.
  private static final int MARK_READ_LIMIT = 5 * 1024 * 1024;

  private final RecyclableBufferedInputStream bufferedStream;

  @Synthetic
  public InputStreamRewinder(InputStream is, ArrayPool byteArrayPool) {
    // We don't check is.markSupported() here because RecyclableBufferedInputStream allows resetting
    // after exceeding MARK_READ_LIMIT, which other InputStreams don't guarantee.
    bufferedStream = new RecyclableBufferedInputStream(is, byteArrayPool);
    bufferedStream.mark(MARK_READ_LIMIT);
  }

  @NonNull
  @Override
  public InputStream rewindAndGet() throws IOException {
    bufferedStream.reset();
    return bufferedStream;
  }

  @Override
  public void cleanup() {
    bufferedStream.release();
  }

  public void fixMarkLimits() {
    bufferedStream.fixMarkLimit();
  }

  /**
   * Factory for producing {@link com.eric.come.glide.load.data.InputStreamRewinder}s from {@link
   * InputStream}s.
   */
  public static final class Factory implements DataRewinder.Factory<InputStream> {
    private final ArrayPool byteArrayPool;

    public Factory(ArrayPool byteArrayPool) {
      this.byteArrayPool = byteArrayPool;
    }

    @NonNull
    @Override
    public DataRewinder<InputStream> build(InputStream data) {
      return new InputStreamRewinder(data, byteArrayPool);
    }

    @NonNull
    @Override
    public Class<InputStream> getDataClass() {
      return InputStream.class;
    }
  }
}
