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


    public LinkedList<MyItemListService> getServices() {
        return services;
    }

    public LinkedList<MyItemSpinner> getCars() {
        return cars;
    }
}
