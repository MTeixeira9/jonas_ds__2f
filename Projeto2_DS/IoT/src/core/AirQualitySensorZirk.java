package core;

import i18n.I18N;
import static i18n.Messages.DEVICE_RUNNING;

import com.bezirk.middleware.Bezirk;
import com.bezirk.middleware.java.proxy.BezirkMiddleware;

public class AirQualitySensorZirk{
    private Bezirk bezirk;

    public AirQualitySensorZirk() {
        BezirkMiddleware.initialize();
        bezirk = BezirkMiddleware.registerZirk("Air Quality Sensor Zirk");
        System.err.println("Got Bezirk instance");
    }

    public void sendAirQualityUpdate() {
    	//produces some  values since this is a mock
        final double humidity = 0.8;
        final int dustLevel = 30;
        final int pollenLevel = 1000;
        final AirQualityUpdateEvent airQualityUpdateEvent = new AirQualityUpdateEvent(humidity, dustLevel, pollenLevel);

        //sends the event
        bezirk.sendEvent(airQualityUpdateEvent);
        System.err.println("Published air quality update: " + airQualityUpdateEvent.toString());
    }

    public static void main(String args[]) throws InterruptedException {
        AirQualitySensorZirk airQualitySensorZirk = new AirQualitySensorZirk();
        System.err.println("This product has an Air Quality Sensor");
        
        System.out.println(I18N.getString(DEVICE_RUNNING, "Air Quality Sensor"));
        airQualitySensorZirk.sendAirQualityUpdate();
     }
}