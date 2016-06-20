package protocol.Packet;

import org.json.simple.JSONObject;

/**
 * Created by Lee on 2016-05-31.
 */
public interface PacketFactory {
    JSONObject join(String user_id, String user_pw, String user_name, int job_id);
    JSONObject login(String user_id, String user_pw);
    JSONObject character(long uuid, String name, int level, int job_id);
    JSONObject move(long entity_id, int map_id, int dest_x, int dest_y);
    JSONObject chat(long entity_id, String content);
    JSONObject notify(String content);
}
