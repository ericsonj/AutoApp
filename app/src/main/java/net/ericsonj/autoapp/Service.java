package net.ericsonj.autoapp;

import net.ericsonj.autoapp.myitemlist.MyItemListService;

import java.util.LinkedList;

/**
 * Created by ejoseph on 1/25/16.
 */
public class Service {

    private LinkedList<MyItemListService> services = new LinkedList<>();

    private static Service ourInstance = new Service();

    public static Service getInstance() {
        return ourInstance;
    }

    private Service() {
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

    public LinkedList<MyItemListService> getServices() {
        return services;
    }
}
