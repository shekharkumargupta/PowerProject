package com.opgea.crm.common.constant;

import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {
    
    /**
     * Development level temprory values.
     * These values have to be fetched from Database/Properties/XML
     */
    public static final int WORKING_HRS_IN_A_DAY = 8;
    /**
     * Application Default values.
     */
    public static final String DEFAULT_THEME = "aristo";
    public static final Integer DEFAULT_QUANTITY = 0;
    public static final String DEFAULT_PASSWORD = "default";
    
    /**
     * Characters that is required to be used in entire application.
     */
    public static final String DASH = "-";
    public static final String FORWARD_SLASH = "/";
    public static final String BACKWARD_SLASH = "\\";
    public static final String DOT = ".";
    
    /**
     * Conditional words.
     */
    public static final String SUCCESS = "Success";
    public static final String FAILURE = "Fail";
    public static final String YES = "Yes";
    public static final String NO = "No";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    
    /**
     * Words that may be use to check the authorization on 
     * below defined action.
     */
    public static final String CREATE = "create";
    public static final String READ = "read";
    public static final String UPDATE = "update";
    public static final String REMOVE = "remove";
    
    /**
     * Session Value defined.
     */
    public static final String SESSION_USER_DETAILS = "userDetails";
    public static final String SESSION_USER = "USER";
    
    /**
     * List of options.
     */
    public static final Long SELECTION_ALL = 0L;
    public static final Long SELECTION_SELECT = -1L;
    public static final Long SELECTION_ANY = -2L;
    public static final Long SELECTION_NONE = -3L;
    
    private static final Map<Long, String> selectionOption = new HashMap<Long, String>();
    
    static{
        selectionOption.put(SELECTION_ALL, "All");
        selectionOption.put(SELECTION_SELECT, "Select");
        selectionOption.put(SELECTION_ANY, "Any");
        selectionOption.put(SELECTION_NONE, "None");
    }
    
    public static String getOptionName(Long optionValue){
        return selectionOption.get(optionValue);
    }
    
    
    
    
    
}
