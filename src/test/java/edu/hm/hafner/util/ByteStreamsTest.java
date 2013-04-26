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

    //Missing Blackbox testing: NULL NULL /NULL Object/ Object Null

    /**
     * Boundary Test
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test
    public void byteStreamCopyBoundaryTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - leer
        when(inMock.read(any(byte[].class))).thenReturn(-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        ByteStreams.copy(inMock, outputSupplierMock);

        //OutputStream - never write()
        verify(outMock, never()).write(any(byte[].class), anyInt(), anyInt());
    }

    /**
     * Interior Test
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test
    public void byteStreamCopyInteriorTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - gefuellt
        when(inMock.read(any(byte[].class))).thenReturn(2,1,-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        ByteStreams.copy(inMock, outputSupplierMock);

        //OutputStream - 2x write()
        verify(outMock,times(2)).write(any(byte[].class), anyInt(), anyInt());
    }

    /**
     * Write() IOException Test (close() ohne catch)
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test (expected = IOException.class) //in copy()
    public void byteStreamCopyWriteIOExceptionTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - gefuellt
        when(inMock.read(any(byte[].class))).thenReturn(2,1,-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        //OutputStream - throw IOException on write()
        doThrow(new IOException()).when(outMock).write(any(byte[].class), anyInt(), anyInt());

        ByteStreams.copy(inMock, outputSupplierMock);
    }

    /**
     * Read() IOException Test (close() ohne catch)
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test (expected = IOException.class) //in copy()
    public void byteStreamCopyReadIOExceptionTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - throw IOException on read()
        doThrow(new IOException()).when(inMock).read(any(byte[].class));

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        ByteStreams.copy(inMock, outputSupplierMock);
    }

    /**
     * OutMock close() IOException Test (close() else Zweig)
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test (expected = IOException.class)
    public void byteStreamCopyOutCloseIOExceptionTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - gefuellt
        when(inMock.read(any(byte[].class))).thenReturn(2,1,-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        //OutputStream - throw IOException on close()
        doThrow(new IOException()).when(outMock).close();

        ByteStreams.copy(inMock, outputSupplierMock);
    }

    /**
     * OutMock write() + close() IOException Test (close() if Zweig)
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test (expected = IOException.class) //in copy() nur die IOException von close() wird gefangen
    public void byteStreamCopyOutWriteAndOutCloseIOExceptionTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - gefuellt
        when(inMock.read(any(byte[].class))).thenReturn(2,1,-1);

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        //OutputStream - throw IOException on write() and close()
        doThrow(new IOException()).when(outMock).write(any(byte[].class), anyInt(), anyInt());
        doThrow(new IOException()).when(outMock).close();

        ByteStreams.copy(inMock, outputSupplierMock);
    }

    /**
     * InMock write() + OutMock close() IOException Test (close() if Zweig)
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test (expected = IOException.class) //in copy() nur die IOException von close() wird gefangen
    public void byteStreamCopyInWriteAndOutCloseIOExceptionTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputStream outMock = mock(OutputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - throw IOException on read()
        doThrow(new IOException()).when(inMock).read(any(byte[].class));

        //OutputSupplier -> return outMock
        when(outputSupplierMock.getOutput()).thenReturn(outMock);

        //OutputStream - throw IOException on close()
        doThrow(new IOException()).when(outMock).close();

        ByteStreams.copy(inMock, outputSupplierMock);
    }

    /**
     * Test Closeable = null (close() -> return)
     * @author Marvin Schütz
     * @author Sebastian Hansbauer
     * @throws IOException
     */
    @Test (expected = NullPointerException.class) //in copy() bei write()
    public void byteStreamCopyCloseableNullTest() throws IOException{

        InputStream inMock = mock(InputStream.class);
        OutputSupplier<OutputStream> outputSupplierMock = mock(OutputSupplier.class);

        //InputStream - gefuellt
        when(inMock.read(any(byte[].class))).thenReturn(2,1,-1);

        //OutputSupplier -> return null
        when(outputSupplierMock.getOutput()).thenReturn(null);

        ByteStreams.copy(inMock, outputSupplierMock);
    }
}