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

    public static final String SERVER_URL_GETSERVICES = "http://192.168.0.101:8084/AutoBookingWeb/rest/autobookingserver/requestService";
    public static final String SERVER_URL_BOOKING = "http://192.168.0.101:8084/AutoBookingWeb/rest/autobookingserver/requestBooking";

    public static ServerData getInstance() {
        return ourInstance;
    }

    private ServerData() {
    }

    public void loadService(){
        services.add(new MyItemListService(R.drawable.ic_gears, "Servicio General",""));
        services.add(new MyItemListService(R.drawable.ic_oil, "Cambio de Aceite",""));
        services.add(new MyItemListService(R.drawable.ic_braket, "Revisión de frenos",""));
        services.add(new MyItemListService(R.drawable.ic_suspe, "Revisión de suspención",""));
        services.add(new MyItemListService(R.drawable.ic_motor, "Revisión de motor",""));
        services.add(new MyItemListService(R.drawable.ic_light, "Revisión de luces",""));
        services.add(new MyItemListService(R.drawable.ic_direction, "Revisión de dirección",""));
        services.add(new MyItemListService(R.drawable.ic_neumatic, "Revisión de neumaticos",""));
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
