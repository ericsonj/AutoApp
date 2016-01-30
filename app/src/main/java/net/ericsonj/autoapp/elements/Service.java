package net.ericsonj.autoapp.elements;

/**
 * Created by ejoseph on 1/30/16.
 */

public class Service{

    private long id;


    private String nameService;


    private String descriptionService;

    public Service() {
    }

    public Service(String nameService, String descriptionService) {
        this.nameService = nameService;
        this.descriptionService = descriptionService;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public String getDescriptionService() {
        return descriptionService;
    }

    public void setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nameService=" + nameService + ", descriptionService=" + descriptionService + '}';
    }

}