package haodong.com.views_diy.prac_point;

import android.animation.TypeEvaluator;



/**
 * @auther linghaoDo QQ:1052354999
 * Created on 2018/10/14
 * @Describu:
 */
public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        DiyPoint startDiyPoint = (DiyPoint) startValue;
        DiyPoint endDiyPoint =(DiyPoint)endValue;

        float x = startDiyPoint.getX() + fraction * (endDiyPoint.getX() - startDiyPoint.getX());
        float y = startDiyPoint.getY() + fraction * (endDiyPoint.getY() - startDiyPoint.getY());
        // 将计算后的坐标封装到一个新的Point对象中并返回
        DiyPoint diyPoint = new DiyPoint(x, y);
        return diyPoint;
    }
}
