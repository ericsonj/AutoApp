package net.ericsonj.autoapp.elements;

/**
 * Created by ejoseph on 1/30/16.
 */
public class RequestMessage {

    private String code;
    private String message;

    public RequestMessage() {
    }

    public RequestMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestMessage{" + "code=" + code + ", message=" + message + '}';
    }

}
