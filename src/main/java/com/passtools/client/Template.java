package com.passtools.client;


import com.passtools.client.exception.InvalidParameterException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Map;

public class Template extends PassToolsClient {
    public JSONObject templateHeader; /* keys are id,name,description... */
    public Map<String,JSONObject> fieldsModel; /* field key + field values = {value, label, changeMessage..} */


    private static void checkNotNull(Long templateId) {
        if (templateId == null) {
            throw new InvalidParameterException("please pass a valid template Id in!");
        }
    }


    public Long getId(){
        if (templateHeader!=null){
            return new Long((String)templateHeader.get("id"));
        } else {
            throw new RuntimeException("please set your templateHeader id first");
        }

    }


    public static Template getTemplate(Long templateId) {
        try {

            checkNotNull(templateId);

            String url = PassTools.API_BASE + "/template/" +templateId.toString();
            PassToolsResponse response = get(url);

            JSONObject jsonResponse = response.getBodyAsJSONObject();

            Template template = new Template();

            JSONObject jsonHeader = (JSONObject)jsonResponse.get("templateHeader");

            template.templateHeader = jsonHeader;
            template.fieldsModel = (JSONObject)jsonResponse.get("fieldsModel");


            return template;

        } catch (RuntimeException rte) {
            throw rte;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }







    public static List<JSONObject> getMyTemplateHeaders() {
        try {

            String url = PassTools.API_BASE + "/template/headers";
            PassToolsResponse response = get(url);


            JSONObject jsonResponse = response.getBodyAsJSONObject();
            JSONArray templatesArray = (JSONArray)jsonResponse.get("templateHeaders");

            return (List<JSONObject>)templatesArray;

        } catch (RuntimeException rte) {
            throw rte;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }




}
