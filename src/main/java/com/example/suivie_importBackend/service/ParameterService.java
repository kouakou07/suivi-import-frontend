package com.example.suivie_importBackend.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.suivie_importBackend.Enum.Deletion;
import com.example.suivie_importBackend.models.ParameterM;
import com.example.suivie_importBackend.repository.ParameterRepository;
import com.example.suivie_importBackend.tools.ReportError;
import com.example.suivie_importBackend.validator.ParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

@Service
public class ParameterService {

    @Autowired
    ParameterRepository parameterRepository;

    public String CODE_SESSION_TIME = "session_time";
    public String CODE_INACTIVITY_TIME = "inactivity_time";
    public String CODE_ATTEMPT_CONNECTION = "attempt_connection";
    public String CODE_UNC_DIRECTORY_INTEGRATION = "unc_remote_folder_integration";
    public String CODE_DIRECTORY_INTEGRATION = "path_remote_folder_integration";
    public String CODE_URI_INTEGRATION = "uri_remote_app_integration";
    public String CODE_PORT_INTEGRATION = "port_remote_app_integration";
    public String CODE_LOT_INTEGRATION = "index_lot";
    


    private Integer DEFAULT_VALUE_SESSION_TIME = 30; // minute
    private Integer DEFAULT_VALUE_INACTIVITY = 5; // minute
    private Integer DEFAULT_VALUE_ATTEMPT_CONNECTION = 3;
    private int DEFAULT_VALUE_INDEX_LOT = 0;


    public Object getAll(){
        return ResponseEntity.ok(parameterRepository.findByDeleted(Deletion.NO));
    }

    public String getIndexLot() throws ParseException{
        Optional<ParameterM> paramOptional = parameterRepository.findFirstByCodeAndDeleted(CODE_LOT_INTEGRATION, Deletion.NO);
        int value = 0;
        ParameterM parm;
        if(paramOptional.isPresent()){
            parm = paramOptional.get();
            value = Integer.parseInt(parm.getValue());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");;
            Date dateUpdated = dateFormat.parse(dateFormat.format(parm.getDateEdition()));
            Date now = dateFormat.parse(dateFormat.format(new Date()));

            long result = now.getTime() - dateUpdated.getTime();
            
            if(result == 0){
                value++;
            }else{
                value = 1;
            }
            parm.setValue(value + "");
            parm.update();

        }else{
            value = DEFAULT_VALUE_INDEX_LOT + 1;
            parm = new ParameterM(CODE_LOT_INTEGRATION, value+ "");
        }
        parm = parameterRepository.save(parm);
        String output = value + "";
        if(output.length() == 1){
            return "00" + output;
        }else if(output.length() == 2){
            return "0" + output;
        }else{
            return output;
        }
    }

    public Integer getSessionTime(){
        Optional<ParameterM> param = parameterRepository.findFirstByCodeAndDeleted(CODE_SESSION_TIME, Deletion.NO);
        if(param.isPresent()){
            return Integer.parseInt(param.get().getValue());
        }
        return DEFAULT_VALUE_SESSION_TIME;
    }

    public Integer getInactivityTime(){
        Optional<ParameterM> param = parameterRepository.findFirstByCodeAndDeleted(CODE_INACTIVITY_TIME, Deletion.NO);
        if(param.isPresent()){
            return Integer.parseInt(param.get().getValue());
        }
        return DEFAULT_VALUE_INACTIVITY;
    }

    public Object getAllSessions(){
        Map<String, Integer> output = new HashMap<String, Integer>();
        output.put(CODE_INACTIVITY_TIME, this.getInactivityTime());
        output.put(CODE_SESSION_TIME, this.getSessionTime());
        return ResponseEntity.ok(output);
    }

    public Integer getAttemptConnection(){
        Optional<ParameterM> param = parameterRepository.findFirstByCodeAndDeleted(CODE_ATTEMPT_CONNECTION, Deletion.NO);
        if(param.isPresent()){
            return Integer.parseInt(param.get().getValue());
        }
        return DEFAULT_VALUE_ATTEMPT_CONNECTION;
    }

    public Object update(@Valid ParameterValidator form, BindingResult res){
       
        List<String> codes = new ArrayList<String>();
        codes.add(CODE_ATTEMPT_CONNECTION);
        codes.add(CODE_INACTIVITY_TIME);
        codes.add(CODE_SESSION_TIME);

        if(res.hasErrors()){
            return ResponseEntity.badRequest().body(ReportError.getErrors(res));
        }
        if(!codes.contains(form.getCode())){
            Map<String, String> errors = new HashMap<String, String>();
            errors.put("code", "Le code n\'existe pas");
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<ParameterM> paramOptional = parameterRepository.findFirstByCodeAndDeleted(form.getCode(), Deletion.NO);
        ParameterM param;
        if(paramOptional.isPresent()){
            param = paramOptional.get();
            param.setValue(form.getValeur().toString());
        }else{
            param = new ParameterM(form.getCode(), form.getValeur().toString());
        }
        param = this.parameterRepository.save(param);
        return ResponseEntity.ok(param);
    }


    public Map<String, String> getRemoteIntegrationDir(){
        Optional<ParameterM> paramUncOptional =  parameterRepository.findFirstByCodeAndDeleted(CODE_UNC_DIRECTORY_INTEGRATION, Deletion.NO);
        Optional<ParameterM> paramPathOptional = parameterRepository.findFirstByCodeAndDeleted(CODE_DIRECTORY_INTEGRATION, Deletion.NO);
        Optional<ParameterM> paramUriOptional = parameterRepository.findFirstByCodeAndDeleted(CODE_URI_INTEGRATION, Deletion.NO);
        Optional<ParameterM> paramPortOptional = parameterRepository.findFirstByCodeAndDeleted(CODE_PORT_INTEGRATION, Deletion.NO);

        Map<String, String> output = new HashMap<String, String>();
        output.put(CODE_DIRECTORY_INTEGRATION, "");
        output.put(CODE_UNC_DIRECTORY_INTEGRATION, "");
        output.put(CODE_URI_INTEGRATION, "");
        output.put(CODE_PORT_INTEGRATION, "");
        if(paramUncOptional.isPresent()){
            output.put(CODE_UNC_DIRECTORY_INTEGRATION, paramUncOptional.get().getValue());
        }
        if(paramPathOptional.isPresent()){
            output.put(CODE_DIRECTORY_INTEGRATION, paramPathOptional.get().getValue());
        }
        if(paramUriOptional.isPresent()){
            output.put(CODE_URI_INTEGRATION, paramUriOptional.get().getValue());
        }
        if(paramPortOptional.isPresent()){
            output.put(CODE_PORT_INTEGRATION, paramPortOptional.get().getValue());
        }
        return output;
    }
}
