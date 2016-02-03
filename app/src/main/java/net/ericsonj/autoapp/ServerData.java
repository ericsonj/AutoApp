package net.ericsonj.autoapp;

import net.ericsonj.autoapp.myitemlist.MyItemListService;
import net.ericsonj.autoapp.myspinner.MyItemSpinner;

import java.util.LinkedList;

/**
 * Created by ejoseph on 1/25/16.
 */
public class ServerData {

    private LinkedList<MyItemListService> services = new LinkedList<>();
    private LinkedList<MyItemSpinner> cars = new LinkedList<>();

    private static ServerData ourInstance = new ServerData();

    public static String SERVER = "http://ericsonj.net:8084/";
    public static String SERVER_LOCAL = "http://192.168.88.254:8084/";
    public static  String SERVER_URL_ROOT = SERVER+"AutoBookingWeb/rest/autobookingserver/";
    public static  String SERVER_URL_GETSERVICES = SERVER_URL_ROOT+"requestService";
    public static  String SERVER_URL_BOOKING = SERVER_URL_ROOT+"requestBooking";
    public static  String SERVER_URL_REQUESTDATE = SERVER_URL_ROOT+"requestDate";
    public static  String SERVER_URL_REQUESTHOUR = SERVER_URL_ROOT+"requestAvailableHour";

    public static ServerData getInstance() {
        return ourInstance;
    }

    private ServerData() {
    }

    public void loadService(){
        services.add(new MyItemListService(R.drawable.ic_gears, "Servicio General","1"));
        services.add(new MyItemListService(R.drawable.ic_oil, "Cambio de Aceite","2"));
        services.add(new MyItemListService(R.drawable.ic_braket, "Revisión de frenos","3"));
        services.add(new MyItemListService(R.drawable.ic_suspe, "Revisión de suspención","4"));
        services.add(new MyItemListService(R.drawable.ic_motor, "Revisión de motor","5"));
        services.add(new MyItemListService(R.drawable.ic_light, "Revisión de luces","6"));
        services.add(new MyItemListService(R.drawable.ic_direction, "Revisión de dirección","7"));
        services.add(new MyItemListService(R.drawable.ic_neumatic, "Revisión de neumaticos","8"));
    }

    public void loadServices(LinkedList<MyItemListService> services){
        this.services = services;
    }

    public void loadcars(){
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Chevrolet", "Chevrolet"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Volkswagen", "Volkswagen"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Ford", "Ford"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Renault", "Renault"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Peugeot", "Peugeot"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Nissan", "Nissan"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "BMW", "BMW"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Audi", "Audi"));
        cars.add(new MyItemSpinner(R.drawable.ic_car, "Otro", "Otro"));
    }

    public int getIcon(String service){

        if(service.contains("GENERAL")){
            return R.drawable.ic_gears;
        }else if(service.toUpperCase().contains("ACEITE")){
            return R.drawable.ic_oil;
        }else if(service.toUpperCase().contains("FRENOS")){
            return R.drawable.ic_braket;
        }else if(service.toUpperCase().contains("SUSPEN")){
            return R.drawable.ic_suspe;
        }else if(service.toUpperCase().contains("MOTOR")){
            return R.drawable.ic_motor;
        }else if(service.toUpperCase().contains("LUCES")){
            return R.drawable.ic_light;
        }else if(service.toUpperCase().contains("DIRECCI")){
            return R.drawable.ic_direction;
        }else if(service.toUpperCase().contains("NEUMATICOS")){
            return R.drawable.ic_neumatic;
        }
        else{
            return R.drawable.ic_gears;
        }

    }

    public LinkedList<MyItemListService> getServices() {
        return services;
    }

    public LinkedList<MyItemSpinner> getCars() {
        return cars;
    }
}
