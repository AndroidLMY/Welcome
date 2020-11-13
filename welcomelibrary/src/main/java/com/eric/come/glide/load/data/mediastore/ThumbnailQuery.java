package com.eric.come.glide.load.data.mediastore;

import android.database.Cursor;
import android.net.Uri;

interface ThumbnailQuery {
  Cursor query(Uri uri);
}
