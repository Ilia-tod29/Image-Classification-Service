package com.talentboost.ics.services.impl;

import com.talentboost.ics.services.ChecksumService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

@Service
public class ChecksumServiceImpl implements ChecksumService {

    private final static int BUFFER_SIZE = 1024;

    private Long getChecksumCRC32(InputStream stream, int bufferSize) throws IOException {
        CheckedInputStream checkedInputStream = new CheckedInputStream(stream, new CRC32());
        byte[] buffer = new byte[bufferSize];
        while (checkedInputStream.read(buffer, 0, buffer.length) >= 0) {}
        return checkedInputStream.getChecksum().getValue();
    }

    @Override
    public Long checksumURL(String url) {
        try {
            InputStream is = new URL(url).openStream();

            return getChecksumCRC32(is, this.BUFFER_SIZE);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
