package com.macovski.jndi;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.macovski.interfaces.IMatchManager;
import com.macovski.interfaces.IPlayerManager;
import com.macovski.interfaces.IVenueManager;

public class JNDILookup {
	 
    private static Context initialContext;
 
    private static final String PKG_INTERFACES = "org.jboss.ejb.client.naming";
 
    public static Context getInitialContext() throws NamingException {
        if (initialContext == null) {
            Properties properties = new Properties();
            properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);
 
            initialContext = new InitialContext(properties);
        }
        return initialContext;
    }
    
    public static IPlayerManager getPlayerManagerLookup()
    {
    	Context context = null;
    	IPlayerManager bean = null;
        try {
            // 1. Obtaining Context
            context = getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = "ejb:/macovski//PlayerManager!com.macovski.interfaces.IPlayerManager";
            // 3. Lookup and cast
            bean = (IPlayerManager) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        bean.InitializeEM();
        return bean;
    }
    
    public static IMatchManager getMatchManagerLookup()
    {
    	Context context = null;
    	IMatchManager bean = null;
        try {
            // 1. Obtaining Context
            context = getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = "ejb:/macovski//MatchManager!com.macovski.interfaces.IMatchManager";
            // 3. Lookup and cast
            bean = (IMatchManager) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        bean.InitializeEM();
        return bean;
    }
    
    public static IVenueManager getVenueManagerLookup()
    {
    	Context context = null;
    	IVenueManager bean = null;
        try {
            // 1. Obtaining Context
            context = getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = "ejb:/macovski//VenueManager!com.macovski.interfaces.IVenueManager";
            // 3. Lookup and cast
            bean = (IVenueManager) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        bean.InitializeEM();
        return bean;
    }
}
