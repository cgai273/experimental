package org.skar.pixivdl.storage;
import static com.google.common.truth.Truth.assertThat;

import okhttp3.Request;
import org.junit.Test;


public class FileIOTest {

    @Test public void testExtractFileNameFromUrl() {
        String url1 = "http://asdf-asdfasd.com/asdfa/asd/123_34jdfs.jpg";

        assertThat(FileIO.extractFileNameFromUrl(url1)).isEqualTo("123_34jdfs.jpg");
    }
}
