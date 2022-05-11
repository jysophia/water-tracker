package persistence;

import org.json.JSONObject;

public interface Writable {
    //Reference: JsonSerializationDemo
    //EFFECT: this is returned as a JSON Object
    JSONObject toJson();
}
