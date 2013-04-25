package edu.hm.hafner.util;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.common.io.ByteStreams;
import com.google.common.io.OutputSupplier;

import org.junit.Test;

/**
 * Boundary interior Test der ByteStream Methode copy().
 * @author Marvin Schütz
 * @author Sebastian Hansbauer
 */
public class ByteStreamsTest {

    /**
     * Boundary Test
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test
    public void byteStreamCopyBoundaryTest() throws IOException{

        long returnValue;
        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - leer
        when(inMock.read(any(byte[].class))).thenReturn(-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        returnValue = ByteStreams.copy(inMock, outputSupplierMock);

        //OutputStream - never write()
        verify(outMock, never()).write(any(byte[].class), anyInt(), anyInt());

        System.out.println("Test 1 beendet!");
    }

    /**
     * Interior Test
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test
    public void byteStreamCopyInteriorTest() throws IOException{

        long returnValue;
        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - gefuellt
        when(inMock.read(any(byte[].class))).thenReturn(2,1,-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        returnValue = ByteStreams.copy(inMock, outputSupplierMock);

        //OutputStream - 2x write()
        verify(outMock,times(2)).write(any(byte[].class), anyInt(), anyInt());

        System.out.println("Test 2 beendet!");
    }
}