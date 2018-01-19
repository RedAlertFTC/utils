package org.firstinspires.ftc.teamcode.utils;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class MatrixUtils {

    MatrixUtils() {
    }

    /**
     * This figures out weather two OpenGLMatrixs are close to each other
     *
     * @param a        first OpenGLMatrix the one being compared
     * @param b        second OpenGLMatrix the second being compared
     * @param deadZone the area to return true
     * @return whether or not a and b are not close
     * TODO: 12/19/2017 fix vague JavaDocs
     * @see OpenGLMatrix
     */
    public static boolean isNotClose(OpenGLMatrix a, OpenGLMatrix b, double deadZone) {
        deadZone /= 2;
        if (Math.abs((b.getTranslation().get(0)) - (a.getTranslation().get(0))) <= deadZone) {
            return false;
        }
        if (Math.abs((b.getTranslation().get(1)) - (a.getTranslation().get(1))) <= deadZone) {
            return false;
        }
        if (Math.abs((b.getTranslation().get(2)) - (a.getTranslation().get(2))) <= deadZone) {
            return false;
        }
        if (Math.abs((Orientation.getOrientation(b, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle) - Orientation.getOrientation(a, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).firstAngle) <= deadZone) {
            return false;
        }
        if (Math.abs((Orientation.getOrientation(b, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle) - Orientation.getOrientation(a, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).secondAngle) <= deadZone) {
            return false;
        }
        return !(Math.abs((Orientation.getOrientation(b, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle) - Orientation.getOrientation(a, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle) <= deadZone);
    }

    /**
     * This figures out weather two OpenGLMatrixs are close to each other
     *
     * @param a first OpenGLMatrix the one being compared
     * @param b second OpenGLMatrix the second being compared
     * @return whether or not a and b are not close
     * TODO: 12/19/2017 fix vague JavaDocs
     * @see OpenGLMatrix
     */
    public static boolean isNotClose(OpenGLMatrix a, OpenGLMatrix b) {
        return isNotClose(a, b, 1);
    }
}
