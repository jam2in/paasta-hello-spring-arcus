package org.openpaas.support;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.openpaas.arcus.ArcusCache;

public class ArcusSupport {

    private ArcusCache arcusCache;

    public ArcusSupport() {
        this.arcusCache();
    }

    public void arcusCache() {
        String vcap_services = System.getenv("VCAP_SERVICES");
        if(vcap_services == null) return;
        System.out.println("VCAP_SERVICES = " + vcap_services);

        JSONObject jsonObj = JSONObject.fromObject(vcap_services);
        System.out.println("vcap_service = " + jsonObj);

        JSONArray userPro = jsonObj.getJSONArray("ARCUS");

        jsonObj = JSONObject.fromObject(userPro.get(0));
        jsonObj = jsonObj.getJSONObject("credentials");
        String serverAddress = jsonObj.getString("uri");
        String serviceCode = jsonObj.getString("name");

        System.out.println("serverAddress = " + serverAddress);
        System.out.println("serviceCode = " + serviceCode);

        this.arcusCache = new ArcusCache();
        this.arcusCache.setServerAddress(serverAddress);
        this.arcusCache.setServiceCode(serviceCode);

        this.arcusCache.setup();
    }

    public ArcusCache getArcusCache() {
        return arcusCache;
    }
}

