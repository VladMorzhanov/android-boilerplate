package com.morzhanov.boilerplate.util;

import android.webkit.MimeTypeMap;
import javax.inject.Inject;

public class MIMETypeUtils {

    @Inject
    public MIMETypeUtils() {
    }

    // url = file path or whatever suitable URL you want.
    public String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        if (type == null) {
            return null;
        }
        if (type.equals("image/bmp") ||
                type.equals("image/png") ||
                type.equals("image/jpg") ||
                type.equals("image/jpeg") ||
                type.equals("image/gif")) {
            return type;
        } else {
            return null;
        }
    }
}
