package org.iota.jota.account.deposits.methods;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import org.iota.jota.account.deposits.ConditionalDepositAddress;
import org.iota.jota.account.deposits.DepositRequest;
import org.iota.jota.account.deposits.DepositTest;
import org.junit.Before;
import org.junit.Test;

import net.glxn.qrgen.javase.QRCode;

public class QrTest extends DepositTest {
    
    private static final byte[] qrBytes = new byte[] {
            -119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 125, 0, 0, 0, 125, 1, 0, 0, 0, 0, 
            -89, -5, 81, -120, 0, 0, 3, 2, 73, 68, 65, 84, 120, -38, 99, -8, -113, 10, 126, 48, -116, 10, 12, 39, -127, 
            15, -126, 23, 20, 66, 2, 109, 56, 28, -22, -95, 2, -33, 47, -83, -82, -49, 18, 56, -40, 127, 31, 38, -16, 
            37, 116, -3, -2, 59, -59, -66, -114, 23, -31, 2, -63, -3, -105, -66, 24, -19, -115, 15, -124, 11, 68, -2, 
            44, -72, -64, 104, 42, 8, 23, -8, 126, -91, -29, -51, -14, -120, -123, 83, -31, 102, 124, 16, 13, 5, 3, -72, 
            45, -1, -1, -37, -98, 102, 15, -120, 89, 9, 119, -57, 103, -23, -123, 37, 11, 56, -100, 46, -67, -128, -87, 
            -8, -6, -80, 87, -25, 112, -96, 73, 119, 0, 92, 64, -24, 94, -61, 74, -81, 51, 29, 19, 96, 90, 126, 87, 86, 
            109, -105, 73, -71, 45, -77, 30, 38, -16, 101, -6, -122, -83, -45, -106, 59, 76, 90, 9, -45, -14, 115, -59, 
            76, -121, -25, 87, -106, 54, -69, -61, 4, -2, 77, -37, -29, 94, 122, -25, -106, -22, 61, -104, -106, -113, 
            -27, 11, 119, -83, -68, 118, -10, -51, 53, -104, -118, -113, -121, -91, 20, -102, -78, 83, 54, -82, -123, 
            -87, -8, -91, 125, -10, -24, -28, 72, -99, 89, -2, -16, 32, -100, -72, 95, 61, 127, 9, -113, -30, 50, -104, 
            -64, -89, -9, 15, 60, 120, -40, -113, -22, -124, -63, -35, -47, 39, 104, 59, 91, 95, 109, -69, 39, -36, -5, 
            -90, -109, 21, -6, 42, 84, -123, -62, -32, 2, -62, 126, 73, -117, 127, 90, 113, -102, -64, -67, -1, -32, 
            -71, 69, -51, -123, 13, 57, -33, 96, 42, 126, 95, -4, -39, 114, -99, 51, -19, 66, 39, 76, -32, -121, -118, 
            66, 65, -105, -89, -19, -84, -40, -1, 112, -65, -84, -6, 31, -43, -41, -101, 81, 6, 119, -23, -34, -56, 78, 
            -42, -53, -89, -89, 112, -63, -76, 124, 10, 122, -13, -123, -37, 82, 121, 2, 23, 76, -59, -73, 124, 111, 
            -42, -103, -66, 91, -72, -32, 46, -3, 116, -24, 83, -59, -22, -93, 12, -103, 47, -32, 78, -33, -3, 93, -106, 
            -65, -78, 34, 8, 30, 115, -1, -39, -25, -102, 31, -80, 101, -7, -52, 4, -73, -91, 48, 35, -63, -36, -63, 
            -110, 11, 97, 109, 0, 67, -49, 82, -41, 19, -121, -125, -32, 14, 43, -81, -36, -70, 61, -13, 29, 95, 57, 76, 
            -32, 7, 107, -64, -102, 6, -17, -92, 13, -84, -16, -12, -15, -26, 124, -46, 124, 14, -99, -90, -71, 112, 91, 
            -36, -77, -104, 47, 108, -29, -15, -47, -125, -69, -12, 101, -31, 25, -18, 117, 127, 123, -118, -32, 2, 94, 
            -98, 37, -85, 95, -70, 92, -11, -122, -57, -83, -35, 78, -99, 107, 14, -90, 92, 28, 48, 21, 63, -69, -126, 
            -38, -73, 26, 45, -82, 91, 7, -73, -74, -34, 121, 106, -46, 65, -9, -38, 52, 120, -104, 74, 103, 44, -23, 
            126, -88, 48, -7, 4, 92, 32, 67, 95, -23, -47, 20, 93, 110, 62, 120, -120, 121, -49, -83, -37, 50, -85, 125, 
            47, -36, 29, -97, -116, 63, -18, -38, 27, 16, -73, -1, 25, 76, -32, 111, -40, 36, -117, -26, -9, 44, 95, 63, 
            -64, 83, -95, 81, -18, -45, 119, 23, 54, 8, 105, -61, -61, 67, 62, -37, -73, -14, -66, -29, 27, 3, 120, 26, 
            -45, 13, -79, 96, 109, -84, -3, -101, 7, 79, 65, 39, 23, -102, -12, -83, 119, -65, -8, 15, 110, 109, 85, 
            -76, -54, -21, 15, 58, -51, -70, 48, -127, 95, 23, -61, -33, -19, 120, 118, 68, 53, 13, 110, -58, 58, 125, 
            -91, -33, -9, 95, 27, -15, -61, 83, -112, -68, 100, -61, 45, -51, -124, -87, -79, 112, 45, -49, 14, -108, 
            -11, -72, -40, 48, 107, -62, -13, -117, 56, -121, -84, -60, 18, 62, -23, 69, 48, 51, -66, 63, -108, 62, -5, 
            122, -53, -95, -116, 56, -104, -118, 111, -18, -102, -70, 12, -15, 14, 83, -39, -31, 14, -5, -76, -42, -24, 
            -72, -41, -102, -53, -16, -104, -5, 32, 123, -87, 112, -86, 82, -73, 79, 0, -36, -116, -21, 118, 79, -105, 
            51, -70, 85, -64, 99, -1, 75, -24, 93, -11, 9, -4, 41, 83, 25, 17, -123, 65, -122, -126, -41, 73, -101, 75, 
            90, 112, -127, 104, -113, 57, -83, -33, 109, -18, 111, -123, -101, 113, -115, 125, -17, -35, 79, -99, -94, 
            -6, 112, 91, -124, 42, 85, 38, 123, 59, 108, -37, 91, 63, 90, 38, 15, 107, 1, 0, -68, 15, -111, 15, -10, 16, 
            -8, 64, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126
    };


    
    QRMethod qrMethod = null;
    ConditionalDepositAddress conditions = null;
    
    @Before
    public void setUp() throws Exception {
        qrMethod = new QRMethod();
        
        DepositRequest request = new DepositRequest(new Date(0), false, 5);
        conditions = new ConditionalDepositAddress(request, depositAddress);
    }

    @Test
    public void qrFromString() {
        QRCode code = qrMethod.build(conditions);
        ConditionalDepositAddress deposit = qrMethod.parse(code);
        assertEquals(conditions, deposit);
    }

    @Test
    public void qrFromDeposit() throws IOException {
        QRCode code = qrMethod.build(conditions);
        ByteArrayOutputStream stream = code.stream();
        System.out.println(java.util.Arrays.toString(code.stream().toByteArray()));
        
        assertArrayEquals(qrBytes, code.stream().toByteArray());
        stream.close();
    }
}
