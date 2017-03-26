package myapps.wycoco.com.yourfaceseemsattendance.FaceDetector;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by Lenovo on 3/25/2017.
 */

public class ExifUtil {

    public static Bitmap rotateBitmap(String path, Bitmap bitmap) {
        try {
            int orientation = getExifOrientation(path);

            if (orientation == 1) {
                return bitmap;
            }

            Matrix matrix = new Matrix();
            switch (orientation) {
                case 2:
                    matrix.setScale(-1, 1);
                    break;

                case 3:
                    matrix.setRotate(180);
                    break;

                case 4:
                    matrix.setRotate(180);
                    matrix.postScale(-1, 1);
                    break;

                case 5:
                    matrix.setRotate(90);
                    matrix.postScale(-1, 1);
                    break;

                case 6:
                    matrix.setRotate(90);
                    break;

                case 7:
                    matrix.setRotate(-90);
                    matrix.postScale(-1, 1);
                    break;

                case 8:
                    matrix.setRotate(-90);
                    break;

                default:
                    return bitmap;
            }

            try {
                Bitmap oriented = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                bitmap.recycle();
                return oriented;

            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                return bitmap;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static int getExifOrientation(String path) throws IOException {
        int orientation = 1;
        ExifInterface exif = new ExifInterface(path);
        orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        return orientation;
    }
}
