package edu.hm.hafner.util;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.OutputSupplier;

import org.junit.Test;
/**
 * Testet die ByteStream.copy() Methode.
 * @author Marvin Schütz
 * @author Sebastian Hansbauer
 */
public class ByteStreamsTest {
    /**
     *Testet die Methode ByteStream.copy.
     *@author Marvin Schütz
     *@author Sebastian Hansbauer
     * @throws IOException
     */
    @Test
    public void byteStreamCopyTest() throws IOException{
        InputStream from = mock(InputStream.class);
        when(from.read()).thenReturn(-1);

        OutputStream out = mock(OutputStream.class);

        OutputSupplier<OutputStream> outs = mock(OutputSupplier.class);
        when(outs.getOutput()).thenReturn(out);

       ByteStreams.copy(from,outs);
      // assertEquals(1,ByteStreams.copy(in,outs));
        verify(from).read();
    }
}

